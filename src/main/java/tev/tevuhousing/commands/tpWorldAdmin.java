package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tev.tevuhousing.Main;

import java.util.Objects;

public class tpWorldAdmin implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
         Player p = (Player) commandSender;
         if(p.hasPermission("tevuhousing.tpworldadmin")){
             if(strings.length == 1){
                //Teleport player to the world name in the argument
                 if(createWorldsAdmin.playerHouses.containsValue(strings[0])){
                     p.teleport(Objects.requireNonNull(p.getServer().getWorld(strings[0])).getSpawnLocation());
                     p.sendMessage("Teleported to world " + strings[0]);
                 }else{
                     p.sendMessage("World not found");
                 }
            }else{
                    p.sendMessage("Invalid arguments");
             }

        }else{
                p.sendMessage("You do not have permission to use this command");
         }
    }return false;
    }
}