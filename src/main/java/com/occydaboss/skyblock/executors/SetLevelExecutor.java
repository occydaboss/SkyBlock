package com.occydaboss.skyblock.executors;

import com.occydaboss.skyblock.util.Level;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class SetLevelExecutor implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (args.length != 2)
        {
            return false;
        }

        Level.setPlayerLevel(Bukkit.getPlayer(args[0]), Integer.parseInt(args[1]));
        Bukkit.getPlayer(args[0]).sendMessage(addPrefix("You are now level " + Level.getPlayerLevel(Bukkit.getPlayer(args[0]))));
        Bukkit.getPlayer(args[0]).playSound(Bukkit.getPlayer(args[0]).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);

        return true;
    }
}
