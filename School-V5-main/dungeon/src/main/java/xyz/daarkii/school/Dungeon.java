package xyz.daarkii.school;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import xyz.daarkii.school.commands.DungeonCommand;
import xyz.daarkii.school.commands.SafeZoneTestCommand;
import xyz.daarkii.school.events.EntityDamageByEntityListener;
import xyz.daarkii.school.events.EntityDeathListener;
import xyz.daarkii.school.events.EntityDamageListener;
import xyz.daarkii.school.manager.GameruleManager;
import xyz.daarkii.school.manager.MobSpawnManager;
import xyz.daarkii.school.core.SchoolCore;

public class Dungeon extends SchoolCore {

    @Override
    public void onEnable() {
        super.onEnable();

        new MobSpawnManager(20*30, false).startTimer();

        new GameruleManager();

        register(Bukkit.getPluginManager());

        System.out.println("Dungeon started");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("Dungeon stopped");
    }

    /**
     * Command register
     * / Event register
     * @param pm
     */
    private void register(PluginManager pm){
        this.commandManager.addCommand(new DungeonCommand());
        this.commandManager.addCommand(new SafeZoneTestCommand());

        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new EntityDamageByEntityListener(), this);
    }


    //TODO
    /*
    - Death message weg machen
    - Damage ändern
    - Life der mobs anpassen
    - Name anpassen





     */
}