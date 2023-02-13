package xyz.daarkii.school.core.pets;

import lombok.Setter;
import org.bukkit.Bukkit;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.SchoolCore;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.MongoObjectManager;

import java.nio.Buffer;
import java.util.UUID;

public class SchoolPet implements BasePet {

    private final Pet pettype;
    private final SchoolPlayer owner;
    private final Document data;

    public SchoolPet(SchoolPlayer owner, Pet pettype){
        this.pettype = pettype;
        this.owner = owner;
        this.data = SchoolCore.getInstance().getMongoManager().getPlayerProperties(owner.getUUID());
    }

    public String getPetString(){
        switch (pettype){
            case EDDY: return "eddy";
            case ANTON: return "anton";
            case FARID: return "farid";
            case PETER: return "peter";
            case HELGAR: return "helgar";
            case MERLIN: return "merlin";
            case BENJAMIN: return "benjamin";
            default: return null;
        }
    }

    public boolean hasPet(){
        if(getPet(getPetString()) == 1)
            return true;
        else
            return false;
    }

    public void setPetOwned(){
        setPet(getPetString(), (byte) 1);
    }

    @Override
    public Document getData() {
        return this.data;
    }

    @Override
    public UUID getUUID() {
        return this.owner.getPlayer().getUniqueId();
    }

    public enum Pet {

        BENJAMIN,
        ANTON,
        FARID,
        HELGAR,
        PETER,
        MERLIN,
        EDDY;
    }
}
