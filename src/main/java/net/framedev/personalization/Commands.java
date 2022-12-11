package net.framedev.personalization;

import net.framedev.personalization.items.ItemBuilder;
import net.framedev.personalization.user.User;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {
    Personalization personalization = Personalization.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            User user = personalization.getUser(player);
            if (command.getName().equalsIgnoreCase("effects")) {
                Inventory inventory;
                if (args.length == 0) {
                    inventory = Bukkit.createInventory(null, personalization.getConfig().getInt("inventories.main.size"), personalization.getConfig().getString("inventories.main.title"));
                    inventory.setItem(3,new ItemBuilder(Material.EMERALD, "Сообщение при смерти", user.customization.getDeathMsg()).build());
                    inventory.setItem(4,new ItemBuilder(Material.REDSTONE, "Эффект удара", user.customization.getHitParticle().name()).build());
                    inventory.setItem(5,new ItemBuilder(Material.DIAMOND_SWORD, "Эффект убийства", user.customization.getKillParticle().name()).build());
                    inventory.setItem(6,new ItemBuilder(Material.ARROW, "След стрелы", user.customization.getArrowTrail().name()).build());
                    player.openInventory(inventory);
                } else if (args[0].equalsIgnoreCase("list")) {
                    List<String> titles = Arrays.asList("Сообщение при смерти", "Эффект удара", "Эффект убийства", "След стрелы");
                    List<String> paths = Arrays.asList("death_msg", "hit_effects", "kill_effects", "arrow_trail");
                    List<Material> materials = Arrays.asList(Material.EMERALD, Material.REDSTONE, Material.DIAMOND_SWORD, Material.ARROW);
                    inventory = Bukkit.createInventory(null, personalization.getConfig().getInt("inventories.effects.size"), personalization.getConfig().getString("inventories.effects.title"));

                    for (int j = 0; j<= titles.size() - 1; j++) {
                        String[] lore = personalization.getConfig().getString("personalization." + paths.get(j)).split("-");
                        for (int i = 0; i <= lore.length - 1; i++) {
                            ItemStack item = new ItemBuilder(materials.get(j), titles.get(j), lore[i]).build();
                            inventory.addItem(item);
                        }
                    }
                    player.openInventory(inventory);
                } else if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        personalization.reloadConfig();
                    } catch (Exception exception) {
                        player.sendMessage(ChatColor.RED + "Fail");
                    }
                }
                return true;
            }
        }
        return false;
    }
}
