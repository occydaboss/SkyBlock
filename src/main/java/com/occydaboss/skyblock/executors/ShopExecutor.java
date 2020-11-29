package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.guis.MainMenuGUI;
import com.occydaboss.skyblock.util.Level;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class ShopExecutor implements CommandExecutor
{
    Logger logger = Bukkit.getLogger();

    @Override
    public boolean onCommand (CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(addPrefix("This command can only be executed by a player!"));
            return true;
        }

        Player player = (Player) commandSender;

        logger.info(player.getName() + " opened the shop menu level " + Level.getPlayerLevel(player));

        if (Level.getPlayerLevel(player) == 0) {
            player.openInventory(MainMenuGUI.getLevel0(player));
        }
        else if (Level.getPlayerLevel(player) == 1)
        {
            player.openInventory(MainMenuGUI.getLevel1(player));
        }


        return true;
    }
}
