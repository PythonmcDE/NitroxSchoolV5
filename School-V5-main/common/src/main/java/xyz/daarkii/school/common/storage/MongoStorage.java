package xyz.daarkii.school.common.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.school.common.document.DocumentUtil;

public class MongoStorage {

    private final String url;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public MongoStorage(String url, String database) {
        this.url = url;
        this.connect(database);
    }

    public void connect(String database) {

        if(mongoClient == null)
            mongoClient = MongoClients.create(this.url);

        this.mongoDatabase = this.mongoClient.getDatabase(database);
    }

    public void disconnect() {

        if(mongoClient != null)
            mongoClient.close();
    }

    public MongoCollection<Document> getCollection(String name) {
        return this.mongoDatabase.getCollection(name);
    }

    public static Document toBson(xyz.daarkii.school.common.document.Document document) {
        return Document.parse(document.toJson());
    }

    public static xyz.daarkii.school.common.document.Document toDocument(@Nullable Document bson) {

        if(bson == null)
            return null;

        return DocumentUtil.fromJson(bson.toJson());
    }
}
