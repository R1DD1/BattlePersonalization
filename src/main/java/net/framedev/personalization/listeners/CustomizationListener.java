package net.framedev.personalization.listeners;

import net.framedev.personalization.Personalization;
import net.framedev.personalization.user.User;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class CustomizationListener implements Listener {

    private final Personalization plugin;
    public CustomizationListener(Personalization plugin) {
        this.plugin = plugin;
    }

    private HashMap<Projectile, BukkitTask> tasks = new HashMap<>();

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

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = ((Player) event.getEntity().getShooter());
            User user = plugin.getUser(player);
            tasks.put(event.getEntity(), new BukkitRunnable() {
                @Override
                public void run() {
                    event.getEntity().getWorld().spawnParticle(user.customization.getArrowTrail(), event.getEntity().getLocation(), 2);
                }
            }.runTaskTimer(plugin, 1L, 2L));
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            BukkitTask task = tasks.get(event.getEntity());
            if (task != null) {
                task.cancel();
                tasks.remove(task);
            }
        }
    }
}
