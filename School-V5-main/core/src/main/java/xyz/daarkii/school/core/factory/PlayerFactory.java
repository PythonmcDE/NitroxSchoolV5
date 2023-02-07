package xyz.daarkii.school.core.factory;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.MongoObjectManager;

import java.util.UUID;

public class PlayerFactory {

    @Setter
    private static MongoObjectManager mongoManager;

    public static SchoolPlayer getPlayer(@Nullable Player player) {

        if(player == null)
            return null;

        var properties = mongoManager.getPlayerProperties(player.getUniqueId());

        if(properties == null)
            properties = createNewData(player);

        return new SchoolPlayer(player, properties);
    }

    public static SchoolPlayer getPlayer(@NotNull UUID uuid) {
        return getPlayer(Bukkit.getPlayer(uuid));
    }

    public static SchoolPlayer getPlayer(@NotNull String name) {
        return getPlayer(Bukkit.getPlayer(name));
    }

    private static Document createNewData(Player player) {
        var properties = generateProperties(player);

        mongoManager.updatePlayerProperties(player.getUniqueId(), properties);
        return properties;
    }

    private static Document generateProperties(Player player) {
        return new Document()
                .append("name", player.getName())
                .append("uuid", player.getUniqueId())
                .append("gems", 1000.0)
                .append("level", 1)
                .append("exp", 0.0)
                .append("mine", 1)
                .append("angel_mine", 1)
                .append("prestige", 1);
    }
}
