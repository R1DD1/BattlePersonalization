package net.framedev.personalization.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.*;

public class DefaultListeners implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getEntity().setHealth(20);
    }

    @EventHandler
    public void onFadeBlock(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onGrowBlock(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        event.setCancelled(true);
    }
}
