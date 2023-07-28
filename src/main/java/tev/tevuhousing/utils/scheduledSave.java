package tev.tevuhousing.utils;

import org.bukkit.scheduler.BukkitRunnable;
import tev.tevuhousing.Main;
import tev.tevuhousing.commands.createWorldsAdmin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class scheduledSave {
    public static void runScheduledSave() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream("worldData");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(createWorldsAdmin.playerHouses);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 10 * 60 * 20);
    }
}
