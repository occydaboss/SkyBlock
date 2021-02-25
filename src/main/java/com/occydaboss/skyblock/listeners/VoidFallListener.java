package com.occydaboss.skyblock.listeners;

import com.occydaboss.skyblock.SkyBlock;
import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static com.occydaboss.skyblock.util.AddPrefix.addPrefix;

public class VoidFallListener implements Listener
{
    @EventHandler
    public void onVoidFall (EntityDamageEvent e)
    {
        if (e.getEntityType().equals(EntityType.PLAYER))
        {
            Player player = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID)
            {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 255, true, false));
                Bukkit.dispatchCommand(player, "is");

                double health = e.getDamage();

                if (player.getHealth() <= 0)
                {
                    e.setCancelled(true);
                }

                BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if ((player.getHealth() + health) > 20)
                        {
                            player.setHealth(20);
                        }
                        else
                        {
                            player.setHealth(player.getHealth() + health);
                        }


                    }
                };
                runnable.runTaskLater(SkyBlock.mainInstance, 2);

            }
        }
        else if (e.getEntityType().equals(EntityType.ZOMBIE))
        {
            if (e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Bob"))
            {
                if (e.getCause() == EntityDamageEvent.DamageCause.VOID)
                {
                    Bukkit.getBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance)).setVisible(false);
                    Bukkit.removeBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance));
                }
            }
        }
    }
}
