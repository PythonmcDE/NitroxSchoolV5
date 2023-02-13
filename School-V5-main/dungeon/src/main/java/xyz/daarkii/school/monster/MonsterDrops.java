package xyz.daarkii.school.monster;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.daarkii.school.common.utils.ItemBuilder;
import xyz.daarkii.school.core.entity.SchoolPlayer;

import java.util.ArrayList;

public class MonsterDrops {

    private final Monster monster;

    public MonsterDrops(Monster monster){
        this.monster = monster;
    }

    /**
     * @return Arraylist of items which will be dropped by killig a mob
     */
    public ArrayList<ItemStack> getItemDrops(){
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        switch (monster){
            case Zombie:
                //Add items in here
                itemStacks.add(new ItemBuilder(Material.STONE).setDisplayName("abcdef").build());
                break;
            case Skelett:
                itemStacks.add(new ItemBuilder(Material.STONE_AXE).setDisplayName("nbuihfeu").build());
                itemStacks.add(new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("nbuihfeu").build());
                break;
            case Zombie_2:
                break;
            case Skelett_2:
                break;
            case Hexe:
                break;
            case Spinne:
                break;
            case Witherskelett:
                break;
            case Blaze:
                break;
            case ZombiePig:
                break;
            default:
                break;

            //TODO add all items to all mobs from #Monster
        }

        return itemStacks;
    }

    public void addSchoolEXP(SchoolPlayer player){
        switch (monster){
            case Zombie:
                //TODO Add exp in here
                break;
            case Skelett:
                break;
            case Zombie_2:
                break;
            case Skelett_2:
                break;
            case Hexe:
                break;
            case Spinne:
                break;
            case Witherskelett:
                break;
            case Blaze:
                break;
            case ZombiePig:
                break;
            default:
                break;
        }
    }

}
