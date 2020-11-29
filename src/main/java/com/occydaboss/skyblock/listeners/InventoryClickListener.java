package com.occydaboss.skyblock.listeners;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.guis.BlocksGUI;
import com.occydaboss.skyblock.guis.BuySellGUI;
import com.occydaboss.skyblock.guis.MainMenuGUI;
import com.occydaboss.skyblock.util.Level;
import com.occydaboss.skyblock.util.Price;
import com.occydaboss.skyblock.util.TextCase;
import jdk.nashorn.internal.ir.Block;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;
import static com.occydaboss.skyblock.util.ShopItems.*;

public class InventoryClickListener implements Listener
{
    @EventHandler
    public void onInventoryClick (InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked();

        if (e.getCurrentItem().equals(exit) || e.getCurrentItem().equals(cancel))
        {
            player.closeInventory();
            if (Level.getPlayerLevel(player) == 0) {
                player.openInventory(MainMenuGUI.getLevel0(player));
            }
            else if (Level.getPlayerLevel(player) == 1)
            {
                player.openInventory(MainMenuGUI.getLevel1(player));
            }
            e.setCancelled(true);
        }

        // Buy Menu
        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Buy Menu"))
        {
            ItemStack item = e.getView().getItem(13);
            float price = Float.parseFloat(e.getView().getItem(22).getItemMeta().getDisplayName().split("\\$")[1]);

            if (e.getCurrentItem().equals(rem1))
            {
                if (item.getAmount() - 1 < 1)
                {
                    player.sendMessage(addPrefix(ChatColor.RED + "Cannot set item amount to below 1!"));
                    e.setCancelled(true);
                }

                item.setAmount(item.getAmount() - 1);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, price - Price.getPriceFromShopItem(item)));
            }
            else if (e.getCurrentItem().equals(rem10))
            {
                if (item.getAmount() - 10 < 1)
                {
                    player.sendMessage(addPrefix(ChatColor.RED + "Cannot set item amount to below 1!"));
                    e.setCancelled(true);
                }

                item.setAmount(item.getAmount() - 10);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, price - (10*Price.getPriceFromShopItem(item))));
            }
            else if (e.getCurrentItem().equals(add1))
            {
                if (item.getAmount() + 1 > 64)
                {
                    player.sendMessage(addPrefix(ChatColor.RED + "Cannot set item amount to above 64!"));
                    e.setCancelled(true);
                }

                item.setAmount(item.getAmount() + 1);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, price + Price.getPriceFromShopItem(item)));
            }
            else if (e.getCurrentItem().equals(add10))
            {
                if (item.getAmount() + 10 > 64)
                {
                    player.sendMessage(addPrefix(ChatColor.RED + "Cannot set item amount to above 64!"));
                    e.setCancelled(true);
                }

                item.setAmount(item.getAmount() + 10);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, price + (10*Price.getPriceFromShopItem(item))));
            }
            else if (e.getCurrentItem().equals(set1))
            {
                item.setAmount(1);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, Price.getPriceFromShopItem(item)));
            }
            else if (e.getCurrentItem().equals(set64))
            {
                item.setAmount(64);
                player.closeInventory();
                player.openInventory(BuySellGUI.getBuyMenu(player, item, (64*Price.getPriceFromShopItem(item))));
            }
            else if (e.getCurrentItem().equals(confirm))
            {
                int amount = item.getAmount();
                ItemStack cleanItem = new ItemStack(item.getType(), amount);

                if (!(SkyBlock.economy.getBalance(player) - price <= 0))
                {
                    player.getInventory().addItem(cleanItem);
                    SkyBlock.economy.withdrawPlayer(player, price);
                    player.sendMessage(addPrefix(ChatColor.GREEN + "Successfully bought " + String.valueOf(amount) + "x " + TextCase.setCase(String.valueOf(item.getType()).replaceAll("_", " ")) + " costing you $" + String.valueOf(price)));
                }
                else
                {
                    player.sendMessage(addPrefix(ChatColor.RED + "You do not have sufficient funds."));
                }

            }
            e.setCancelled(true);
        }

        // Main Menu
        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Main Menu"))
        {
            if (e.getCurrentItem().equals(blocksButton))
            {
                player.closeInventory();
                if (Level.getPlayerLevel(player) == 0) {
                    player.openInventory(BlocksGUI.getLevel0(player));
                }
                else if (Level.getPlayerLevel(player) == 1)
                {
                    player.openInventory(BlocksGUI.getLevel1(player));
                }
            }
            else if (e.getCurrentItem().equals(materialsButton))
            {
                player.closeInventory();
                if (Level.getPlayerLevel(player) == 0) {
                    player.openInventory(BlocksGUI.getLevel0(player));
                }
                else if (Level.getPlayerLevel(player) == 1)
                {
                    player.openInventory(BlocksGUI.getLevel1(player));
                }
            }
            if (e.getCurrentItem().equals(farmingButton))
            {
                player.closeInventory();
                if (Level.getPlayerLevel(player) == 0) {
                    player.openInventory(BlocksGUI.getLevel0(player));
                }
                else if (Level.getPlayerLevel(player) == 1)
                {
                    player.openInventory(BlocksGUI.getLevel1(player));
                }
            }
            e.setCancelled(true);
        }

        // Blocks
        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Blocks"))
        {
            float price = Price.getPriceFromShopItem(e.getCurrentItem());
            ItemMeta meta = e.getCurrentItem().getItemMeta();
            meta.setLore(Collections.singletonList(meta.getLore().get(0)));
            ItemStack item = e.getCurrentItem();
            item.setItemMeta(meta);

            player.closeInventory();
            player.openInventory(BuySellGUI.getBuyMenu(player, item, price));
            e.setCancelled(true);
        }
    }
}
