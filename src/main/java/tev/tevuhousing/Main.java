package tev.tevuhousing;

import org.bukkit.plugin.java.JavaPlugin;
import tev.tevuhousing.commands.createWorldsAdmin;
import tev.tevuhousing.commands.deleteWorld;
import tev.tevuhousing.commands.tpWorld;
import tev.tevuhousing.commands.tpWorldAdmin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Load all worlds from hashmap
        for(String world : createWorldsAdmin.playerHouses.values()){
            getServer().createWorld(new org.bukkit.WorldCreator(world));
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Delete all worlds
        for(String world : createWorldsAdmin.playerHouses.values()){
            getServer().unloadWorld(world, true);
            getServer().getWorld(world).getWorldFolder().delete();
        }
    }
}
