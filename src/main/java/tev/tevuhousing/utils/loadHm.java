package tev.tevuhousing.utils;

import tev.tevuhousing.commands.createWorldsAdmin;

import java.io.*;
import java.util.HashMap;

public class loadHm {
    public static void runLoad() throws IOException, ClassNotFoundException {
        File file = new File("worldData");
        if(file.exists()){
            FileInputStream fis = new FileInputStream("worldData");
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, String> loadedMap = (HashMap<String, String>) ois.readObject();
            createWorldsAdmin.playerHouses = loadedMap;
            ois.close();
        }
    }
}
