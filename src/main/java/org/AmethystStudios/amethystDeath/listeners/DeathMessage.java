package org.AmethystStudios.amethystDeath.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public final class DeathMessage implements Listener {
    private final FileConfiguration messages;

    public DeathMessage(FileConfiguration messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();

        if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent ev) {
            Entity damager = ev.getDamager();

            if (damager instanceof LivingEntity mob && !(damager instanceof Player)) {

                String mobName = mob.getType().toString().replace("_", " ").toLowerCase();
                mobName = Character.toUpperCase(mobName.charAt(0)) + mobName.substring(1);

                String rawMobMsgChat = messages.getString("death.mob.chat", "&c%player%&f was killed by a &a%mob%.");
                rawMobMsgChat = rawMobMsgChat
                        .replace("%player%", player.getDisplayName())
                        .replace("%mob%", mobName);
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawMobMsgChat));

                String rawMobMsgActionbar = messages.getString("death.mob.actionbar", "&c%player%&f was killed by a &a%mob%.");
                rawMobMsgActionbar = rawMobMsgActionbar
                        .replace("%player%", player.getDisplayName())
                        .replace("%mob%", mobName);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        new TextComponent(ChatColor.translateAlternateColorCodes('&', rawMobMsgActionbar)));
            } else if (damager instanceof Player killer) {

                String killerName = killer.getDisplayName();

                ItemStack weapon = killer.getInventory().getItemInMainHand();
                String killerWeapon = weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName()
                        ? weapon.getItemMeta().getDisplayName()
                        : weapon.getType().toString().replace("_", " ").toLowerCase();
                killerWeapon = Character.toUpperCase(killerWeapon.charAt(0)) + killerWeapon.substring(1);

                String rawKilledMsgChat = messages.getString("death.killed.chat", "&c%player%&f was slain by &a%killer%&f using %weapon%.");
                rawKilledMsgChat = rawKilledMsgChat
                        .replace("%player%", player.getDisplayName())
                        .replace("%killer%", killerName)
                        .replace("%weapon%", killerWeapon);
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawKilledMsgChat));

                String rawKilledMsgActionbar = messages.getString("death.killed.actionbar", "&c%player%&f was slain by &a%killer%&f using %weapon%.");
                rawKilledMsgActionbar = rawKilledMsgActionbar
                        .replace("%player%", player.getDisplayName())
                        .replace("%killer%", killerName)
                        .replace("%weapon%", killerWeapon);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        new TextComponent(ChatColor.translateAlternateColorCodes('&', rawKilledMsgActionbar)));
            }

        } if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL) {

            String rawFallMsgChat = messages.getString("death.fall.chat", "&c%player%&f hit the ground too hard.");
            rawFallMsgChat = rawFallMsgChat
                    .replace("%player%", player.getDisplayName());
            e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawFallMsgChat));

            String rawFallMsgActionbar = messages.getString("death.fall.actionbar", "&c%player%&f hit the ground too hard.");
            rawFallMsgActionbar = rawFallMsgActionbar
                    .replace("%player%", player.getDisplayName());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', rawFallMsgActionbar)));

        } if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.DROWNING) {
            String rawDrowningMsgChat = messages.getString("death.drowning.chat", "&c%player%&f drowned.");
            rawDrowningMsgChat = rawDrowningMsgChat
                    .replace("%player%", player.getDisplayName());
            e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawDrowningMsgChat));

            String rawDrowningMsgActionbar = messages.getString("death.drowning.actionbar", "&c%player%&f drowned.");
            rawDrowningMsgActionbar = rawDrowningMsgActionbar
                    .replace("%player%", player.getDisplayName());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', rawDrowningMsgActionbar)));
        } if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA) {
            String rawLavaMsgChat = messages.getString("death.lava.chat", "&c%player%&f tried to swim in lava.");
            rawLavaMsgChat = rawLavaMsgChat
                    .replace("%player%", player.getDisplayName());
            e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawLavaMsgChat));

            String rawLavaMsgActionbar = messages.getString("death.lava.actionbar", "&c%player%&f tried to swim in lava.");
            rawLavaMsgActionbar = rawLavaMsgActionbar
                    .replace("%player%", player.getDisplayName());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', rawLavaMsgActionbar)));
        } if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) {
            String rawVoidMsgChat = messages.getString("death.void.chat", "&c%player%&f fell into the void and vanished.");
            rawVoidMsgChat = rawVoidMsgChat
                    .replace("%player%", player.getDisplayName());
            e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', rawVoidMsgChat));

            String rawVoidMsgActionbar = messages.getString("death.void.actionbar", "&c%player%&f fell into the void and vanished.");
            rawVoidMsgActionbar = rawVoidMsgActionbar
                    .replace("%player%", player.getDisplayName());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', rawVoidMsgActionbar)));
        }
    }
}
