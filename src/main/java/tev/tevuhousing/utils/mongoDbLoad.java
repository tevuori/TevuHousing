package tev.tevuhousing.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import tev.tevuhousing.Main;
import tev.tevuhousing.commands.createWorldsAdmin;

import java.util.HashMap;

public class mongoDbLoad {
    public static void loadWorldsData(){
        MongoCursor<Document> cursor = Main.getPlugin(Main.class).collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            HashMap<String, Object> loadedMap = (HashMap<String, Object>) doc.get("data");
            HashMap<String, String> convertedMap = new HashMap<>();
            for (HashMap.Entry<String, Object> entry : loadedMap.entrySet()) {
                convertedMap.put(entry.getKey(), (String) entry.getValue());
            }
            createWorldsAdmin.playerHouses = convertedMap;
        }
    }
}
