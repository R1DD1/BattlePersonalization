package net.framedev.personalization;

import net.framedev.personalization.items.ItemBuilder;
import net.framedev.personalization.user.User;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {
    Personalization personalization = Personalization.getInstance();
    Inventories inventories = new Inventories();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("effects")) {
                if (args.length == 0) {
                    inventories.openMain(player);
                } else if (args[0].equalsIgnoreCase("list")) {
                    inventories.openList(player);
                } else if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        personalization.reloadConfig();
                    } catch (Exception exception) {
                        player.sendMessage(ChatColor.RED + "Fail");
                    }
                }
                return true;
            }
        }
        return false;
    }
}
