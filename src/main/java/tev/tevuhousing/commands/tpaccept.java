package tev.tevuhousing.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
        Player p = (Player) commandSender;
        if(strings.length ==1 ){
            if(tpa.tpaRequests.containsValue(p.getName())){
                if(tpa.tpaRequests.get(p.getName()).equals(strings[0])){
                    //Teleport the player who sent the message to the player who accepted the request
                    Player playerToTeleport = Bukkit.getPlayer(strings[0]);
                    p.teleport(playerToTeleport);
                    playerToTeleport.sendMessage("Teleported to " + playerToTeleport.getName());
                }else{
                    p.sendMessage("You do not have any requests");
                }
            }else{
                p.sendMessage("You do not have any requests");
            }
        }
        }
        return false;
    }
}
