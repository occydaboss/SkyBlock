package com.occydaboss.skyblock.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopItems
{
    public static final ItemStack filler = new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build();
    public static final ItemStack confirm = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName(ChatColor.GREEN + "Confirm Purchase").build();
    public static final ItemStack cancel = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "Cancel Purchase").build();

    public static final ItemStack add1 = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName(ChatColor.GREEN + "Add 1 Item").build();
    public static final ItemStack add10 = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName(ChatColor.GREEN + "Add 10 Items").build();
    public static final ItemStack set64 = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setDisplayName(ChatColor.GREEN + "Set to 64 Items").build();
    public static final ItemStack rem1 = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "Remove 1 Item").build();
    public static final ItemStack rem10 = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "Remove 10 Items").build();
    public static final ItemStack set1 = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "Set to 1 Item").build();

    public static final ItemStack exit = new ItemBuilder(Material.BARRIER).setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Exit").build();
    public static final ItemStack blocksButton = new ItemBuilder(Material.COBBLESTONE).setDisplayName(ChatColor.YELLOW + "Blocks").build();
    public static final ItemStack toolsButton = new ItemBuilder(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.YELLOW + "Tools").build();
    public static final ItemStack farmingButton = new ItemBuilder(Material.WHEAT).setDisplayName(ChatColor.YELLOW + "Farming").build();
    public static final ItemStack materialsButton = new ItemBuilder(Material.DIAMOND).setDisplayName(ChatColor.YELLOW + "Materials").build();
    public static final ItemStack redstoneButton = new ItemBuilder(Material.REDSTONE).setDisplayName(ChatColor.YELLOW + "Redstone").build();
    public static final ItemStack miscButton = new ItemBuilder(Material.BEACON).setDisplayName(ChatColor.YELLOW + "Redstone").build();
    public static final ItemStack bossButton = new ItemBuilder(Material.ROTTEN_FLESH).setDisplayName(ChatColor.YELLOW + "Boss summons").build();

    /*
    Level 0
     */

    // Blocks
    public static final ItemStack stone = new ItemBuilder(Material.STONE).setLore(ShopLore.getLore(2f)).build();
    public static final ItemStack cobblestone = new ItemBuilder(Material.COBBLESTONE).setLore(ShopLore.getLore(1f)).build();
    public static final ItemStack andesite = new ItemBuilder(Material.ANDESITE).setLore(ShopLore.getLore(3f)).build();
    public static final ItemStack diorite = new ItemBuilder(Material.DIORITE).setLore(ShopLore.getLore(3f)).build();
    public static final ItemStack granite = new ItemBuilder(Material.GRANITE).setLore(ShopLore.getLore(3f)).build();
    public static final ItemStack grass = new ItemBuilder(Material.GRASS_BLOCK).setLore(ShopLore.getLore(10f)).build();
    public static final ItemStack dirt = new ItemBuilder(Material.ANDESITE).setLore(ShopLore.getLore(2f)).build();
    public static final ItemStack oakPlanks = new ItemBuilder(Material.OAK_PLANKS).setLore(ShopLore.getLore(5f)).build();
    public static final ItemStack sprucePlanks = new ItemBuilder(Material.SPRUCE_PLANKS).setLore(ShopLore.getLore(5f)).build();
    public static final ItemStack birchPlanks = new ItemBuilder(Material.BIRCH_PLANKS).setLore(ShopLore.getLore(5f)).build();
    public static final ItemStack sand = new ItemBuilder(Material.SAND).setLore(ShopLore.getLore(6f)).build();
    public static final ItemStack gravel = new ItemBuilder(Material.GRAVEL).setLore(ShopLore.getLore(6f)).build();
    public static final ItemStack oakLog = new ItemBuilder(Material.OAK_LOG).setLore(ShopLore.getLore(7f)).build();
    public static final ItemStack spruceLog = new ItemBuilder(Material.SPRUCE_LOG).setLore(ShopLore.getLore(7f)).build();
    public static final ItemStack birchLog = new ItemBuilder(Material.BIRCH_LOG).setLore(ShopLore.getLore(7f)).build();
    public static final ItemStack sandstone = new ItemBuilder(Material.SANDSTONE).setLore(ShopLore.getLore(18f)).build();
    public static final ItemStack whiteWool = new ItemBuilder(Material.WHITE_WOOL).setLore(ShopLore.getLore(10f)).build();
    public static final ItemStack ice = new ItemBuilder(Material.ICE).setLore(ShopLore.getLore(15f)).build();

     /*
    Level 1
     */

    // Blocks
    public static final ItemStack oakLeaves = new ItemBuilder(Material.OAK_LEAVES).setLore(ShopLore.getLore(8)).build();
    public static final ItemStack spruceLeaves = new ItemBuilder(Material.SPRUCE_LEAVES).setLore(ShopLore.getLore(8)).build();
    public static final ItemStack birchLeaves = new ItemBuilder(Material.BIRCH_LEAVES).setLore(ShopLore.getLore(8)).build();
    public static final ItemStack redSand = new ItemBuilder(Material.RED_SAND).setLore(ShopLore.getLore(10)).build();
    public static final ItemStack stoneBrick = new ItemBuilder(Material.STONE_BRICKS).setLore(ShopLore.getLore(8)).build();
    public static final ItemStack redSandstone = new ItemBuilder(Material.RED_SANDSTONE).setLore(ShopLore.getLore(5)).build();
}
