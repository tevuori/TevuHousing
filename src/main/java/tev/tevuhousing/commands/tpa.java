package tev.tevuhousing.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class tpa implements CommandExecutor
{
    public static HashMap<String,String> tpaRequests = new HashMap<String,String>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //If player send this command he needs to specify to which player he wants to teleport. To the player reqestedd should send a message with request and the player requested can approvev or deny teleport. When approve the player will be teleported to the player that he requested to teleport to
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(strings.length == 1) {
                String playernameRequested = strings[0];
                if(Bukkit.getPlayer(playernameRequested) != null){
                    Player playerRequested = Bukkit.getPlayer(playernameRequested);
                    playerRequested.sendMessage("Player " + p.getName() + " wants to teleport to you. Type /tpaccept to accept or /tpdeny to deny");
                    p.sendMessage("Request sent");
                    tpaRequests.put(p.getName(),playernameRequested);
            }
        }
    }return false;
}
}
