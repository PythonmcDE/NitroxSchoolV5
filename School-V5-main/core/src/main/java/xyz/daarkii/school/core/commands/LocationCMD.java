package xyz.daarkii.school.core.commands;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.LocationManager;

import java.util.List;

public class LocationCMD extends Command {

    public LocationCMD() {
        super("loc", "school.admin.command.location", "", List.of("locs", "location"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("set")){
                LocationManager locationManager = new LocationManager();
                locationManager.addLocation(args[1], player.getLocation());
                player.sendMessage("school.admin.location.set");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
                return;
            }else if(args[0].equalsIgnoreCase("teleport")){
                LocationManager locationManager = new LocationManager();
                if(locationManager.locationexists(args[1])){
                    player.getPlayer().teleport(locationManager.getLocation(args[1]));
                    player.sendMessage("School.admin.command.location.teleport.success");
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
                    return;
                }else {
                    player.sendMessage("school.admin.command.location.teleport.failed");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1F, 1F);

                }
                return;
            }
        }
        player.sendMessage("school.admin.location.error");
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1F, 1F);
    }
}