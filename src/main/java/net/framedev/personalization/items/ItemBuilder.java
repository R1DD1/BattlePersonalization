package net.framedev.personalization.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {
    private Material material;
    private String title;
    private ArrayList<String> lore;

    public ItemBuilder(Material material, String title, String lore) {
        this.material = material;
        this.title = title;
        this.lore = new ArrayList<>(Arrays.asList(lore.split("/n")));
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        meta.removeItemFlags();
        item.setItemMeta(meta);
        return item;
    }
}
