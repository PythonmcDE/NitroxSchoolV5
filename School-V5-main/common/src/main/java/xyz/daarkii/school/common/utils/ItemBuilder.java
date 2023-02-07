package xyz.daarkii.school.common.utils;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.daarkii.school.common.message.MessageWrapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    @Deprecated
    public ItemBuilder(Material material, short data) {
        this.itemStack = new ItemStack(material,1, data);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * Migrates the displayName to an adventure component and sets the name of the item
     *
     * @param displayName for the item
     * @return the current builder
     */
    public ItemBuilder setDisplayName(String displayName) {
        return this.setDisplayName(MessageWrapper.wrap(displayName));
    }

    /**
     * Sets the name of the item
     *
     * @param displayName component for the item
     * @return the current builder
     */
    public ItemBuilder setDisplayName(Component displayName) {
        this.itemMeta.displayName(displayName);
        return this;
    }

    /**
     * Adds an enchant to the current enchants
     *
     * @param enchant to add
     * @param level of the enchantment
     * @param b Whether this level can be reached in Vanilla
     * @return the current builder
     */
    public ItemBuilder addEnchant(Enchantment enchant, int level, boolean b) {
        this.itemMeta.addEnchant(enchant, level, b);
        return this;
    }

    /**
     * Sets the amount of this ItemStack
     *
     * @param amount which the stack should have
     * @return the current builder
     */
    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * @deprecated since 1.19 upgrade
     * <p>
     * Sets the durability of the item
     *
     * @param durability which should be subtracted from the maximum value
     * @return the current builder
     */
    @Deprecated
    public ItemBuilder setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    /**
     * Sets the durability of the item
     *
     * @param durability which should be subtracted from the maximum value
     * @return the current builder
     */
    public ItemBuilder setDurability(int durability) {

        var damageable = (Damageable) itemMeta;

        damageable.setDamage(durability);

        this.itemMeta = damageable;
        return this;
    }

    /*
    Legacy lore:
     */

    /**
     * Migrates the String lore to adventure components and sets the lore for the item, old lines are maintained
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder addLegacyLore(List<String> lore) {

        List<String> newLore = this.itemMeta != null && this.itemMeta.lore() != null ? this.itemStack.getItemMeta().getLore() : new LinkedList<>();

        if(newLore != null)
            newLore.addAll(lore);

        return this.setLegacyLore(newLore);
    }

    /**
     * Migrates the String lore to adventure components and sets the lore for the item, old lines are maintained
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder addLegacyLore(String... lore) {
        return this.addLegacyLore(Arrays.asList(lore));
    }

    /**
     * Migrates the String lore to adventure components and sets the lore for the item, old lines are overwritten
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder setLegacyLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Migrates the String lore to adventure components and sets the lore for the item, old lines are overwritten
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder setLegacyLore(String... lore) {
        return this.setLegacyLore(Arrays.asList(lore));
    }

    /*
    Adventure lore:
     */

    /**
     * Sets the lore for the item, old lines are maintained
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder addLore(List<Component> lore) {

        List<Component> newLore = this.itemMeta != null && this.itemMeta.lore() != null ? this.itemStack.getItemMeta().lore() : new LinkedList<>();

        if(newLore != null)
            newLore.addAll(lore);

        return this.setLore(newLore);
    }

    /**
     * Sets the lore for the item, old lines are maintained
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder addLore(Component... lore) {
        return addLore(List.of(lore));
    }

    /**
     * Sets the lore for the item, old lines are overwritten
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder setLore(List<Component> lore) {
        this.itemMeta.lore(lore);
        return this;
    }

    /**
     * Sets the lore for the item, old lines are overwritten
     *
     * @param lore which the item should have
     * @return the current builder
     */
    public ItemBuilder setLore(Component... lore) {
        return setLore(List.of(lore));
    }

    /**
     * Adds a Flag to the item
     *
     * @param flag which the item should hava
     * @return the current builder
     */
    public ItemBuilder addItemFlag(ItemFlag flag){
        this.itemMeta.addItemFlags(flag);
        return this;
    }

    /**
     * Sets the unbreakable tag
     *
     * @param b whether the item can be destroyed
     * @return the current builder
     */
    public ItemBuilder setUnbreakable(boolean b){
        this.itemMeta.setUnbreakable(b);
        return this;
    }

    /**
     * Adds a Nbt tag to the current itemStack
     *
     * @param key the nbt key
     * @param value the nbt value
     * @return the current builder
     */
    public ItemBuilder addNBTTag(String key, String value) {
        NBTEditor.set(this.itemStack, value, key);
        return this;
    }

    /**
     * Migrates the current builder to an ItemStack
     *
     * @return the final itemStack
     */
    @NotNull
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

    /**
     * Migrates the current builder to an ItemStack with the given Nbt tag
     *
     * @param nbt sets the nbt tag value for the <b>invsafeid</b> key
     * @return the final itemStack
     */
    @NotNull
    public ItemStack build(String nbt) {
        this.itemStack.setItemMeta(this.itemMeta);

        NBTEditor.set(this.itemStack, nbt, "invsafeid");
        return this.itemStack;
    }

    /**
     * Migrates the current builder to an ItemStack with the given Nbt tag
     *
     * @param key the nbt tag key
     * @param value the nbt tag value
     * @return the final itemStack
     */
    @NotNull
    public ItemStack build(String key, Object value) {
        this.itemStack.setItemMeta(this.itemMeta);

        NBTEditor.set(this.itemStack, value, key);
        return this.itemStack;
    }

    /**
     * Migrates the current builder to a GuiItem of the IF framework
     * These items are used for inventories only!
     *
     * @return the wrapped gui item
     */
    @NotNull
    public GuiItem buildGuiItem() {
        return new GuiItem(this.build());
    }

    /**
     * Converts the number to the normal used Enchant levels
     *
     * @param value the level of the enchantment
     * @return the number as enchantment String
     */
    @NotNull
    private String convertToString(int value) {
        return switch (value) {
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> "II";
        };
    }

}
