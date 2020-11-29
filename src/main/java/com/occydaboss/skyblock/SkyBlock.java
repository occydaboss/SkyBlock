package com.occydaboss.skyblock;

import com.occydaboss.skyblock.executors.IslandExecutor;
import com.occydaboss.skyblock.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class SkyBlock extends JavaPlugin
{

    public static SkyBlock mainInstance;

    private static File schem;
    private static File levelLogF;

    private static FileConfiguration config;
    private static FileConfiguration levelLog;

    private Logger logger;

    @Override
    public void onEnable ()
    {
        // Variable Initialisation
        logger = Bukkit.getLogger();
        mainInstance = this;

        // Methods
        createConfig();
        logger.warning("SkyBlock has been enabled.");

        // Command Executors
        this.getCommand("is").setExecutor(new IslandExecutor());

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable ()
    {
        logger.warning("SkyBlock has been disabled.");
    }

    public void createConfig ()
    {
        File configf = new File(getDataFolder(), "config.yml");
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

        levelLogF = new File(getDataFolder(), "level.yml");
        if (!levelLogF.exists())
        {
            levelLogF.getParentFile().mkdirs();
            try
            {
                levelLogF.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        levelLog = new YamlConfiguration();

        try
        {
            levelLog.load(levelLogF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getPluginConfig ()
    {
        return config;
    }
    public static FileConfiguration getLevelLog ()
    {
        return levelLog;
    }

    public static File getLevelLogF ()
    {
        return levelLogF;
    }
    public static File getIslandSchematic ()
    {
        return schem;
    }
}
