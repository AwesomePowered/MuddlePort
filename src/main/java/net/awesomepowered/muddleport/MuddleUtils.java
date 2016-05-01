package net.awesomepowered.muddleport;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.logging.Level;

public class MuddleUtils {

    public static void newMuddle(Player p) {
        WorldEditPlugin we = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        Selection sel = we.getSelection(p);
        if (sel == null) {
            p.sendMessage(MuddlePort.coloredPrefix + "Invalid selection!");
        } else {
            String newMuddle = sel.getWorld().getName()+"."+
                    sel.getMaximumPoint().getBlockX()+"."+
                    sel.getMaximumPoint().getBlockY()+"."+
                    sel.getMaximumPoint().getBlockZ();
            MuddleData.muddleManager(newMuddle, true, false, false);
            outputDebug(MuddlePort.prefix + "Created new muddle! " + newMuddle);
            p.sendMessage(MuddlePort.coloredPrefix + "New muddle created!");
        }
    }

    public static int rd(int max, int min) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static void randomTeleport(Player p) {
        Location teleportTo = new Location(p.getWorld(), rd(MuddleData.maxX, MuddleData.minX), 100, rd(MuddleData.maxY, MuddleData.minY));           //DONT FORGET TO DO THIS ONE BRO
        Biome biome = teleportTo.getWorld().getBiome(teleportTo.getBlockX(), teleportTo.getBlockZ());
        if (biome.equals(Biome.valueOf(MuddleData.muddleManager(null, false, false, true)))) {
            MuddleUtils.outputDebug("Got invalid biome for " + p.getName() + " re-teleporting");
            randomTeleport(p);
        } else {
            MuddleUtils.outputDebug("Random Teleporting " + p.getName() + " to " + teleportTo);
            MuddleUtils.outputDebug("Biome: " + biome);
            p.teleport(teleportTo);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160,999)); //Let's put them in a list later...
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 999));
        }
    }

    public String parseLocation(Location loc) {
        MuddleUtils.outputDebug("Parsing location " + loc);
        outputDebug(loc.getWorld().getName()+"."+loc.getBlockX()+"."+loc.getBlockY()+"."+loc.getBlockZ());
        return (loc.getWorld().getName()+"."+loc.getBlockX()+"."+loc.getBlockY()+"."+loc.getBlockZ());
    }

    public static void debugConfig() {
        outputDebug("--------------------------------------");
        outputDebug("GetMuddles " + MuddleData.muddleManager(null, false, true, false));
        outputDebug("GetBiomes " + MuddleData.muddleManager(null, false, false, true));
        outputDebug("MuddleFile " + MuddleData.muddleFile.toString());
        outputDebug("BiomeFile " + MuddleData.biomesFile.toString());
        outputDebug("MaxX " + MuddleData.maxX + " MinX " + MuddleData.minX);
        outputDebug("MaxY " + MuddleData.maxY + " MinY " + MuddleData.minY);
        outputDebug("--------------------------------------");
    }

    public static void outputDebug(String s) {
        if (MuddlePort.debug) {
        Bukkit.getLogger().log(Level.INFO, s);
        }
    }

}
