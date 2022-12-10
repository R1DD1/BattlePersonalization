package net.framedev.personalization.listeners;

import net.framedev.personalization.Personalization;
import net.framedev.personalization.user.User;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CustomizationListener implements Listener {

    private final Personalization plugin;
    public CustomizationListener(Personalization plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        User user = plugin.getUser(player);

        event.setDeathMessage(user.name + " " + user.customization.getDeathMsg());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        LivingEntity entity = (LivingEntity) event.getEntity();
        Location loc = entity.getLocation().clone().add(0.0, 1.0, 0.0);

        User user = plugin.getUser(player);
        Effect hitParticle = user.customization.getHitParticle();
        Effect killParticle = user.customization.getKillParticle();

        if (entity.getHealth() - event.getDamage() <= 0) {
            loc.getWorld().playEffect(loc, killParticle, 1);
        } else {
            loc.getWorld().playEffect(loc, hitParticle, 1);
        }

    }
}
