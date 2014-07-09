package net.awesomepowered.muddleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class MuddlePort extends JavaPlugin implements Listener {

    public int maxX = 75000;
    public int minX = -75000;
    public int maxZ = 75000;
    public int minZ = -75000;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

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
        if (!biome.equals(Biome.valueOf("DEEP_OCEAN")) || !biome.equals(Biome.valueOf("OCEAN"))) {
        p.teleport(teleportTo);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 140,999));
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 999));
        } else {
            randomTeleport(p);
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
