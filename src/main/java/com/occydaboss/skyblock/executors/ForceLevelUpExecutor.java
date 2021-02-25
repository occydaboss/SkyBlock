package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.util.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class ForceLevelUpExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand (CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (!(commandSender instanceof Player))
        {
            return true;
        }

        Player player = (Player) commandSender;
        Level.setPlayerLevel(player, Level.getPlayerLevel(player) + 1);
        player.sendMessage(addPrefix("You are now level " + Level.getPlayerLevel(player)));

        return true;
    }
}
