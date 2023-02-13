package xyz.daarkii.school.manager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.daarkii.school.core.SchoolCore;
import xyz.daarkii.school.monster.DungeonMonster;
import xyz.daarkii.school.monster.MonsterLocations;

public class MobSpawnManager {

    private long spawnrate;
    private boolean shouldspawn;
    private int spawnamount;

    public MobSpawnManager(long spawnrate, boolean shouldspawn){
        this.spawnrate = spawnrate;
        this.shouldspawn = shouldspawn;
    }

    /**
     * Timer that spawns mobs ever spawnrate intervals
     */
    public void startTimer(){
        MonsterLocations monsterLocations = new MonsterLocations();
        new BukkitRunnable(){
            byte j = 1;
            DungeonMonster monster = new DungeonMonster(j, monsterLocations
                    .getAllLocations(j),monsterLocations.getAllLocations(j).get(0).getWorld());
            @Override
            public void run() {
                if(shouldSpawn()){

                        for (int i = 1; i < getSpawnAmount(); i++) {
                            monster.spawnDungeonMonster(monster.randomMobByDungeoen(j));
                        }
                }
            }
        }.runTaskTimer(SchoolCore.getInstance(), spawnrate, spawnrate);

        new BukkitRunnable(){
            byte j = 2;
            DungeonMonster monster = new DungeonMonster(j, monsterLocations
                    .getAllLocations(j),monsterLocations.getAllLocations(j).get(0).getWorld());
            @Override
            public void run() {
                if(shouldSpawn()){

                    for (int i = 1; i < getSpawnAmount(); i++) {
                        monster.spawnDungeonMonster(monster.randomMobByDungeoen(j));
                    }
                }
            }
        }.runTaskTimer(SchoolCore.getInstance(), spawnrate+60, spawnrate+60);

        new BukkitRunnable(){
            byte j = 3;
            DungeonMonster monster = new DungeonMonster(j, monsterLocations
                    .getAllLocations(j),monsterLocations.getAllLocations(j).get(0).getWorld());
            @Override
            public void run() {
                if(shouldSpawn()){

                    for (int i = 1; i < getSpawnAmount(); i++) {
                        monster.spawnDungeonMonster(monster.randomMobByDungeoen(j));
                    }
                }
            }
        }.runTaskTimer(SchoolCore.getInstance(), spawnrate+120, spawnrate+120);
    }

    /**
     * @return the amount of mobs per intervals
     */
    private int getSpawnAmount(){
        if(Bukkit.getOnlinePlayers().size() >= 1)
            spawnamount = Bukkit.getOnlinePlayers().size()*4;
        else
            spawnamount = 0;
        return spawnamount;
    }
    private boolean shouldSpawn(){
        if(Bukkit.getOnlinePlayers().size() >= 1)
            shouldspawn = true;
        else
            shouldspawn = false;
        return shouldspawn;
    }

}
