package xyz.daarkii.school.core.console;

import org.bukkit.Bukkit;
import xyz.daarkii.school.core.command.sender.Sender;

public class Console extends Sender {

    public Console() {
        super(Bukkit.getConsoleSender());
    }
}
