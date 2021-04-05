package dev.yhdiamond.wisp1milenchants;

import dev.yhdiamond.wisp1milenchants.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wisp1MilEnchants extends JavaPlugin {
    public static Wisp1MilEnchants plugin;
    public static boolean isStarted = false;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);
        plugin = this;
        new Metrics(this, 10927);
        getCommand("wisp1milenchants").setExecutor(new StartCommand());
        getCommand("wisp1milenchants").setTabCompleter(new CommandComplete());
    }
}
