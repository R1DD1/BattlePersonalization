package net.framedev.personalization.user;

import org.bukkit.entity.Player;

import java.security.PublicKey;
import java.util.UUID;

public class User {
    public UUID uuid;
    public Player player;
    public String name;
    public Customization customization;
    public User(UUID uuid, Player player, String name, Customization customization) {
        this.uuid = uuid;
        this.player = player;
        this.name = name;
        this.customization = customization;
    }
}
