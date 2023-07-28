package tev.tevuhousing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class unassignWorldAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("tevuhousing.unassignworldamdin")) {
                if (strings.length == 1){
                    String worldId = strings[0];
                    if(createWorldsAdmin.playerHouses.containsValue(worldId)){
                        int sizeOfHashMap = createWorldsAdmin.playerHouses.size() + 1;
                        createWorldsAdmin.playerHouses.remove(worldId);
                        createWorldsAdmin.playerHouses.put("UNASSIGNED" + sizeOfHashMap, worldId);
                        p.sendMessage("Unassigned world " + worldId);
                    }
                }
            }else{
                p.sendMessage("You do not have permission to use this command");
            }
        }
        return false;
    }
}
