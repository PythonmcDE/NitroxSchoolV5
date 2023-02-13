package xyz.daarkii.school.core.enchant;

import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class DefaultEnchant<T> extends Enchant<T> {

    private final BiConsumer<T, Integer> action;

    public DefaultEnchant(String id, String pretty, Integer maxLevel, EnchantType enchantType, TargetType targetType, BiConsumer<T, Integer> action) {
        super(id, pretty, maxLevel, enchantType, targetType);
        this.action = action;
    }

    @Override
    public void trigger(T event, Integer level) {
        this.action.accept(event, level);
    }

    @Override
    public boolean canAppliedTo(ItemStack item) {
        return false; //TODO
    }
}
