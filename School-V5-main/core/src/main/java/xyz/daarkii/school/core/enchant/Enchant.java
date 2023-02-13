package xyz.daarkii.school.core.enchant;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public abstract class Enchant<T> {

    /* static enchants */
    public static Enchant<EntityDamageEvent> test = builder()
            .setID("test")
            .setName("Test")
            .setTarget(TargetType.SWORD)
            .setEnchantType(EnchantType.CUSTOM)
            .setMaxLevel(10)
            .build(EntityDamageEvent.class, (event, level) -> {
                event.setCancelled(true);
            });

    private final String id;
    private final String pretty;

    private final Integer maxLevel;
    private final EnchantType enchantType;
    public final TargetType targetType;

    protected Enchant(String id, String pretty, Integer maxLevel, EnchantType enchantType, TargetType targetType) {
        this.id = id;
        this.pretty = pretty;
        this.maxLevel = maxLevel;
        this.enchantType = enchantType;
        this.targetType = targetType;
    }

    public abstract void trigger(T event, Integer level);

    public abstract boolean canAppliedTo(ItemStack item);

    public String id() {
        return id;
    }

    public String pretty() {
        return pretty;
    }

    public Integer maxLevel() {
        return maxLevel;
    }

    public EnchantType enchantType() {
        return enchantType;
    }

    public TargetType targetType() {
        return this.targetType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public enum EnchantType {

        VANILLA,
        CUSTOM
    }

    public enum TargetType {

        SWORD,
        BOW,
        PICKAXE,
        ROD,
        ARMOR,
        ALL
    }

    public static class Builder {

        private String id;
        private String pretty;

        private Integer maxLevel;
        private EnchantType enchantType;
        public  TargetType targetType;

        public Builder setID(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.pretty = name;
            return this;
        }

        public Builder setMaxLevel(Integer maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public Builder setEnchantType(EnchantType type) {
            this.enchantType = type;
            return this;
        }

        public Builder setTarget(TargetType type) {
            this.targetType = type;
            return this;
        }

        public <T> Enchant<T> build(Class<T> cls, BiConsumer<T, Integer> action) {

            if(id == null && pretty == null)
                throw new UnsupportedOperationException("An Enchant needs a name or an id!");

            if(id == null)
                id = pretty;

            if(pretty == null)
                pretty = id;

            if(maxLevel == null)
                maxLevel = 10;

            if(enchantType == null)
                enchantType = EnchantType.CUSTOM;

            if(targetType == null)
                targetType = TargetType.ALL;

            return new DefaultEnchant<>(id, pretty, maxLevel, enchantType, targetType, action);
        }
    }
}
