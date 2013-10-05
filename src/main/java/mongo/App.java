package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("mongo-play");

        List<String> dbs = mongo.getDatabaseNames();
        for (String dbName : dbs) {
            System.out.println("Database Names: " + dbName);
        }

        Set<String> tables = db.getCollectionNames();

        for (String coll : tables) {
            System.out.println("Table: " + coll);
        }

        DBCollection table = db.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Paul");
        document.put("age", 32);
        document.put("createdDate", new Date());
        table.insert(document);


        BasicDBObject searchQuery = new BasicDBObject();
       	searchQuery.put("name", "Paul");
       	DBCursor cursor = table.find(searchQuery);
       	while (cursor.hasNext()) {
       		System.out.println("Found: " + cursor.next());
       	}
    }
}
