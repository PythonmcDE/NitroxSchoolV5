package xyz.daarkii.school.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.monster.DungeonMonster;
import xyz.daarkii.school.monster.Monster;
import xyz.daarkii.school.monster.MonsterDrops;

import java.util.ArrayList;

public class EntityDeathListener implements Listener {

    /**
     * Event called on entityDeath
     * @param e
     */
    @EventHandler
    public void onDeath(final EntityDeathEvent e){
        Entity entity = e.getEntity();
        DungeonMonster dungeonMonster = new DungeonMonster();
        if(e.getEntity().getKiller() != null) {
            if (dungeonMonster.getMonster(entity) != null) {

                // Entity drops and exp removed
                e.setDroppedExp(0);
                e.getDrops().clear();

                //New Items added
                Monster monster = dungeonMonster.getMonster(entity);
                MonsterDrops monsterDrops = new MonsterDrops(monster);
                ArrayList<ItemStack> itemStackArrayList = monsterDrops.getItemDrops();
                for (int i = 0; i < itemStackArrayList.size(); i++) {
                    if (itemStackArrayList != null)
                        if (itemStackArrayList.get(i) != null)
                            e.getDrops().add(itemStackArrayList.get(i));
                }

                //SchoolExp per killed Mob
                SchoolPlayer player = (SchoolPlayer) e.getEntity().getKiller();
                monsterDrops.addSchoolEXP(player);

                //TODO Stats per killed mob

            }
        }
    }

}
