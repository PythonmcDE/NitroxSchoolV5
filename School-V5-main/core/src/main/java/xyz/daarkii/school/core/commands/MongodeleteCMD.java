package xyz.daarkii.school.core.commands;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import xyz.daarkii.school.common.storage.MongoStorage;
import xyz.daarkii.school.core.SchoolCore;
import xyz.daarkii.school.core.command.Command;
import xyz.daarkii.school.core.entity.SchoolPlayer;
import xyz.daarkii.school.core.manager.MongoObjectManager;

import java.io.BufferedReader;
import java.util.List;

public class MongodeleteCMD extends Command {

    public MongodeleteCMD(){
        super("mongo", "School.admin.command.mongodel", "", List.of("mongodel"));
    }

    @Override
    public void execute(SchoolPlayer player, String[] args) {
        SchoolCore.getInstance().getMongoStorage().getCollection("player").deleteOne(new Document().append("uuid", player.getUUID()));
        Bukkit.broadcastMessage("Userdaten von " + player.getName() + " gelöscht!");
    }
}
