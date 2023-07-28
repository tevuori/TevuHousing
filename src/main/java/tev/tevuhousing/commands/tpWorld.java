package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tev.tevuhousing.Main;

public class tpWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if(strings.length == 0){
                Player p = (Player) commandSender;
                int worldCount = countWorlds(p.getName());
                if(worldCount == 1){
                    //Teleport player to the world name in the argument
                    if(createWorldsAdmin.playerHouses.containsKey(p.getName())){
                        p.teleport(p.getServer().getWorld(createWorldsAdmin.playerHouses.get(p.getName())).getSpawnLocation());
                        p.sendMessage("Teleported to world " + createWorldsAdmin.playerHouses.get(p.getName()));
                    }else{
                        p.sendMessage("World not found");
                    }
                }else{
                    p.sendMessage("You have more than 1 world, please specify which world to teleport to");
                }
            }else if (strings.length == 1){
                //Teleport the player to the world only if the player is the owner of the world
                Player p = (Player) commandSender;
                if(createWorldsAdmin.playerHouses.containsKey(p.getName())){
                    if(createWorldsAdmin.playerHouses.get(p.getName()).equals(strings[0])){
                        p.teleport(p.getServer().getWorld(strings[0]).getSpawnLocation());
                        p.sendMessage("Teleported to world " + strings[0]);
                    }else{
                        p.sendMessage("You do not own this world");
                    }
                }
            }
        }
        return false;
    }
    public int countWorlds(String playerName){
        int count = 0;
        for(String key : createWorldsAdmin.playerHouses.keySet()){
            if(key.equals(playerName)){
                count++;
            }
        }
        return count;
    }
}
