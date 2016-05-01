package net.awesomepowered.muddleport;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class MuddleMuddle implements Listener {

    public int coolteim = 600000; //60k a minute
    public MuddlePort muddlez;
    public MuddleUtils mudz = new MuddleUtils();
    public MuddleData mahData = new MuddleData();
    private HashMap<UUID, Long> kewlDowwwwn = new HashMap<UUID, Long>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent ev) {
        if (ev.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = ev.getPlayer();
            if (mahData.checkMuddles(mudz.parseLocation(ev.getClickedBlock().getLocation()))) {
                if (!isInCooldown(p.getUniqueId())) {
                    MuddleUtils.randomTeleport(ev.getPlayer());
                    MuddleUtils.outputDebug("LISTENER 2 OK!");
                    kewlDowwwwn.put(p.getUniqueId(), System.currentTimeMillis() + coolteim);
                } else {
                    p.sendMessage(ChatColor.RED + "You may only use this once every 10 minutes.");
                }
            }
        }
    }

    public boolean isInCooldown(UUID uidz) {
        if (!kewlDowwwwn.containsKey(uidz)) {
            return false;
        }
        else if (kewlDowwwwn.get(uidz) > System.currentTimeMillis()) {
            return true;
        }
        else {
            kewlDowwwwn.remove(uidz);
            return false;
        }
    }

}
