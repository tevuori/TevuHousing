package tev.tevuhousing.events;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tev.tevuhousing.Main;
import tev.tevuhousing.commands.createWorldsAdmin;

import java.util.Objects;
import java.util.Random;

public class onFirstJoinCreate implements Listener {
@EventHandler
    public void onFJoinCreate(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!createWorldsAdmin.playerHouses.containsKey(player.getName())) {
            //Pick one key from Hashmap that has key null and replace it with the player name
            for (String key : createWorldsAdmin.playerHouses.keySet()){
                if(key.contains("UNASSIGNED")){
                    String IdOfWorld = createWorldsAdmin.playerHouses.get(key);
                    createWorldsAdmin.playerHouses.remove(key);
                    createWorldsAdmin.playerHouses.put(player.getName(), IdOfWorld);
                    System.out.println("Player " + player.getName() + " has been assigned world " + IdOfWorld);
                    break;
                }
            }
        }
    }
}