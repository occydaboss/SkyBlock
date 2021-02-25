package com.occydaboss.skyblock.util;

import com.occydaboss.skyblock.SkyBlock;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Level
{
    public static int getPlayerLevel (Player player)
    {
        return SkyBlock.getLevelLog().getInt(player.getUniqueId().toString()+".main");
    }

    public static void setPlayerLevel (Player player, int level)
    {
        SkyBlock.getLevelLog().set(player.getUniqueId().toString()+".main", level);
        try
        {
            SkyBlock.getLevelLog().save(SkyBlock.getLevelLogF());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static double getSubLevel (Player player, String type)
    {
        return SkyBlock.getLevelLog().getDouble(player.getUniqueId().toString()+"."+type);
    }

    public static void setSubLevel (Player player, double level, String type)
    {
        SkyBlock.getLevelLog().set(player.getUniqueId().toString()+"."+type, level);
        try
        {
            SkyBlock.getLevelLog().save(SkyBlock.getLevelLogF());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
