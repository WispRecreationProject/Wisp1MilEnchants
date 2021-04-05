package dev.yhdiamond.wisp1milenchants;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("wisp1milenchants.toggle")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        if (!Wisp1MilEnchants.isStarted) {
                            Wisp1MilEnchants.isStarted = true;
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Enchants are 1,000,000 challenge has started!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "The challenge is already started!");
                        }
                    } else if (args[0].equals("stop")){
                        if (Wisp1MilEnchants.isStarted) {
                            Wisp1MilEnchants.isStarted = false;
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Enchants are 1,000,000 challenge has ended!");
                        } else {
                            sender.sendMessage(ChatColor.RED + "The challenge hasn't started!");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED+"/wisp1milenchants <start/stop>");
                    }
                } else {
                    p.sendMessage(ChatColor.RED+"/wisp1milenchants <start/stop>");
                }
            } else {
                p.sendMessage(ChatColor.RED+"You do not have the required permission to run this command!");
            }
        } else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }

        return true;
    }
}