package dev.yhdiamond.wisp1milenchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantListener implements Listener {
    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        if (Wisp1MilEnchants.isStarted) {
            List list = new ArrayList<>();
            for (Map.Entry<Enchantment, Integer> entry : e.getEnchantsToAdd().entrySet()) {
                String[] lowercaseWords = entry.getKey().toString().replace("Enchantment[minecraft:", "").replace(", ", "").replace(entry.getKey().getName(), "").replace("]", "").replace("_", " ").split(" ");
                List<String> uppercaseWords = new ArrayList<>();
                for (String s : lowercaseWords) {
                    uppercaseWords.add(s.replaceFirst(s.charAt(0) + "", Character.toUpperCase(s.charAt(0)) + ""));
                }
                list.add(ChatColor.GRAY + String.join(" ", uppercaseWords) + " 1,000,000");
                entry.setValue(32767);
            }
            ItemMeta meta = e.getItem().getItemMeta();
            meta.setLore(list);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            e.getItem().setItemMeta(meta);
            if (e.getInventory() != null && ((EnchantingInventory) e.getInventory()).getSecondary() != null) {

                int amount = ((EnchantingInventory) e.getInventory()).getSecondary().getAmount();
                BukkitRunnable setLapis = new BukkitRunnable() {
                    @Override
                    public void run() {
                        ((EnchantingInventory) e.getInventory()).setSecondary(new ItemStack(Material.LAPIS_LAZULI, amount));
                    }
                };
                setLapis.runTaskLater(Wisp1MilEnchants.plugin, 1);
            }
        }
    }
}
