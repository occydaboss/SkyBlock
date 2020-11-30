package com.occydaboss.skyblock.listeners;

import com.occydaboss.skyblock.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        String id = player.getUniqueId().toString();
        new WorldCreator(id).createWorld();

        if (!SkyBlock.getLevelLog().contains(String.valueOf(player.getUniqueId())))
        {
            SkyBlock.mainInstance.getServer().broadcastMessage(addPrefix(ChatColor.GOLD + player.getName() + " joined for the first time! Give them a warm welcome!"));
            SkyBlock.getLevelLog().set(id, 0);
            try
            {
                SkyBlock.getLevelLog().save(SkyBlock.getLevelLogF());
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }

        if (new File(player.getUniqueId().toString()).exists())
        {

        }
    }
}
