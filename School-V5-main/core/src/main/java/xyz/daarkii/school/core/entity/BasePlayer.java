package xyz.daarkii.school.core.entity;

import org.bukkit.Location;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.bank.CoopBank;
import xyz.daarkii.school.core.bank.PersonalBank;

import java.util.UUID;

public interface BasePlayer {

    Document getData();

    UUID getUUID();

    Location getLocation();

    String getName();

    String getDisplayName();

    String getLanguage();

    double getGems();

    double getGemLimit();

    int getLevel();

    double getEXP();

    double getNeededEXP();

    int getMine();

    int getAngelMine();

    int getPrestige();

    boolean hasCoop();

    CoopBank getCoopBank();

    PersonalBank getBank();
}
