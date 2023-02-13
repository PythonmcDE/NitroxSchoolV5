package xyz.daarkii.school.core.commands;

import org.bukkit.GameMode;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.common.utils.StringUtils;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;

import java.util.List;

public class GamemodeCMD extends Command {

    public GamemodeCMD() {
        super("gm", "school.admin.command.gm", "", List.of("gamemode"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("1")){
                player.getPlayer().setGameMode(GameMode.CREATIVE);
            }else if(args[0].equalsIgnoreCase("0")){
                player.getPlayer().setGameMode(GameMode.SURVIVAL);
            }else if(args[0].equalsIgnoreCase("2")){
                player.getPlayer().setGameMode(GameMode.ADVENTURE);
            }else if(args[0].equalsIgnoreCase("3")){
                player.getPlayer().setGameMode(GameMode.SPECTATOR);
            }
            player.sendMessage("school.admin.command.gm.changed");
        }else
            player.sendMessage("school.admin.command.gm.error");
    }
}