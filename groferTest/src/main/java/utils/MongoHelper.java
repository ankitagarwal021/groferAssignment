package utils;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static utils.BaseClass.mongoHostTND;
import static utils.BaseClass.mongoPortTND;

/**
 * Created By: Ankit Agarwal
 **/

public class MongoHelper {

    public static List<Object> getResultFromMongo(MongoCollection mongoCollection, BasicDBObject searchQuery) {
        FindIterable findIterable = mongoCollection.find(searchQuery);
        MongoCursor mongoCursor = findIterable.iterator();
        ArrayList mongoAsObjectResult = new ArrayList();

        while(mongoCursor.hasNext()) {
            mongoAsObjectResult.add(mongoCursor.next());
        }

        return mongoAsObjectResult;
    }

    public static MongoCollection getCollection(MongoClient mongoClient, String dataBase, String collection) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBase);
        return mongoDatabase.getCollection(collection);
    }

    public static MongoCollection getMongoCollectionTND(String database, String collection) {
        MongoClient mongoClient = new MongoClient(mongoHostTND, Integer.parseInt(mongoPortTND));
        return getCollection(mongoClient, database, collection);
    }

}
