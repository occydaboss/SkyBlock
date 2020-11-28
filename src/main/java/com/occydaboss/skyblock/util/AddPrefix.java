package com.occydaboss.skyblock.util;

import com.occydaboss.skyblock.SkyBlock;

public class AddPrefix
{
    public static String addPrefix (String str)
    {
        if (SkyBlock.getPluginConfig().getBoolean("usePrefix"))
        {
            String prefix = SkyBlock.getPluginConfig().getString("prefix");
            return prefix + " " + str;
        }
        else
        {
            return str;
        }
    }
}
