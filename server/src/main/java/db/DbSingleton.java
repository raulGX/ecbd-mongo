package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public final class DbSingleton {
    private static volatile DbSingleton instance = null;
    private static volatile MongoClient mClient;
    private static final String DbName = "ecbd";
    private static final String UserCollection = "user";
    private static final String ArticleCollection = "article";
    private static final String IndirectIndexCollection = "indirectindex";
    private MongoClient getMongoClient() {
        if (mClient == null) {
            mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        }
        return mClient;
    }

    private MongoDatabase getDB() {
        return getMongoClient().getDatabase(DbName);
    }

    public MongoCollection<Document> getUserCollection() {
        return getDB().getCollection(UserCollection);
    }
    public MongoCollection<Document> getArticleCollection() {
        return getDB().getCollection(ArticleCollection);
    }

    public MongoCollection<Document> getIndirectIndexCollection() {
        return getDB().getCollection(IndirectIndexCollection);
    }

    private DbSingleton() {
        getMongoClient();
    }

    public static DbSingleton getInstance() {
        if (instance == null) {
            synchronized(DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }
        }
        return instance;
    }
}