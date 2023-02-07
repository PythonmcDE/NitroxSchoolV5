package xyz.daarkii.school.common.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MessageWrapper {

    public static Component wrap(String msg) {
        return MiniMessage.miniMessage().deserialize(migrate(msg));
    }

    public static Component wrap(String msg, PlaceHolder... placeHolders) {
        return MiniMessage.miniMessage().deserialize(migrate(msg), Arrays.stream(placeHolders)
                .map(placeHolder -> Placeholder.component(placeHolder.name(), placeHolder.component())).toArray(TagResolver[]::new));
    }

    public static String unwrap(ComponentLike component) {
        return LegacyComponentSerializer.builder()
                .extractUrls()
                .build()
                .serialize(component.asComponent());
    }

    public static Component applyComponents(@NotNull ComponentLike componentLike, PlaceHolder... placeHolders) {

        Component component = componentLike.asComponent();

        for(PlaceHolder placeHolder : placeHolders) {
            component = component.replaceText(builder -> builder.match("{" + placeHolder.name() + "}").replacement(placeHolder.component()));
        }

        return component;
    }

    public static String migrate(String value) {

        String current = value;
        StringBuilder builder = new StringBuilder();

        /*
        Replace special characters
        */

        current = current
                .replace("&", "§")
                .replace("§l", "<replace_b>")
                .replace("§o", "<replace_i>")
                .replace("§n", "<replace_u>")
                .replace("§m", "<replace_st>")
                .replace("§r", "");
        ;

        String[] replaced = current.split("§");

        for(int i = 0; i < replaced.length; i++) {

            String line = replaced[i];

            if(i != 0)
                builder.append("§");

            builder.append(line);

            /*
            Add closing tags
             */

            if(line.contains("<replace_b>"))
                builder.append("</b>");

            if(line.contains("<replace_i>"))
                builder.append("</i>");

            if(line.contains("<replace_u>"))
                builder.append("</u>");

            if(line.contains("<replace_st>"))
                builder.append("</st>");
        }

        current = builder.toString();

        //format to the right tags
        current = current
                .replace("<replace_b>", "<b>")
                .replace("<replace_i>", "<i>")
                .replace("<replace_u>", "<u>")
                .replace("<replace_st>", "<st>");

        // Now all Special characters are replaced, so only colors needs to be replaced now
        current = current
                .replace("§0", "<c:black>")
                .replace("§1", "<c:dark_blue>")
                .replace("§2", "<c:dark_green>")
                .replace("§3", "<c:dark_aqua>")
                .replace("§4", "<c:dark_red>")
                .replace("§5", "<c:dark_purple>")
                .replace("§6", "<c:gold>")
                .replace("§7", "<c:gray>")
                .replace("§8", "<c:dark_gray>")
                .replace("§9", "<c:blue>")
                .replace("§a", "<c:green>")
                .replace("§b", "<c:aqua>")
                .replace("§c", "<c:red>")
                .replace("§d", "<c:light_purple>")
                .replace("§e", "<c:yellow>")
                .replace("§f", "<c:white>");

        return current;
    }

}
