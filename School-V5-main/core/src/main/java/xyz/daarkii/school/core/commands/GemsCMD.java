package xyz.daarkii.school.core.commands;

import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.common.utils.StringUtils;

import java.util.List;

public class GemsCMD extends Command {

    public GemsCMD() {
        super("gems", "school.command.gems", "", List.of("money", "g"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        player.sendMessage("school.command.gems", new PlaceHolder("gems", StringUtils.formatGems(player.getGems())));
    }
}
