package xyz.daarkii.school.common.utils;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import xyz.daarkii.school.common.document.Document;

@UtilityClass
public class NBTTags {

    public ItemStack addTag(ItemStack item, String key, String value) {
        return NBTEditor.set(item, value, key);
    }

    public ItemStack addTag(ItemStack item, String key, Document value) {
        return addTag(item, key, value.toJson());
    }

    public ItemStack addTag(ItemStack item, String key, Number value) {
        return addTag(item, key, value.toString());
    }

    public ItemStack addTag(ItemStack item, String key, boolean value) {
        return addTag(item, key, String.valueOf(value));
    }

    public String getString(ItemStack item, String key) {
        return NBTEditor.getString(item, key);
    }

    public Document getDocument(ItemStack item, String key) {
        var stringValue = getString(item, key);
        return stringValue != null ? new Document(stringValue) : null;
    }

    public Boolean getBoolean(ItemStack item, String key) {
        return NBTEditor.getBoolean(item, key);
    }

    public Integer getInt(ItemStack item, String key) {
        return NBTEditor.getInt(item, key);
    }

    public Double getDouble(ItemStack item, String key) {
        return NBTEditor.getDouble(item, key);
    }

    public Long getLong(ItemStack item, String key) {
        return NBTEditor.getLong(item, key);
    }
}
