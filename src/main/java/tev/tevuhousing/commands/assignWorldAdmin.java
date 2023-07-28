package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class assignWorldAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tevuhousing.assignworldadmin")){
                if(strings.length == 2){
                    //Assign the world to the player
                    String playerName = strings[0];
                    String houseId = strings[1];

                    if(createWorldsAdmin.playerHouses.containsValue(houseId)) {
                        //replace the old key with new one (player name)
                        createWorldsAdmin.playerHouses.forEach((key, value) -> {
                            if (value.equals(houseId)) {
                                createWorldsAdmin.playerHouses.replace(key, playerName);
                            }
                        });
                        p.sendMessage("Assigned world " + houseId + " to " + playerName);
                    }else{
                        p.sendMessage("World not found");
                    }
                }else{
                    p.sendMessage("Invalid arguments");
                }
        }else{
                p.sendMessage("You do not have permission to use this command");
        }
        }
        return false;
}
}
