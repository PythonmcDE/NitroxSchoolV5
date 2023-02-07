package xyz.daarkii.school.core.entity;

import com.github.stefvanschie.inventoryframework.gui.type.util.InventoryBased;
import com.github.stefvanschie.inventoryframework.gui.type.util.NamedGui;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.common.message.MessageLoader;
import xyz.daarkii.school.common.message.PlaceHolder;
import xyz.daarkii.school.core.SchoolCore;
import xyz.daarkii.school.core.bank.CoopBank;
import xyz.daarkii.school.core.bank.PersonalBank;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

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

    @Override
    public Location getLocation(){
        return this.player.getLocation();
    }

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
    public double getGems() {
        return this.data.getDouble("gems");
    }

    @Override
    public double getGemLimit() {
        return 0; //TODO Register
    }

    @Override
    public int getLevel() {
        return this.data.getInt("level");
    }

    @Override
    public double getEXP() {
        return this.data.getDouble("exp");
    }

    @Override
    public double getNeededEXP() {
        return 0; //TODO Register
    }

    @Override
    public int getMine() {
        return this.data.getInt("mine");
    }

    @Override
    public int getAngelMine() {
        return this.data.getInt("angel_mine");
    }

    @Override
    public int getPrestige() {
        return this.data.getInt("prestige");
    }

    @Override
    public boolean hasCoop() {
        return false;
    }

    @Override
    public CoopBank getCoopBank() {
        var bankID = this.data.contains("coop_bank_id") ? this.data.getString("coop_bank_id") : this.data.getString("personal_bank_id");

        return SchoolCore.getInstance().getMongoManager().getBank(bankID);
    }

    @Override
    public PersonalBank getBank() {
        return SchoolCore.getInstance().getMongoManager().getPersonalBank(this, this.data.getString("personal_bank_id"));
    }
}
