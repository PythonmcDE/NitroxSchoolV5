package xyz.daarkii.school.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.daarkii.school.core.command.sender.Sender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandManager {

    private final Set<Command> commands = new HashSet<>();

    public void addCommand(Command command) {
        this.commands.add(command);

        var commandMap = Bukkit.getCommandMap();

        var existingCommand = commandMap.getCommand(command.getCommandInfo().name());

        if(existingCommand != null)
            existingCommand.unregister(commandMap);

        commandMap.register(command.getCommandInfo().name(), new CommandWrapper(command.getCommandInfo().name(), command.getCommandInfo().usage(), command.getCommandInfo().aliases().stream().toList(), this));
    }

    public void removeCommand(String name) {

        var commandMap = Bukkit.getCommandMap();
        var command = commandMap.getCommand(name);

        if(command != null)
            command.unregister(commandMap);
    }

    public void executeCommand(String name, CommandSender commandSender, String[] args) {

        var commands = this.commands.stream().filter(command -> command.getCommandInfo().name().equalsIgnoreCase(name)).toList();

        if(commands.isEmpty())
            return;

        var sender = new Sender(commandSender);

        commands.forEach(command -> {

            command.execute(sender, args);
            command.getConsumer(args).forEach(consumer -> consumer.accept(sender, args));

            if(commandSender instanceof Player) {

                command.execute(sender.asPlayer(), args);
                command.getPlayerConsumer(args).forEach(consumer -> consumer.accept(sender.asPlayer(), args));
            } else {

                command.execute(sender.asConsole(), args);
                command.getConsoleConsumer(args).forEach(consumer -> consumer.accept(sender.asConsole(), args));
            }
        });
    }

    private static class CommandWrapper extends BukkitCommand {

        private final CommandManager commandManager;
        private final String name;

        private CommandWrapper(@NotNull String name, @NotNull String usageMessage, @NotNull List<String> aliases, CommandManager commandManager) {
            super(name, "", usageMessage, aliases);
            this.name = name;
            this.commandManager = commandManager;
        }

        @Override
        public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
            this.commandManager.executeCommand(this.name, sender, args);
            return false;
        }
    }
}
