package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //If the player denies the request, remove the request from the hashmap and send a message to the player who sent the request that the request was denied
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(tpa.tpaRequests.containsKey(p.getName())){
                if(tpa.tpaRequests.get(p.getName()).equals(strings[0])){
                    tpa.tpaRequests.remove(p.getName());
                    p.sendMessage("Request denied");
                    Player requestedPlayer = p.getServer().getPlayer(strings[0]);
                    requestedPlayer.sendMessage("Request denied");
                }else{
                    p.sendMessage("You do not have any requests");
                }
            }else{
                p.sendMessage("You do not have any requests");
            }
        }
        return false;
    }
}
