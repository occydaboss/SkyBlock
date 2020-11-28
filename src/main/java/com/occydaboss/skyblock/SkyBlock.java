package com.occydaboss.skyblock;

import com.occydaboss.skyblock.cmdexec.IslandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SkyBlock extends JavaPlugin
{

    public static SkyBlock mainInstance;
    private File configf;
    private static File schem;
    private static FileConfiguration config;

    @Override
    public void onEnable ()
    {
        mainInstance = this;

        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "\n\nThanks for choosing SkyBlock!\n\n");
        this.getCommand("is").setExecutor(new IslandExecutor());

        createConfig();

    }

    @Override
    public void onDisable ()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "\n\nSkyBlock has been disabled.\n\n");
    }

    public void createConfig ()
    {
        configf = new File(getDataFolder(), "config.yml");
        if (!configf.exists())
        {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        schem = new File(getDataFolder(), "island.schem");

        if (!schem.exists())
        {
            saveResource("island.schem", false);
        }

        config = new YamlConfiguration();

        try
        {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getPluginConfig ()
    {
        return config;
    }

    public static File getIslandSchematic ()
    {
        return schem;
    }
}
