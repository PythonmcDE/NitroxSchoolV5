package xyz.daarkii.school.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class DungeonMonster {

    private final byte dungeon;
    private ArrayList<Location> loc;
    private final World world;

    public DungeonMonster(byte dungeon, ArrayList<Location> locations, World world) {
        this.dungeon = dungeon;
        this.loc = locations;
        this.world = world;
    }

    public void spawnDungeonMonster(Monster monster){
        MonsterEquipment equipment = new MonsterEquipment();

        int random = new Random().nextInt(loc.size());

        LivingEntity entity = (LivingEntity) world.spawnEntity(loc.get(random),getType(monster));
        equipment.equip(monster, entity);

    }

    public Monster randomMobByDungeoen(byte dungeon) {
        int r = new Random().nextInt(3);
        switch (dungeon) {
            case 1:
                //Mobs of dungeon 1
                if (r == 0) {
                    return Monster.Zombie;
                } else if (r == 1) {
                    return Monster.Skelett;
                } else {
                    return Monster.Spinne;
                }
            case 2:
                //Mobs of dungeon 2
                if (r == 0) {
                    return Monster.Zombie_2;
                } else if (r == 1) {
                    return Monster.Skelett_2;
                } else {
                    return Monster.Hexe;
                }
            case 3:
                //Mobs of dungeon 3
                if (r == 0) {
                    return Monster.Witherskelett;
                } else if (r == 1) {
                    return Monster.ZombiePig;
                } else {
                    return Monster.Blaze;
                }
            default:
                break;

        }
        return Monster.Zombie;
    }
    // gettype of a monster enum
    private EntityType getType(Monster monster){
        switch (monster){
            case Zombie:
            case Zombie_2:
                return EntityType.ZOMBIE;
            case Skelett:
            case Skelett_2: return EntityType.SKELETON;
            case Spinne: return EntityType.SPIDER;
            case Hexe: return EntityType.WITCH;
            case Witherskelett: return EntityType.WITHER_SKELETON;
            case ZombiePig: return EntityType.ZOMBIFIED_PIGLIN;
            case Blaze: return EntityType.BLAZE;
            default: return EntityType.VILLAGER;
        }
    }
}
