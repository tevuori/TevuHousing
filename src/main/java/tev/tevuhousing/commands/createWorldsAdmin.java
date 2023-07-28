package tev.tevuhousing.commands;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tev.tevuhousing.Main;

import java.util.HashMap;
import java.util.Random;

public class createWorldsAdmin implements CommandExecutor {
    public static HashMap<String, String> playerHouses = new HashMap<String, String>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("tevuhousing.createworldsadmin")) {
                if(strings.length != 0 ) {
                    int worldsToGenerate = Integer.parseInt(strings[0]);
                    for (int i = 0; i < worldsToGenerate; i++) {
                        //Create Id for world
                        Random r = new Random();
                        int generateID = r.nextInt(1000000);
                        String houseID = Integer.toString(generateID);

                        //Generate the world with id
                        WorldCreator wc = new WorldCreator(houseID);
                        wc.environment(World.Environment.NORMAL);
                        wc.createWorld();

                        //Add world to the Id to hashmap
                        int sizeOfHashMap = playerHouses.size() + 1;
                        playerHouses.put("UNASSIGNED" + sizeOfHashMap, houseID);
                        playerHouses.forEach((key, value) -> p.sendMessage(key + " owns " + value));
                        //Send message to the world creator
                        p.sendMessage("World " + houseID + " has been created");
                    }
                }
            } else {
                p.sendMessage("You do not have permission to use this command");
            }
        }
        return false;
    }

}
