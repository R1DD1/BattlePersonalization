package net.framedev.personalization.items;

import net.framedev.personalization.Personalization;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SpecialItems {

    CHOOSER(new ItemBuilder(
            Material.TOTEM,
            ChatColor.BOLD + "Персонализация",
            ChatColor.WHITE + "Выбор персонализации:/n -Сообщение при смерти/n -Эффект удара/n -Эффект убийства")
    ),
    DEATH_MSG(new ItemBuilder(
            Material.BLAZE_POWDER,
            ChatColor.BOLD + "Сообщения при убийсве",
            ChatColor.WHITE + "Выбор сообщения/n -1/n -2/n -3")
    ),
    HIT_PARTICLE(new ItemBuilder(
            Material.REDSTONE,
            ChatColor.BOLD + "Персонализация",
            ChatColor. WHITE + "Выбор 'эффекта'/n -1/n -2/n -3")
    ),
    KILL_PARTICLE(new ItemBuilder(
            Material.DIAMOND_SWORD,
            ChatColor.BOLD + "Персонализация",
            ChatColor. WHITE + "Выбор 'эффекта'/n -1/n -2/n -3")
    );

    private ItemBuilder builder;

    SpecialItems(ItemBuilder builder) {
        this.builder = builder;
    }

    public ItemStack getItem() {
        return builder.build();
    }


}

