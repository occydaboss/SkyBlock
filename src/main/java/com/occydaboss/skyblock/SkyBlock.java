package com.occydaboss.skyblock;

import com.occydaboss.skyblock.executors.IslandExecutor;
import com.occydaboss.skyblock.executors.LevelUpExecutor;
import com.occydaboss.skyblock.executors.SetLevelExecutor;
import com.occydaboss.skyblock.executors.ShopExecutor;
import com.occydaboss.skyblock.listeners.CobbleGenListener;
import com.occydaboss.skyblock.listeners.InventoryClickListener;
import com.occydaboss.skyblock.listeners.PlayerJoinListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class SkyBlock extends JavaPlugin
{

    public static SkyBlock mainInstance;
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;

    private static File schem;
    private static File levelLogF;
    private static File isDeleteF;

    private static FileConfiguration config;
    private static FileConfiguration levelLog;
    private static FileConfiguration isDelete;

    public static Logger logger;

    @Override
    public void onEnable ()
    {
        // Variable Initialisation
        logger = Bukkit.getLogger();
        mainInstance = this;

        // Methods
        createConfig();
        setupChat();
        setupPermissions();
        logger.warning("SkyBlock has been enabled.");

        if (!setupEconomy()){
            logger.severe(addPrefix("Disabled due to no Vault dependency found!"));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Command Executors
        this.getCommand("is").setExecutor(new IslandExecutor());
        this.getCommand("levelup").setExecutor(new LevelUpExecutor());
        this.getCommand("shop").setExecutor(new ShopExecutor());
        this.getCommand("setlevel").setExecutor(new SetLevelExecutor());

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new CobbleGenListener(), this);
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

        isDeleteF = new File(getDataFolder(), "isdelete.yml");
        if (!isDeleteF.exists())
        {
            isDeleteF.getParentFile().mkdirs();
            try
            {
                isDeleteF.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        isDelete = new YamlConfiguration();

        try
        {
            isDelete.load(isDeleteF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static FileConfiguration getPluginConfig ()
    {
        return config;
    }
    public static FileConfiguration getLevelLog ()
    {
        return levelLog;
    }
    public static FileConfiguration getIsDelete ()
    {
        return isDelete;
    }

    public static File getLevelLogF ()
    {
        return levelLogF;
    }
    public static File getIsDeleteF ()
    {
        return isDeleteF;
    }
    public static File getIslandSchematic ()
    {
        return schem;
    }
}
