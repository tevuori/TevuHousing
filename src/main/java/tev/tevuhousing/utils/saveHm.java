package tev.tevuhousing.utils;

import tev.tevuhousing.commands.createWorldsAdmin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class saveHm {
    public static void runSave() throws IOException {
        //If worldData file doesn't exist, create it
        FileOutputStream fos = new FileOutputStream("worldData");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(createWorldsAdmin.playerHouses);
        oos.close();
    }
}
