package xyz.daarkii.school.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {


    /**
     * if Player hits antother player (should be cancelled)
     * @param e
     */
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            e.setCancelled(true);
        }
    }
}
