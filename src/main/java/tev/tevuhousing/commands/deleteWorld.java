package tev.tevuhousing.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deleteWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(createWorldsAdmin.playerHouses.containsKey(p.getName()) || p.hasPermission("tevuhousing.deleteworldadmin")){
                //Delete the world
                String worldId = createWorldsAdmin.playerHouses.get(p.getName());
                createWorldsAdmin.playerHouses.remove(p.getName());

                //Delete the world with name ID from server
                World world = p.getServer().getWorld(worldId);
                assert world != null;
                try{
                    world.getWorldFolder().delete();
                    p.sendMessage("Deleted world " + worldId);
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
        return false;
}
}
