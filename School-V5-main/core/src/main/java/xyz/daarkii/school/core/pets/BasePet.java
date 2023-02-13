package xyz.daarkii.school.core.pets;

import org.bukkit.Bukkit;
import xyz.daarkii.school.common.document.Document;
import xyz.daarkii.school.core.SchoolCore;

import java.nio.Buffer;
import java.util.UUID;

public interface BasePet {


    default void update() {
        SchoolCore.getInstance().getMongoManager().updatePlayerProperties(this.getUUID(), this.getData());
    }
    Document getData();

    UUID getUUID();
    /**
     * // own == 1 -> the user owns the pet
     * // own == 0 -> the user don't own the pet
     * @param pet
     * @param own
     */
    default void setPet(String pet, byte own) {
        this.getData().append(pet, own);
        this.update();
    }

    default int getPet(String pet) {
        return this.getData().getInt(pet);
    }


}
