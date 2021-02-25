package com.occydaboss.skyblock.util;

import com.occydaboss.skyblock.SkyBlock;
import org.bukkit.ChatColor;

public class AddPrefix
{
    public static String addPrefix (String str)
    {
        if (SkyBlock.getPluginConfig().getBoolean("usePrefix"))
        {
            String prefix = SkyBlock.getPluginConfig().getString("prefix");
            return prefix + " " + ChatColor.YELLOW + str;
        }
        else
        {
            return str;
        }
    }
}
