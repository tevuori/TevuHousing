package tev.tevuhousing.utils;

import org.bson.Document;
import tev.tevuhousing.Main;
import tev.tevuhousing.commands.createWorldsAdmin;

public class mongoDbSave {
    public static void saveWorldsData(){
        Main.getPlugin(Main.class).collection.deleteMany(new Document());
        Document document = new Document(createWorldsAdmin.playerHouses);
        Main.getPlugin(Main.class).collection.insertOne(document);
    }
}
