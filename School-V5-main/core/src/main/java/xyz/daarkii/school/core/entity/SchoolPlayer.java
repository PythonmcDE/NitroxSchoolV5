package xyz.daarkii.school.core.entity;

import com.github.stefvanschie.inventoryframework.gui.type.util.NamedGui;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.common.message.MessageLoader;
import xyz.daarkii.school.common.message.PlaceHolder;

import java.util.UUID;

public class SchoolPlayer implements BasePlayer {

    private final Player player;
    private final Document data;

    public SchoolPlayer(Player player, Document data) {
        this.player = player;
        this.data = data;
    }

    public void sendMessage(String key) {
        this.player.sendMessage(MessageLoader.loadComponent(key, this.getLanguage()));
    }

    public void sendMessage(String key, PlaceHolder... placeHolders) {
        this.player.sendMessage(MessageLoader.loadComponent(key, this.getLanguage(), placeHolders));
    }

    /* Bukkit API */

    public void showInventory(Inventory inventory) {
        this.player.openInventory(inventory);
    }

    public void showInventory(NamedGui gui) {
        gui.show(player);
    }

    public void openInventory(Inventory inventory) {
        this.showInventory(inventory);
    }

    public void openInventory(NamedGui gui) {
        gui.show(player);
    }

    public Inventory getInventory() {
        return this.player.getInventory();
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Document getData() {
        return this.data;
    }

    @Override
    public UUID getUUID() {
        return this.player.getUniqueId();
    }

    public Location getLocation(){
        return this.player.getLocation();
    }


    /* Implementation */

    @Override
    public String getName() {
        return this.data.getString("name");
    }

    @Override
    public String getDisplayName() {
        return this.getName();
    }

    @Override
    public String getLanguage() {
        return "de";
        /*var user = AysuServer.instance().user(this.getUUID());

        if(user == null) {
            System.out.println("USER IS NULL");
            return "de";
        }

        return user.language();*/
    }

    @Override
    public boolean hasCoop() {
        return false;
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch){
        player.playSound(location,sound,volume,pitch);
    }
}
