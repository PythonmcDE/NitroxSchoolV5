package xyz.daarkii.school.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.factory.PlayerFactory;
import xyz.daarkii.school.core.manager.LocationManager;
import xyz.daarkii.school.mobs.MonsterLocations;

public class LocationCommand implements CommandExecutor {


    /**
     * Tets command to set a location (not finished!!!)
     *
     *
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            if(sender.hasPermission("*")){
                LocationManager locationManager = new LocationManager();
                MonsterLocations monsterLocations = new MonsterLocations();
                SchoolPlayer player = PlayerFactory.getPlayer((Player)sender);
                locationManager.addLocation("dungeon" + Integer.parseInt(args[0]) + "point" + (monsterLocations.getAllLocations((byte) Integer.parseInt(args[0])).size() +1), player.getLocation());
                player.sendMessage("school.admin.location.set");
            }
        }
        return false;
    }
}
