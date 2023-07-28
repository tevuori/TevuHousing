package tev.tevuhousing;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;
import tev.tevuhousing.commands.createWorldsAdmin;
import tev.tevuhousing.commands.deleteWorld;
import tev.tevuhousing.commands.tpWorld;
import tev.tevuhousing.commands.tpWorldAdmin;
import tev.tevuhousing.utils.mongoDbLoad;
import tev.tevuhousing.utils.mongoDbSave;

import java.io.*;
import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;
import static tev.tevuhousing.utils.loadHm.runLoad;
import static tev.tevuhousing.utils.saveHm.runSave;
import static tev.tevuhousing.utils.scheduledSave.runScheduledSave;

public final class Main extends JavaPlugin {
    public MongoCollection<Document> collection;
    public MongoDatabase database;
    @Override
    public void onEnable() {
        // Plugin startup logic
        // Load all worlds from hashmap
        for(String world : createWorldsAdmin.playerHouses.values()){
            getServer().createWorld(new org.bukkit.WorldCreator(world));
        }
        //Load the hashmap from the file
        try {
            runLoad();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Save hashmap every 10 minutes
        runScheduledSave();
        //MongoDB Setup
        try{
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("data");
            collection = database.getCollection("worlds");
            System.out.println("MongoDB connection successful");
            mongoDbLoad.loadWorldsData();
        }catch (Exception e) {
            System.out.println("MongoDB connection failed");
        }
        // Register events
        getServer().getPluginManager().registerEvents(new tev.tevuhousing.events.onFirstJoinCreate(), this);
        // Register commands
        getCommand("listworlds").setExecutor(new tev.tevuhousing.commands.listWorlds());
        getCommand("tpworld").setExecutor(new tpWorld());
        getCommand("tpworldadmin").setExecutor(new tpWorldAdmin());
        getCommand("createWorlds").setExecutor(new createWorldsAdmin());
        getCommand("deleteWorld").setExecutor(new deleteWorld());
        getCommand("assignWorldAdmin").setExecutor(new tev.tevuhousing.commands.assignWorldAdmin());
        getCommand("unassignWorldAdmin").setExecutor(new tev.tevuhousing.commands.unassignWorldAdmin());
        getCommand("tpa").setExecutor(new tev.tevuhousing.commands.tpa());
        getCommand("tpaccept").setExecutor(new tev.tevuhousing.commands.tpaccept());
        getCommand("tpdeny").setExecutor(new tev.tevuhousing.commands.tpdeny());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Delete all worlds
        for(String world : createWorldsAdmin.playerHouses.values()){
            getServer().unloadWorld(world, true);
            getServer().getWorld(world).getWorldFolder().delete();
        }
        //On disable, save hashmap into file worldData.txt so it can be loaded on stasrtup
        try {
            runSave();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            mongoDbSave.saveWorldsData();
        }catch (Exception e){
            System.out.println("MongoDB connection failed");
        }
    }
}
