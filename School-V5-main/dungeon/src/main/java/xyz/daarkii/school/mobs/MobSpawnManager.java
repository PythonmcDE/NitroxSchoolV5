package xyz.daarkii.school.mobs;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.daarkii.school.core.SchoolCore;

public class MobSpawnManager {

    private long spawnrate;
    private boolean shouldspawn;
    private int spawnamount;

    public MobSpawnManager(long spawnrate, boolean shouldspawn){
        this.spawnrate = spawnrate;
        this.shouldspawn = shouldspawn;
    }

    public void startTimer(){
        new BukkitRunnable(){

            @Override
            public void run() {
                if(shouldSpawn()){
                    for(int i = 1; i<= getSpawnAmount(); i++){
                        //TODO Dungeon des spielers geben
                        byte dungeon = 1;
                        MonsterLocations monsterLocations = new MonsterLocations();
                        DungeonMonster monster = new DungeonMonster(dungeon, monsterLocations.getAllLocations(dungeon),monsterLocations.getAllLocations(dungeon).get(0).getWorld());
                        monster.spawnDungeonMonster(monster.randomMobByDungeoen(dungeon));
                    }
                }
            }
        }.runTaskTimer(SchoolCore.getInstance(), spawnrate, spawnrate);
    }

    private int getSpawnAmount(){
        if(Bukkit.getOnlinePlayers().size() >= 1)
            spawnamount = Bukkit.getOnlinePlayers().size()*8;
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
