package xyz.daarkii.school.core.entity;

import org.bukkit.Location;
import org.bukkit.Sound;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.SchoolCore;
import xyz.daarkii.school.core.bank.CoopBank;
import xyz.daarkii.school.core.bank.PersonalBank;

import java.util.UUID;

public interface BasePlayer {

    default void update() {
        SchoolCore.getInstance().getMongoManager().updatePlayerProperties(this.getUUID(), this.getData());
    }

    Document getData();

    UUID getUUID();

    String getName();

    String getDisplayName();

    String getLanguage();

    default void addGems(double gems) {
        this.setGems(this.getGems() + gems);
    }

    default void removeGems(double gems) {
        this.setGems(this.getGems() - gems > 0 ? this.getGems() - gems : 0);
    }

    default void setGems(double gems) {
        this.getData().append("gems", gems);
        this.update();
    }

    default double getGems() {
        return this.getData().getDouble("gems");
    }

    default double getGemLimit() {
        return 0; //TODO Register
    }

    default int getLevel() {
        return 1; //TODO Register
    }

    default double getEXP() {
        return this.getData().getDouble("exp");
    }

    default double getNeededEXP() {
        return 0; //TODO Register
    }

    default int getMine() {
        return this.getData().getInt("mine");
    }

    default int getAngelMine() {
        return this.getData().getInt("angel_mine");
    }

    default int getPrestige() {
        return this.getData().getInt("prestige");
    }

    default int getDungeon() {
        return this.getPrestige() == 3 ? 3 : this.getPrestige() + 1;
    }

    boolean hasCoop();

    default CoopBank getCoopBank() {
        var bankID = this.getData().contains("coop_bank_id") ? this.getData().getString("coop_bank_id") : this.getData().getString("personal_bank_id");
        return SchoolCore.getInstance().getMongoManager().getBank(bankID);
    }

    default PersonalBank getBank() {
        return SchoolCore.getInstance().getMongoManager().getPersonalBank(this, this.getData().getString("personal_bank_id"));
    }

    void playSound(Location location, Sound sound, float volume, float pitch);
}
