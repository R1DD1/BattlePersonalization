package net.framedev.personalization.listeners;

import net.framedev.personalization.Personalization;
import net.framedev.personalization.items.SpecialItems;
import net.framedev.personalization.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChooserListener implements Listener {

    private final Personalization plugin;
    public ChooserListener(Personalization plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(InventoryDragEvent event) {
        event.getWhoClicked().sendMessage("drag");
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(InventoryInteractEvent event) {
        event.getWhoClicked().sendMessage("inter");
        event.setCancelled(true);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        User user = plugin.getUser(player);

        if (item.getType() == Material.REDSTONE) { user.customization.setHitParticle(Effect.valueOf(item.getItemMeta().getLore().get(0))); }
        else if (item.getType() == Material.DIAMOND_SWORD) { user.customization.setKillParticle(Effect.valueOf(item.getItemMeta().getLore().get(0))); }
        else if (item.getType() == Material.EMERALD) { user.customization.setDeathMsg(item.getItemMeta().getDisplayName()); }
        else if (item.getType() == Material.ARROW) { user.customization.setArrowTrail(Particle.valueOf(item.getItemMeta().getLore().get(0))); }

        if (event.getClickedInventory().getHolder() != event.getWhoClicked()) {
            event.setCancelled(true);
        }
    }
}
