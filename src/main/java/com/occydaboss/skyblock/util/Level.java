package com.occydaboss.skyblock.util;

import com.occydaboss.skyblock.SkyBlock;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Level
{
    public static int getPlayerLevel (Player player)
    {
        return SkyBlock.getLevelLog().getInt(player.getUniqueId().toString());
    }

    public static void setPlayerLevel (Player player, int level)
    {
        SkyBlock.getLevelLog().set(player.getUniqueId().toString(), level);
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
