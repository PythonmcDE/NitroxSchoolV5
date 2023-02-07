package xyz.daarkii.school.core.commands;

import tv.aysu.api.lib.collection.Pair;
import tv.aysu.api.lib.util.WebUtil;
import xyz.daarkii.school.common.message.MessageLoader;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.common.utils.StringUtils;

import java.util.List;

public class ExpCMD extends Command {

    public ExpCMD() {
        super("exp", "school.command.exp", "", List.of("xp"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {

        System.out.println("Before");
        System.out.println(WebUtil.startPostRequest("https://api.aysu.tv/multilanguage/", true, new Pair<>("MESSAGE", "school.command.exp"), new Pair<>("LANGUAGE", "de")));
        System.out.println(player.getLanguage());

        System.out.println(tv.aysu.api.lib.message.MessageLoader.loadMessage("school.command.exp", player.getLanguage()));
        System.out.println(MessageLoader.loadMessage("school.command.exp", player.getLanguage()));
        System.out.println("After");

        player.sendMessage("school.command.exp",
                new PlaceHolder("exp", StringUtils.format(player.getEXP())),
                new PlaceHolder("needed_exp", StringUtils.format(player.getNeededEXP())));
    }

}
