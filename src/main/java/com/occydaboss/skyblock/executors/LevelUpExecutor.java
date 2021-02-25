package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.guis.LevelUpGUI;
import com.occydaboss.skyblock.util.CustomItems;
import com.occydaboss.skyblock.util.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class LevelUpExecutor implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(addPrefix("This command can only be executed by a player!"));
            return true;
        }

        Player player = (Player) commandSender;
        if (Level.getPlayerLevel(player) == 0)
        {
            player.openInventory(LevelUpGUI.getLevel0(player));
        }
        else if (Level.getPlayerLevel(player) == 1)
        {
            player.openInventory(LevelUpGUI.getLevel1(player));
        }

        return true;
    }
}
