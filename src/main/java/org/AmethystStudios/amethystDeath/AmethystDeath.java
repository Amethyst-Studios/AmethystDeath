package org.AmethystStudios.amethystDeath;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.AmethystStudios.amethystDeath.listeners.*;

public final class AmethystDeath extends JavaPlugin implements Listener {
    private FileConfiguration messages;
    private File messagesFile;

    public void onEnable() {
        this.saveDefaultMessages();
        FileConfiguration config = this.getConfig();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new DeathMessage(this.messages), this);
        this.getServer().getPluginManager().registerEvents(new DeathReward(config), this);
        this.getLogger().info("AmethystDeath » The plugin successfully started.");
    }

    public void onDisable() {
        this.getLogger().info("AmethystDeath » Plugin failed to load!");
    }

    private void saveDefaultMessages() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        this.messagesFile = new File(this.getDataFolder(), "messages.yml");
        if (!this.messagesFile.exists()) {
            this.saveResource("messages.yml", false);
        }

        this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);
    }

    public void reloadMessages() {
        this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);
    }

    public FileConfiguration getMessages() {
        return this.messages;
    }
}
