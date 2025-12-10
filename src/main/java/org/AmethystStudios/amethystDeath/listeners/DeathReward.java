package org.AmethystStudios.amethystDeath.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class DeathReward implements Listener {
    private final FileConfiguration messages;

    public DeathReward(FileConfiguration messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity();
        Player killer = dead.getKiller();

        for (String cmd : messages.getStringList("death.reward.victim")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    cmd.replace("%player%", dead.getName()));
        }

        if (killer != null) {
            for (String cmd : messages.getStringList("death.reward.killer")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        cmd.replace("%player%", killer.getName())
                                .replace("%killed%", dead.getName()));
            }
        }
    }
}
