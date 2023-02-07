package xyz.daarkii.school.core.command.sender;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.daarkii.school.core.console.Console;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.factory.PlayerFactory;

public class Sender {

    private final CommandSender sender;

    public Sender(CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public boolean isConsole() {
        return sender instanceof ConsoleCommandSender;
    }

    public SchoolPlayer asPlayer() {
        return PlayerFactory.getPlayer((Player) this.sender);
    }

    public Console asConsole() {
        return new Console();
    }
}
