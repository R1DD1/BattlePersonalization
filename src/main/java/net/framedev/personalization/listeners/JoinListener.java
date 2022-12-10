package net.framedev.personalization.listeners;

import net.framedev.personalization.Personalization;
import net.framedev.personalization.items.SpecialItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final Personalization plugin;

    public JoinListener(Personalization plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getUser(player);
        player.getInventory().clear();
        player.getInventory().setItem(8, SpecialItems.CHOOSER.getItem());
    }
}
