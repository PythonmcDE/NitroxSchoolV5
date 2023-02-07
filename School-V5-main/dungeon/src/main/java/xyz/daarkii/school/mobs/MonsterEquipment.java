package xyz.daarkii.school.mobs;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import xyz.daarkii.school.common.utils.ItemBuilder;
import xyz.daarkii.school.core.SchoolCore;

public class MonsterEquipment {

    public MonsterEquipment(){
    }

    /*
            case Zombie:
                entity.setCustomName("§fZombie §8(§7Level §61§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(30);
                entity.setHealth(30);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.IRON_SWORD).build());
                break;

             */

    //method to set itemtype and add armor oder name, etc.
    public void equip(Monster monster, LivingEntity entity){
        switch (monster){
            case Zombie:
                entity.setCustomName("§fZombie §8(§7Level §61§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(30);
                entity.setHealth(30);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.IRON_SWORD).build());
                break;
            case Zombie_2:
                entity.setCustomName("§fZombie §8(§7Level §62§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(90);
                entity.setHealth(90);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 4, false).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false).build());
                break;
            case Skelett:
                entity.setCustomName("§fSkeleton §8(§7Level §61§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(30);
                entity.setHealth(30);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.IRON_SWORD).build());
                break;

            case Skelett_2:
                entity.setCustomName("§fSkeleton §8(§7Level §62§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(90);
                entity.setHealth(90);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5, false).build());
                break;
            case Spinne:
                entity.setCustomName("§fSpider §8(§7Level §61§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(30);
                entity.setHealth(30);
                break;
            case Hexe:
                entity.setCustomName("§fWitch §8(§7Level §65§8)");
                entity.setMaxHealth(120);
                entity.setHealth(120);
                entity.setCustomNameVisible(true);

                break;
            case Witherskelett: ;
                entity.setCustomName("§fWitherskeleton §8(§7Level §610§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(140);
                entity.setHealth(140);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.STONE_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 6, true).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false).build());
                break;
            case ZombiePig:
                entity.setCustomName("§fZombie-Pigman §8(§7Level §610§8)");
                entity.setCustomNameVisible(true);
                entity.setMaxHealth(140);
                entity.setHealth(140);
                entity.getEquipment().setHelmet(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setBoots(new ItemBuilder(Material.AIR).build());
                entity.getEquipment().setItemInHand(new ItemBuilder(Material.GOLDEN_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 6, true).build());
                entity.getEquipment().setChestplate(new ItemBuilder(Material.GOLDEN_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false).build());
                entity.getEquipment().setLeggings(new ItemBuilder(Material.GOLDEN_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false).build());
                break;
            case Blaze:
                entity.setCustomName("§fBlaze §8(§7Level §610§8)");
                entity.setMaxHealth(160);
                entity.setHealth(160);
                entity.setCustomNameVisible(true);

                break;


        }
    }


}