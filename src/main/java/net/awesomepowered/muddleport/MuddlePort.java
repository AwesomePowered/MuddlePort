package net.awesomepowered.muddleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MuddlePort extends JavaPlugin implements Listener {

    public static String prefix = "[Muddle] ";
    public static String coloredPrefix = ChatColor.RED + "[MuddlePort] ";
    public static boolean debug = false;
    public static MuddlePort instance;

    /*
    public int maxX = 75000;
    public int minX = -75000;
    public int maxZ = 75000;
    public int minZ = -75000;
     */

    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MuddleMuddle(), this);
        check4WE();
        MuddleData.DataStuff.getConfigFiles();
        MuddleUtils.debugConfig();
    }

    public void check4WE() {
        if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            Bukkit.getConsoleSender().sendMessage(coloredPrefix + ChatColor.DARK_PURPLE + "WorldEdit not found, commands will not work!");
        } else {
            MuddleUtils.outputDebug("Got plugin WorldEdit!");
            getCommand("muddle").setExecutor(new MuddleCommand());
        }
    }


    /**
    @EventHandler
    public void onRightClick(PlayerInteractEvent ev) {
                  if (ev.getAction() == Action.RIGHT_CLICK_BLOCK) {
                      Location flowerLoc = new Location(getServer().getWorld("Survival"),210,71,-140);
                      if (ev.getClickedBlock().getLocation().equals(flowerLoc)) {
                        randomTeleport(ev.getPlayer());
                        System.out.println("Random Teleported " + ev.getPlayer().getName());
                      }
                  }
    }


    public void randomTeleport(Player p) {
        Location teleportTo = new Location(p.getWorld(), randInt(minX, maxX), 100, randInt(minZ, maxZ));
        Biome biome = teleportTo.getWorld().getBiome(teleportTo.getBlockX(), teleportTo.getBlockZ());
        if (biome.equals(Biome.valueOf("DEEP_OCEAN")) || biome.equals(Biome.valueOf("OCEAN"))) {
            randomTeleport(p);
        } else {
            p.teleport(teleportTo);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160,999));
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 999));
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    **/
}