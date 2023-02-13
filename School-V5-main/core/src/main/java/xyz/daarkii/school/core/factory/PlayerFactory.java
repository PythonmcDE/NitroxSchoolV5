package xyz.daarkii.school.core.factory;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.MongoObjectManager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerFactory {

    private static final Map<UUID, SchoolPlayer> playerCache = new ConcurrentHashMap<>();

    @Setter
    private static MongoObjectManager mongoManager;

    public static SchoolPlayer getPlayer(@Nullable Player player) {
        return getPlayer(player, false);
    }

    public static SchoolPlayer getPlayer(@Nullable Player player, boolean allowCache) {

        if(player == null)
            return null;

        if(allowCache && playerCache.containsKey(player.getUniqueId()))
            return playerCache.get(player.getUniqueId());

        var properties = mongoManager.getPlayerProperties(player.getUniqueId());
        if(properties == null) {
            properties = createNewData(player);
        }

        var schoolPlayer = new SchoolPlayer(player, properties);

        playerCache.put(player.getUniqueId(), schoolPlayer);
        return schoolPlayer;
    }

    public static SchoolPlayer getPlayer(@NotNull UUID uuid) {
        return getPlayer(Bukkit.getPlayer(uuid));
    }

    public static SchoolPlayer getPlayer(@NotNull String name) {
        return getPlayer(Bukkit.getPlayer(name));
    }

    public static void removePlayer(Player player) {
        playerCache.remove(player.getUniqueId());
    }

    private static Document createNewData(Player player) {
        var properties = generateProperties(player);

        mongoManager.createPlayerProperties(properties);
        return properties;
    }

    private static Document generateProperties(Player player) {
        return new Document()
                .append("name", player.getName())
                .append("uuid", player.getUniqueId())
                .append("gems", 2000.0)
                .append("level", 1)
                .append("exp", 0.0)
                .append("mine", 1)
                .append("angel_mine", 1)
                .append("personal_bank_id", mongoManager.createBank())
                .append("prestige", 1)
                .append("benjamin", 0)
                .append("anton", 0)
                .append("farid",0)
                .append("helgar",0)
                .append("merlin",0)
                .append("peter", 0)
                .append("eddy", 0);
    }
}
