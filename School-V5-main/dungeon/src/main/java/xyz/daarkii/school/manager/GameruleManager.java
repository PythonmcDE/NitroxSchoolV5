package xyz.daarkii.school.manager;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.block.data.type.BubbleColumn;

public class GameruleManager {

    /**
     * set all gamerules on server start
     */
    public GameruleManager(){
        for(World world: Bukkit.getWorlds()){
            world.setDifficulty(Difficulty.HARD);
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
        }
    }
}
