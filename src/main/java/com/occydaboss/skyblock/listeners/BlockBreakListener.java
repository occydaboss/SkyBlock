package com.occydaboss.skyblock.listeners;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.Level;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener
{
    @EventHandler
    public void onBlockBreak (BlockBreakEvent e)
    {
        Player player = e.getPlayer();
        int level = Level.getPlayerLevel(player);
        Block block = e.getBlock();

        if (level == 0)
        {
            if (block.getType().equals(Material.COBBLESTONE))
            {
                if (Level.getSubLevel(player, "mining") < 100)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "mining") + 5, "mining");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GRAY + "+5 mining xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
            else if (block.getType().equals(Material.MELON) || block.getType().equals(Material.PUMPKIN))
            {
                if (Level.getSubLevel(player, "farming") < 100)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "farming") + 5, "farming");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "+5 farming xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
        }
        else if (level == 1)
        {
            if (block.getType().equals(Material.COBBLESTONE))
            {
                if (Level.getSubLevel(player, "mining") < 1000)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "mining") + 5, "mining");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GRAY + "+5 mining xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
            else if (block.getType().equals(Material.SANDSTONE))
            {
                if (Level.getSubLevel(player, "mining") < 1000)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "mining") + 7.5, "mining");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GRAY + "+7.5 mining xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
            else if (block.getType().equals(Material.SUGAR_CANE))
            {
                if (Level.getSubLevel(player, "farming") < 1000)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "farming") + 5, "farming");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "+5 farming xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
            else if (block.getType().equals(Material.MELON) || block.getType().equals(Material.PUMPKIN))
            {
                if (Level.getSubLevel(player, "farming") < 1000)
                {
                    Level.setSubLevel(player, Level.getSubLevel(player, "farming") + 7.5, "farming");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + "+5 farming xp"));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
        }
    }
}
