package xyz.daarkii.school.core;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.daarkii.school.common.collection.Config;
import xyz.daarkii.school.common.storage.MySQLStorage;
import xyz.daarkii.school.core.command.CommandManager;
import xyz.daarkii.school.core.commands.*;
import xyz.daarkii.school.core.factory.PlayerFactory;
import xyz.daarkii.school.common.storage.MongoStorage;
import xyz.daarkii.school.core.manager.MongoObjectManager;
import xyz.daarkii.school.core.pets.SchoolPet;

import java.io.File;

public class SchoolCore extends JavaPlugin {

    @Getter
    private static SchoolCore instance;

    protected CommandManager commandManager;

    @Getter
    protected MongoObjectManager mongoManager;

    @Getter
    protected MongoStorage mongoStorage;

    @Getter
    protected MySQLStorage mySQL;

    @Getter
    protected Config customConfig;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;

        this.customConfig = new Config(new File(this.getDataFolder(), "config.json"), "config.json");

        this.mySQL = new MySQLStorage(customConfig);
        this.mongoStorage = new MongoStorage("mongodb://" + customConfig.getString("mongo.user") + ":" + customConfig.getString("mongo.password") + "@" +customConfig.getString("mongo.host") + ":" + customConfig.getString("mongo.port") + "/?maxPoolSize=20&w=majority", customConfig.getString("mongo.database"));
        this.mongoManager = new MongoObjectManager(this.mongoStorage);

        this.commandManager = new CommandManager();
        this.registerCommands();

        PlayerFactory.setMongoManager(this.mongoManager);
    }

    private void registerCommands() {

        this.commandManager.addCommand(new BankCMD());
        this.commandManager.addCommand(new ExpCMD());
        this.commandManager.addCommand(new GemsCMD());
        this.commandManager.addCommand(new GamemodeCMD());
        this.commandManager.addCommand(new LocationCMD());
        this.commandManager.addCommand(new PetCMD());
        this.commandManager.addCommand(new MongodeleteCMD());
    }
}
