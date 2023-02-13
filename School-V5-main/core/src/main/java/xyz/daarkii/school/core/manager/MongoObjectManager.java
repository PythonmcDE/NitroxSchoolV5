package xyz.daarkii.school.core.manager;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import xyz.daarkii.school.common.storage.MongoStorage;
import xyz.daarkii.school.common.utils.StringUtils;
import xyz.daarkii.school.core.bank.Bank;
import xyz.daarkii.school.core.bank.CoopBank;
import xyz.daarkii.school.core.bank.PersonalBank;
import xyz.daarkii.school.core.entity.BasePlayer;

import java.util.UUID;

public class MongoObjectManager {

    private final MongoStorage mongo;

    public MongoObjectManager(MongoStorage mongo) {
        this.mongo = mongo;
    }

    /* Bank */

    public CoopBank getBank(String id) {

        var document = this.mongo.getCollection("bank").find(new Document().append("id", id)).first();

        if(document == null)
            return null;

        return new CoopBank(MongoStorage.toDocument(document));
    }

    public PersonalBank getPersonalBank(BasePlayer player, String id) {

        var document = this.mongo.getCollection("bank").find(new Document().append("id", id)).first();

        if(document == null)
            return null;

        return new PersonalBank(MongoStorage.toDocument(document), player);
    }

    @NotNull
    public String createBank() {

        String id;

        do
            id = StringUtils.generate(16);
        while (this.existBank(id));

        var data = new Document()
                .append("id", id)
                .append("gems", 0)
                .append("level", Bank.Level.WOOD.toString());

        this.mongo.getCollection("bank").insertOne(data);
        return id;
    }

    public void updateBank(Bank bank) {
        this.mongo.getCollection("bank").replaceOne(new Document().append("id", bank.getID()), MongoStorage.toBson(bank.getData()));
    }

    public boolean existBank(String id) {
        return this.mongo.getCollection("bank").find(new Document().append("id", id)).limit(1).first() != null;
    }


    /* Player */

    public xyz.daarkii.school.common.document.Document getPlayerProperties(UUID uuid) {
        return MongoStorage.toDocument(this.mongo.getCollection("player").find(new Document().append("uuid", uuid)).first());
    }

    public void updatePlayerProperties(UUID uuid, xyz.daarkii.school.common.document.Document properties) {
        mongo.getCollection("player").replaceOne(new Document().append("uuid", uuid), MongoStorage.toBson(properties));
    }

    public void createPlayerProperties(xyz.daarkii.school.common.document.Document properties) {
        mongo.getCollection("player").insertOne(MongoStorage.toBson(properties));
    }
}
