package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.EmptyChunkGenerator;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
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

    private void generate (File file, BlockVector3 location, int x, int y, int z, long time, boolean rain, Player player)
    {
        player.sendMessage(addPrefix("Creating Island..."));
        logger.info("Creating world...");
        WorldCreator wc = new WorldCreator(player.getUniqueId().toString());
        wc.generator(new EmptyChunkGenerator());
        World world = wc.createWorld();

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
        if (rain == false)
        {
            world.setStorm(false);
        }
        else
        {
            world.setStorm(true);
        }

        logger.info("Setting chest...");
        Block block = world.getBlockAt(x, y, z);
        block.setType(Material.CHEST);

        Chest chest = (Chest) block.getState();
        Inventory inventory = chest.getBlockInventory();

        ItemStack[] items = {
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CARROT, 8), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.PUMPKIN_SEEDS, 8), new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.MELON_SEEDS, 8), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.POTATO, 8), new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
        };

        logger.info("Adding inventory...");
        inventory.setContents(items);

        player.sendMessage(addPrefix("Done! Teleporting..."));
        player.teleport(new Location(world, location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String name, String[] args) {
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

        generate(schem, location, cX, cY, cZ, time, rain, player);

        return true;
    }
}
