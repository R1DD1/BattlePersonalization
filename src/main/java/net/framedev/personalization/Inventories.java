package net.framedev.personalization;

import net.framedev.personalization.items.ItemBuilder;
import net.framedev.personalization.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Inventories {
    Personalization personalization = Personalization.getInstance();

    private List<String> titles = Arrays.asList("Сообщение при смерти", "Эффект удара", "Эффект убийства", "След стрелы");
    private List<String> paths = Arrays.asList("death_msg", "hit_effects", "kill_effects", "arrow_trail");
    private List<Material> materials = Arrays.asList(Material.EMERALD, Material.REDSTONE, Material.DIAMOND_SWORD, Material.ARROW);

    private Inventory main = Bukkit.createInventory(null, personalization.getConfig().getInt("inventories.main.size"), personalization.getConfig().getString("inventories.main.title"));
    private Inventory list = Bukkit.createInventory(null, personalization.getConfig().getInt("inventories.effects.size"), personalization.getConfig().getString("inventories.effects.title"));

    private void initInv() {
        for (int j = 0; j<= titles.size() - 1; j++) {
            String[] lore = personalization.getConfig().getString("personalization." + paths.get(j)).split("-");
            for (int i = 0; i <= lore.length - 1; i++) {
                ItemStack item = new ItemBuilder(materials.get(j), titles.get(j), lore[i]).build();
                list.addItem(item);
            }
        }
        list.setItem(35, new ItemBuilder(Material.BARRIER, "Моя пероснализация", "Вернуться назад").build());
    }

    public void openMain(Player player) {
        User user = personalization.getUser(player);
        main.setItem(0,new ItemBuilder(Material.EMERALD, "Сообщение при смерти", user.customization.getDeathMsg()).build());
        main.setItem(1,new ItemBuilder(Material.REDSTONE, "Эффект удара", user.customization.getHitParticle().name()).build());
        main.setItem(2,new ItemBuilder(Material.DIAMOND_SWORD, "Эффект убийства", user.customization.getKillParticle().name()).build());
        main.setItem(3,new ItemBuilder(Material.ARROW, "След стрелы", user.customization.getArrowTrail().name()).build());

        main.setItem(8,new ItemBuilder(Material.PAPER, "Список кастомизации", "").build());
        player.openInventory(main);
    }

    public void openList(Player player) {
        player.openInventory(list);
    }

    {
        initInv();
    }

}
