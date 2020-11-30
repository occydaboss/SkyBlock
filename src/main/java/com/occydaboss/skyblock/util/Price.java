package com.occydaboss.skyblock.util;

import com.occydaboss.skyblock.SkyBlock;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Price
{
    public static float getPriceFromShopItem (ItemStack item)
    {
        String line = item.getItemMeta().getLore().get(0).split("/")[0];

        return Float.parseFloat(line.split("\\$")[1]);
    }

    public static float getSellFromShopItem (ItemStack item)
    {
        String line = item.getItemMeta().getLore().get(0).split("/")[1];

        return Float.parseFloat(line.split("\\$")[1]);
    }

}
