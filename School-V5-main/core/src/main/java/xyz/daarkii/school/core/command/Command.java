package xyz.daarkii.school.core.command;

import org.jetbrains.annotations.ApiStatus;
import xyz.daarkii.school.common.collection.Pair;
import xyz.daarkii.school.core.command.sender.Sender;
import xyz.daarkii.school.core.console.Console;
import xyz.daarkii.school.core.entity.SchoolPlayer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public abstract class Command {

    private final Map<Integer, Collection<Pair<CommandArgument[], BiConsumer<Sender, String[]>>>> consumerUse = new ConcurrentHashMap<>();
    private final Map<Integer, Collection<Pair<CommandArgument[], BiConsumer<SchoolPlayer, String[]>>>> playerConsumerUse = new ConcurrentHashMap<>();
    private final Map<Integer, Collection<Pair<CommandArgument[], BiConsumer<Console, String[]>>>> consoleConsumerUse = new ConcurrentHashMap<>();

    private final CommandInfo commandInfo;

    public Command(String name, String permission, String usageMessage, Collection<String> aliases) {
        this.commandInfo = new CommandInfo(name, permission, usageMessage, aliases);
    }

    public Command(String name, String permission, String usageMessage, String... aliases) {
        this.commandInfo = new CommandInfo(name, permission, usageMessage, List.of(aliases));
    }

    public Command(String name, String permission, Collection<String> aliases) {
        this.commandInfo = new CommandInfo(name, permission, "school.command.default.usage", aliases);
    }

    public Command(String name, String permission, String... aliases) {
        this.commandInfo = new CommandInfo(name, permission, "school.command.default.usage", List.of(aliases));
    }

    public Command(String name, String permission) {
        this.commandInfo = new CommandInfo(name, permission, "school.command.default.usage", List.of());
    }

    /**
     * Gets called when a Bukkit Command gets executed
     *
     * @param sender    the command sender object
     * @param args      the used arguments
     */
    public void execute(Sender sender, String[] args) {}

    /**
     * Gets called when a Bukkit Command gets executed from a player
     *
     * @param player    the schoolPlayer object
     * @param args      the used arguments
     */
    public void execute(SchoolPlayer player, String[] args) {}


    /**
     * Gets called when a Bukkit Command gets executed from the console
     *
     * @param console    the console object
     * @param args       the used arguments
     */
    public void execute(Console console, String[] args) {}

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     */
    public final void setOnUse(Integer argumentSize, BiConsumer<Sender, String[]> consumer) {
        this.setOnUse(argumentSize, consumer, new CommandArgument[]{});
    }

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed by a player
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     */
    public final void setOnPlayerUse(Integer argumentSize, BiConsumer<SchoolPlayer, String[]> consumer) {
        this.setOnPlayerUse(argumentSize, consumer, new CommandArgument[]{});
    }

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed by the console
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     */
    public final void setOnConsoleUse(Integer argumentSize, BiConsumer<Console, String[]> consumer) {
        this.setOnConsoleUse(argumentSize, consumer, new CommandArgument[]{});
    }

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     * @param arguments         the arguments which describes when the consumer gets called
     */
    public final void setOnUse(Integer argumentSize, BiConsumer<Sender, String[]> consumer, CommandArgument... arguments) {

        var current = this.consumerUse.getOrDefault(argumentSize, List.of());

        current.add(new Pair<>(arguments, consumer));
        this.consumerUse.put(argumentSize, current);
    }

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed by a player
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     * @param arguments         the arguments which describes when the consumer gets called
     */
    public final void setOnPlayerUse(Integer argumentSize, BiConsumer<SchoolPlayer, String[]> consumer, CommandArgument... arguments) {

        var current = this.playerConsumerUse.getOrDefault(argumentSize, List.of());

        current.add(new Pair<>(arguments, consumer));
        this.playerConsumerUse.put(argumentSize, current);
    }

    /**
     * Adds a new consumer which gets accepted when a bukkit command is executed by the console
     *
     * @param argumentSize      the size of the arguments
     * @param consumer          the consumer which includes a sender object and the arguments
     * @param arguments         the arguments which describes when the consumer gets called
     */
    public final void setOnConsoleUse(Integer argumentSize, BiConsumer<Console, String[]> consumer, CommandArgument... arguments) {

        var current = this.consoleConsumerUse.getOrDefault(argumentSize, List.of());

        current.add(new Pair<>(arguments, consumer));
        this.consoleConsumerUse.put(argumentSize, current);
    }

    /**
     * Gets all sender objects consumer which matches the arguments
     *
     * @param args      the arguments to check
     * @return          a collection of all consumer
     */
    @ApiStatus.Internal
    public final Collection<BiConsumer<Sender, String[]>> getConsumer(String[] args) {

        var current = this.consumerUse.get(args.length);

        if(current == null)
            return List.of();

        return current.stream().filter(pair -> this.isValid(pair.first(), args)).map(Pair::second).toList();
    }

    /**
     * Gets all player objects consumer which matches the arguments
     *
     * @param args      the arguments to check
     * @return          a collection of all consumer
     */
    @ApiStatus.Internal
    public final Collection<BiConsumer<SchoolPlayer, String[]>> getPlayerConsumer(String[] args) {

        var current = this.playerConsumerUse.get(args.length);

        if(current == null)
            return List.of();

        return current.stream().filter(pair -> this.isValid(pair.first(), args)).map(Pair::second).toList();
    }

    /**
     * Gets all console objects consumer which matches the arguments
     *
     * @param args      the arguments to check
     * @return          a collection of all consumer
     */
    @ApiStatus.Internal
    public final Collection<BiConsumer<Console, String[]>> getConsoleConsumer(String[] args) {

        var current = this.consoleConsumerUse.get(args.length);

        if(current == null)
            return List.of();

        return current.stream().filter(pair -> this.isValid(pair.first(), args)).map(Pair::second).toList();
    }

    /**
     * Checks if the command arguments match the accepted arguments
     *
     * @param arguments     the accepted arguments
     * @param args          the command arguments
     * @return weather the arguments matches or not
     */
    private boolean isValid(CommandArgument[] arguments, String[] args) {

        for(var i = 0; i < args.length; i++) {

            int finalI = i;
            var commandArguments = Arrays.stream(arguments).filter(commandArgument -> commandArgument.argument == (finalI + 1)).toList();

            if(commandArguments.isEmpty())
                continue;

            var argument = args[i];

            if(commandArguments.stream().map(CommandArgument::acceptedValues).noneMatch(values -> Arrays.stream(values).noneMatch(value -> value.equalsIgnoreCase(argument))))
                return false;
        }

        return true;
    }

    /**
     * Gets the {@link CommandInfo} from this command
     *
     * @return the wrapped {@link CommandInfo}
     */
    public CommandInfo getCommandInfo() {
        return this.commandInfo;
    }

    /**
     * Class used to identify accepted command arguments
     *
     * @param argument          the position of the checked values
     * @param acceptedValues    the accepted values
     */
    public record CommandArgument(int argument, String[] acceptedValues) { }
}
