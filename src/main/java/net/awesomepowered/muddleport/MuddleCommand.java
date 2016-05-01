package net.awesomepowered.muddleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuddleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String CommandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                //args 0 stuff
                MuddleUtils.outputDebug("Got command muddle");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("new") && sender.hasPermission("muddle.admin")) {
                    MuddleUtils.newMuddle(p);
                    MuddleUtils.outputDebug("got command muddle new");
                } if (args[0].equalsIgnoreCase("teleport") && sender.hasPermission("muddle.teleport.command")) {
                    MuddleUtils.randomTeleport(p);
                    MuddleUtils.outputDebug("Got command muddle teleport");
                }
            }
        } else {
            sender.sendMessage(MuddlePort.prefix + "Silly console, muddles are for players!");
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
