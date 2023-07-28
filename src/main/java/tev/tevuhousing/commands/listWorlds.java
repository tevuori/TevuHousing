package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tev.tevuhousing.Main;

public class listWorlds implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tevuhousing.listworlds")){
                p.sendMessage("List of worlds:");
                //Print out all worlds and its owners
                System.out.println(createWorldsAdmin.playerHouses);
                createWorldsAdmin.playerHouses.forEach((key, value) -> p.sendMessage(key + " owns " + value));
            }
        }
        return false;
    }
}
