package net.framedev.personalization;

import net.framedev.personalization.listeners.ChooserListener;
import net.framedev.personalization.listeners.CustomizationListener;
import net.framedev.personalization.listeners.DefaultListeners;
import net.framedev.personalization.listeners.JoinListener;
import net.framedev.personalization.user.Customization;
import net.framedev.personalization.user.User;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Personalization extends JavaPlugin {

    private static Personalization instance;
    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new DefaultListeners(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new CustomizationListener(this), this);
        getServer().getPluginManager().registerEvents(new ChooserListener(this), this);

        getCommand("effects").setExecutor(new Commands());
        new Config().defaultData();

        loadConfiguration();

        new Inventories();
    }

    public static Personalization getInstance() {
        return instance;
    }

    HashMap<UUID, User> users = new HashMap<>();

    public User getUser(Player player) {
        if (userExist(player.getUniqueId())) {
            return users.get(player.getUniqueId());
        }
        return addUser(player.getUniqueId());
    }

    public User getUser(UUID uuid) {
        if (userExist(uuid)) {
            return users.get(uuid);
        }
        return addUser(uuid);
    }

    private User addUser(UUID uuid) {
        if (!userExist(uuid)) {
            Player player = getServer().getPlayer(uuid);
            return users.put(uuid, new User(uuid, player, player.getPlayerListName(), new Customization()));
        }
        return null;
    }

    private Boolean userExist(UUID uuid){
        return users.containsKey(uuid);
    }

    private void loadConfiguration() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }
}
