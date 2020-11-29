package com.occydaboss.skyblock.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopLore
{
    public static ArrayList<String> getLore (float price)
    {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Buy Price: $" + String.valueOf(price) + " / Sell Price: $" + String.valueOf(price/2));
        lore.add(ChatColor.YELLOW + "LMB to buy, MMB to sell inventory, RMB to sell");
        return lore;
    }
}
