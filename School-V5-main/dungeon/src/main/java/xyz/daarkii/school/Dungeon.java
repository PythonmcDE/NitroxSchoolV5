package xyz.daarkii.school;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import xyz.daarkii.school.commands.LocationCommand;
import xyz.daarkii.school.mobs.MobSpawnManager;
import xyz.daarkii.school.core.SchoolCore;

import java.io.BufferedReader;

public class Dungeon extends SchoolCore {

    @Override
    public void onEnable() {
        super.onEnable();

        MobSpawnManager mobSpawnManager = new MobSpawnManager(20*30, false);
        mobSpawnManager.startTimer();

        System.out.println("Dungeon started");

        register(Bukkit.getPluginManager());

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
        getCommand("loc").setExecutor(new LocationCommand());
    }
}