package net.awesomepowered.muddleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MuddleData {

    public MuddlePort thisMuddle;
    public static File muddleFile;
    public static File biomesFile;
    public static FileConfiguration muddleData;
    public static FileConfiguration biomesData;
    public static MuddleData DataStuff = new MuddleData(); //what the fuck is this for

    public static int maxX;
    public static int minX;
    public static int maxY;
    public static int minY;

    public static String muddleManager(String muddle, boolean setMuddle, boolean getMuddles, boolean getMuddleBiomes) {
        muddleFile = new File(Bukkit.getPluginManager().getPlugin("MuddlePort").getDataFolder(), "Muddles.yml");
        biomesFile = new File(Bukkit.getPluginManager().getPlugin("MuddlePort").getDataFolder(), "IgnoredBiomes.yml");
        muddleData = new YamlConfiguration();
        biomesData = new YamlConfiguration();

        if (!muddleFile.exists()) {
            try {
                muddleFile.createNewFile();
                muddleData.load(muddleFile);
                List<String> md = muddleData.getStringList("Muddles");
                md.add("WORLD.1.2.3");
                muddleData.set("Muddles", md);
                muddleData.save(muddleFile);
                System.out.println(MuddlePort.prefix + "Created " + muddleFile.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!biomesFile.exists()) {
            try {
                biomesFile.createNewFile();
                biomesData.load(biomesFile);
                List<String> ib = biomesData.getStringList("Biomes");
                ib.add("DEEP_OCEAN");
                ib.add("OCEAN");
                biomesData.set("Biomes", ib);
                biomesData.save(biomesFile);
                System.out.println(MuddlePort.prefix + "Created " + biomesFile.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (setMuddle) {
            try {
                muddleData.load(muddleFile);
                List<String> md = muddleData.getStringList("Muddles");
                md.add(muddle);
                muddleData.set("Muddles", md);
                muddleData.save(muddleFile);
                MuddleUtils.outputDebug("Setting muddle " + muddle);
            } catch (IOException e) {
                throw new MuddleXception("Failed to set Muddles ",e);
            } catch (InvalidConfigurationException e) {
                throw new MuddleXception("Failed to load MuddleData ",e);
            }
        }

        if (getMuddles) { // I regret doing it like this but am too lazy to change
                try {
                    muddleData.load(muddleFile);
                    MuddleUtils.outputDebug("Getting muddles");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                List<String> md = muddleData.getStringList("Muddles");
               for (String m : md)
                   if (m != null) {
                       return m;
                   }
            }

        if (getMuddleBiomes) {
            try {
                biomesData.load(biomesFile);
                MuddleUtils.outputDebug("Getting muddle biomes");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
           for (String b : biomesData.getStringList("Biomes"))
               return b;
        }
        return null;
    }

    public void getConfigFiles() {
        MuddleUtils.outputDebug("Getting config files");
        MuddlePort.instance.saveDefaultConfig(); //heh, fuk u
        maxX = MuddlePort.instance.getConfig().getInt("Area.MaxX");
        maxY = MuddlePort.instance.getConfig().getInt("Area.MaxY");
        minX = MuddlePort.instance.getConfig().getInt("Area.MinX");
        minY = MuddlePort.instance.getConfig().getInt("Area.MinY");
    }

    public boolean checkMuddles(String s) {
        try {
            muddleData.load(muddleFile);
            for (String m : muddleData.getStringList("Muddles")) {
                if (s.equalsIgnoreCase(m)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

}
