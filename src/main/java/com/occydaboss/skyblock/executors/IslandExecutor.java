package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.EmptyChunkGenerator;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class IslandExecutor implements CommandExecutor
{
    Logger logger = Bukkit.getLogger();
    File schem;
    String locString;
    String cLocString;
    boolean rain;
    long time;
    BlockVector3 location;
    int cX;
    int cY;
    int cZ;

    public static List<Block> blocksFromTwoPoints(Location loc1, Location loc2)
    {
        List<Block> blocks = new ArrayList<Block>();

        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);

                    blocks.add(block);
                }
            }
        }

        return blocks;
    }


    private void generate (File file, BlockVector3 location, int x, int y, int z, long time, boolean rain, Player player)
    {
        player.sendMessage(addPrefix("Creating Island..."));
        logger.info("Creating world...");
        WorldCreator wc = new WorldCreator(player.getUniqueId().toString());
        wc.generator("VoidGenerator:" + SkyBlock.getPluginConfig().getString("biome"));
        World world = wc.createWorld();

        List<Player> players = world.getPlayers();

        for (Player player_ : players)
        {
            player_.teleport(new Location(Bukkit.getWorld("world"),
                    Bukkit.getWorld("world").getSpawnLocation().getX(),
                    Bukkit.getWorld("world").getSpawnLocation().getY(),
                    Bukkit.getWorld("world").getSpawnLocation().getZ()));
        }

        try {
            world.getChunkAt(new Location(world, 0, 64, 0)).load();
            if (world.getChunkAt(new Location(world, 0, 64, 0)).isLoaded()) {
                logger.info("Pasting schematic...");
                EditSession editSession = ClipboardFormats.findByFile(file).load(file).paste(BukkitAdapter.adapt(world), location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        world.setTime(time);

        logger.info("Setting weather...");
        world.setStorm(rain);

        logger.info("Setting chest...");
        Block block = world.getBlockAt(x, y, z);
        block.setType(Material.CHEST);

        Chest chest = (Chest) block.getState();
        Inventory inventory = chest.getBlockInventory();

        ItemStack[] items = {
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CARROT, 8), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.CACTUS, 8), new ItemStack(Material.PUMPKIN_SEEDS, 8), new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.MELON_SEEDS, 8), new ItemStack(Material.COCOA_BEANS, 8), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.POTATO, 8), new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
        };

        logger.info("Adding inventory...");
        inventory.setContents(items);

        logger.info("Adding border...");
        world.getWorldBorder().setSize(SkyBlock.getPluginConfig().getDouble("borderRadius"));

        player.sendMessage(addPrefix("Done! Teleporting..."));
        player.teleport(new Location(world, location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean onCommand (CommandSender commandSender, Command cmd, String name, String[] args)
    {
        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(addPrefix("This command can only be executed by a player!"));
            return true;
        }

        Player player = (Player) commandSender;

        if (!SkyBlock.getPluginConfig().getBoolean("useDefaultSchematic"))
        {
            if (!SkyBlock.getPluginConfig().getString("schemPath").equals(""))
            {
                if (new File(SkyBlock.getPluginConfig().getString("schemPath")).exists())
                {
                    schem = new File(SkyBlock.getPluginConfig().getString("schemPath"));
                }

            }
            else
            {
                player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
                logger.severe("useDefaultSchematic is set to false, however, schemPath is blank!");
            }
        }
        else
        {
            schem = SkyBlock.getIslandSchematic();
        }

        if (!SkyBlock.getPluginConfig().getString("schemLoc").equals(""))
        {
            locString = SkyBlock.getPluginConfig().getString("schemLoc");
        }
        else
        {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("schemLoc is not set to anything!");
        }

        if (!SkyBlock.getPluginConfig().getString("chestLoc").equals(""))
        {
            cLocString = SkyBlock.getPluginConfig().getString("chestLoc");
        }
        else
        {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("chestLoc is not set to anything!");
        }

        if (!SkyBlock.getPluginConfig().getBoolean("rain"))
        {
            rain = false;
        }
        else
        {
            rain = true;
        }

        time = SkyBlock.getPluginConfig().getLong("time");

        String[] locSplit = locString.split(",");
        if (locSplit.length != 3) {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("schemLoc must be set to the format x,y,z");
        }

        try
        {
            location = BlockVector3.at(Double.parseDouble(locSplit[0]), Double.parseDouble(locSplit[1]), Double.parseDouble(locSplit[2]));
            logger.info(locString);
        }
        catch (NumberFormatException e)
        {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("schemLoc contain numbers!");
        }

        String[] cLocSplit = cLocString.split(",");
        if (cLocSplit.length != 3) {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("chestLoc must be set to the format x,y,z");
        }

        try
        {
            cX = Integer.parseInt(cLocSplit[0]);
            cY = Integer.parseInt(cLocSplit[1]);
            cZ = Integer.parseInt(cLocSplit[2]);
        }
        catch (NumberFormatException e)
        {
            player.sendMessage(addPrefix(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "An error occured. Please report this to the moderators."));
            logger.severe("chestLoc contain numbers!");
        }


        if (args.length <= 0)
        {
            if (new File(player.getUniqueId().toString()).exists())
            {
                logger.info(player.getUniqueId().toString());
                logger.info(Bukkit.getWorlds().toString());
                logger.info(location.getX() + "," + location.getY() + "," + location.getZ());

                player.teleport(new Location(Bukkit.getWorld(player.getUniqueId().toString()), location.getX(), location.getY(), location.getZ()));
                player.sendMessage(addPrefix("Teleporting..."));
            }
            else
            {
                player.sendMessage(addPrefix("You don't have an island! Create one with /is create."));
                return true;
            }
        }
        else
        {
            if (args[0].equals("create"))
            {
                if (new File(player.getUniqueId().toString()).exists())
                {
                    player.sendMessage(addPrefix("You already have an island! Teleport to it with /is."));
                }
                else
                {
                    generate(schem, location, cX, cY, cZ, time, rain, player);
                }
            }
            else if (args[0].equals("reset"))
            {
                if (new File(player.getUniqueId().toString()).exists())
                {
                    SkyBlock.getIsDelete().set(player.getUniqueId().toString()+".check", true);
                    SkyBlock.getIsDelete().set(player.getUniqueId().toString()+".time", System.currentTimeMillis());
                    try
                    {
                        SkyBlock.getIsDelete().save(SkyBlock.getIsDeleteF());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    player.sendMessage(addPrefix("Are you sure you want to reset your island? Confirm using /is confirm in the next 30 seconds."));

                    BukkitRunnable runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            SkyBlock.getIsDelete().set(player.getUniqueId().toString()+".check", false);
                            try
                            {
                                SkyBlock.getIsDelete().save(SkyBlock.getIsDeleteF());
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    };
                    runnable.runTaskLater(SkyBlock.mainInstance, 200);
                }
                else
                {
                    player.sendMessage(addPrefix("You don't have an island! Create one with /is create."));
                }
            }
            else if (args[0].equals("confirm"))
            {
                if (new File(player.getUniqueId().toString()).exists())
                {
                    if (SkyBlock.getIsDelete().getBoolean(player.getUniqueId().toString()+".check") &&
                            System.currentTimeMillis() - SkyBlock.getIsDelete().getLong(player.getUniqueId().toString()+".time") <= 30000)
                    {
                        SkyBlock.getIsDelete().set(player.getUniqueId().toString()+".check", false);
                        try
                        {
                            SkyBlock.getIsDelete().save(SkyBlock.getIsDeleteF());
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        List<Player> players = Bukkit.getWorld(player.getUniqueId().toString()).getPlayers();

                        for (Player player_ : players)
                        {
                            player_.teleport(new Location(Bukkit.getWorld("world"),
                                    Bukkit.getWorld("world").getSpawnLocation().getX(),
                                    Bukkit.getWorld("world").getSpawnLocation().getY(),
                                    Bukkit.getWorld("world").getSpawnLocation().getZ()));
                        }
                        Bukkit.unloadWorld(player.getUniqueId().toString(), true);
                        try
                        {
                            FileUtils.deleteDirectory(new File(player.getUniqueId().toString()));
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        generate(schem, location, cX, cY, cZ, time, rain, player);
                    }
                    else
                    {
                        player.sendMessage(addPrefix("You didn't type this within 10 seconds of typing /is reset!"));
                    }
                }
                else
                {
                    player.sendMessage(addPrefix("You don't have an island! Create one with /is create."));
                }
            }
        }



        return true;
    }
}
