package tev.tevuhousing.utils;

import tev.tevuhousing.commands.createWorldsAdmin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class loadHm {
    public static void runLoad() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("worldData");
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<String, String> loadedMap = (HashMap<String, String>) ois.readObject();
        createWorldsAdmin.playerHouses = loadedMap;
        ois.close();
    }
}
