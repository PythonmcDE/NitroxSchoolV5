package xyz.daarkii.school.core;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.daarkii.school.common.collection.Config;
import xyz.daarkii.school.common.storage.MySQLStorage;
import xyz.daarkii.school.core.command.CommandManager;
import xyz.daarkii.school.core.commands.BankCMD;
import xyz.daarkii.school.core.commands.ExpCMD;
import xyz.daarkii.school.core.commands.GemsCMD;
import xyz.daarkii.school.core.factory.PlayerFactory;
import xyz.daarkii.school.common.storage.MongoStorage;
import xyz.daarkii.school.core.manager.MongoObjectManager;

import java.io.File;

public class SchoolCore extends JavaPlugin {

    @Getter
    private static SchoolCore instance;

    private CommandManager commandManager;

    @Getter
    private MongoObjectManager mongoManager;

    @Getter
    private MySQLStorage mySQL;

    @Getter
    private Config customConfig;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;

        this.customConfig = new Config(new File(this.getDataFolder(), "config.json"), "config.json");

        this.mySQL = new MySQLStorage(customConfig);
        this.mongoManager = new MongoObjectManager(new MongoStorage("mongodb://" + customConfig.getString("mongo.user") + ":" + customConfig.getString("mongo.password") + "@" +customConfig.getString("mongo.host") + ":" + customConfig.getString("mongo.port") + "/?maxPoolSize=20&w=majority", customConfig.getString("mongo.database")));

        this.commandManager = new CommandManager();
        this.registerCommands();

        PlayerFactory.setMongoManager(this.mongoManager);
    }

    private void registerCommands() {

        this.commandManager.addCommand(new BankCMD());
        this.commandManager.addCommand(new ExpCMD());
        this.commandManager.addCommand(new GemsCMD());
    }
}
