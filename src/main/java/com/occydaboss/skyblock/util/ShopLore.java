package com.occydaboss.skyblock.util;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopLore
{
    public static ArrayList<String> getLore (float price)
    {
        DecimalFormat df = new DecimalFormat("#####.##");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Buy Price: $" + String.valueOf(df.format(price)) + " / Sell Price: $" + String.valueOf(df.format(price/2)));
        lore.add(ChatColor.YELLOW + "LMB to buy, MMB to sell inventory, RMB to sell");
        return lore;
    }

    public static ArrayList<String> noSellLore (float price)
    {
        DecimalFormat df = new DecimalFormat("#####.##");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Buy Price: $" + String.valueOf(df.format(price)) + " / You cannot sell this item");
        lore.add(ChatColor.YELLOW + "LMB to buy");
        return lore;
    }
}
