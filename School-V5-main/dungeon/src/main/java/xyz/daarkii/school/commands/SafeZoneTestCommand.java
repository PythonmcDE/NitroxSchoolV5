package xyz.daarkii.school.commands;

import org.bukkit.Bukkit;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.LocationManager;
import xyz.daarkii.school.manager.SafeZoneManager;


import java.io.BufferedReader;
import java.util.List;

public class SafeZoneTestCommand extends Command {


    public SafeZoneTestCommand() {
        super("testcmd", "school.admin.command.location", "", List.of("test"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        SafeZoneManager safeZoneManager = new SafeZoneManager(player.getPlayer());
            if (safeZoneManager.isInSafeZone())
                Bukkit.broadcastMessage("abcd in drin");
            else
                Bukkit.broadcastMessage("nicht drin");
    }
}
