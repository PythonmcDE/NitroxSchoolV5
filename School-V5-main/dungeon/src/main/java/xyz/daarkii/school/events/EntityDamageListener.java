package xyz.daarkii.school.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.daarkii.school.manager.SafeZoneManager;

public class EntityDamageListener implements Listener {


    @EventHandler
    public void onHit(EntityDamageEvent e){
        Entity entity = e.getEntity();
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            e.setCancelled(true);
        }
        if(entity instanceof Player) {
            SafeZoneManager safeZoneManager = new SafeZoneManager(entity);
            if(safeZoneManager.isInSafeZone()){
                e.setCancelled(true);
            }
        }
    }
}
