package com.occydaboss.skyblock.entities;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.AddPrefix;
import com.occydaboss.skyblock.util.CustomItems;
import com.occydaboss.skyblock.util.Level;
import net.royawesome.jlibnoise.module.combiner.Add;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class BobBoss implements Listener
{
    @EventHandler
    public void onSpawnEgg (PlayerInteractEvent e)
    {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR && e.getItem().getType() == Material.ROTTEN_FLESH && e.getItem().getItemMeta().getDisplayName() != null)
        {
            if (e.getItem().getItemMeta().getDisplayName().equals(CustomItems.bobSummon().getItemMeta().getDisplayName()) && Level.getPlayerLevel(e.getPlayer()) >= 1)
            {
                try
                {
                    if (e.getClickedBlock().getWorld() != null && !e.getClickedBlock().getWorld().getName().equals(e.getPlayer().getUniqueId().toString()))
                    {
                        e.getPlayer().sendMessage(AddPrefix.addPrefix("You can only spawn a boss on your island!"));
                        e.setCancelled(true);
                        return;
                    }
                }
                catch (NullPointerException ignored) {}

                Player player = e.getPlayer();
                player.getInventory().removeItem(CustomItems.bobSummon());
                Zombie bob = (Zombie) player.getWorld().spawnEntity(new Location(e.getClickedBlock().getLocation().getWorld(), e.getClickedBlock().getLocation().getX(), e.getClickedBlock().getLocation().getY()+2, e.getClickedBlock().getLocation().getZ()), EntityType.ZOMBIE);
                player.getWorld().strikeLightningEffect(e.getClickedBlock().getLocation());
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1f);
                bob.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + "Bob");
                bob.setCanPickupItems(false);
                bob.setAdult();
                Attributable bobAttributable = (Attributable) bob;
                AttributeInstance health = bobAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                AttributeInstance speed = bobAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                health.setBaseValue(100);
                speed.setBaseValue(0.3);

                bob.setHealth(100);

                KeyedBossBar healthBar = Bukkit.createBossBar(new NamespacedKey(SkyBlock.mainInstance, "bob_boss"), ChatColor.AQUA + "" + ChatColor.BOLD + "Bob", BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
                healthBar.setProgress(bob.getHealth() / 100);
                healthBar.addPlayer(player);

                EntityEquipment equipment = bob.getEquipment();
                equipment.setItemInMainHand(CustomItems.bobSword());
                equipment.setHelmet(CustomItems.bobHelmet());
                equipment.setChestplate(CustomItems.bobChestplate());
                equipment.setLeggings(CustomItems.bobLeggings());
                equipment.setBoots(CustomItems.bobBoots());

                player.sendMessage(AddPrefix.addPrefix("Bob has awoken!"));
            }
        }
    }

    @EventHandler
    public void onBobDamage (EntityDamageEvent e)
    {
        if (e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Bob"))
        {
            Bukkit.getBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance)).setProgress(((Zombie) e.getEntity()).getHealth() / 100);
        }
    }

    @EventHandler
    public void onBobDeath (EntityDeathEvent e)
    {
        if (e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Bob"))
        {
            if (Level.getPlayerLevel(e.getEntity().getKiller()) == 1)
            {
                if (Level.getSubLevel(e.getEntity().getKiller(), "combat") == 0)
                {
                    SkyBlock.getLevelLog().set(e.getEntity().getKiller().getUniqueId().toString()+".combat", 1);
                    try
                    {
                        SkyBlock.getLevelLog().save(SkyBlock.getLevelLogF());
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }
            }

            e.setDroppedExp(2000);
            e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
            e.getDrops().clear();
            e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());

            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),CustomItems.meatOfTheUndead);
            e.getEntity().getKiller().sendMessage(AddPrefix.addPrefix("Bob has been defeated!"));

            Bukkit.getBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance)).setVisible(false);
            Bukkit.removeBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance));
        }
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e)
    {
        if (e.getPlayer().getWorld() == Bukkit.getWorld(e.getPlayer().getUniqueId().toString()))
        {
            if (e.getPlayer().getWorld().getEntities() != null)
            {
                List<Entity> entities = e.getPlayer().getWorld().getEntities();
                for (Entity entity : entities)
                {
                    if (entity.getType() != EntityType.PLAYER)
                    {
                        entity.remove();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e)
    {
        if (e.getEntity().getWorld() == Bukkit.getWorld(e.getEntity().getUniqueId().toString()))
        {
            if (e.getEntity().getWorld().getEntities() != null)
            {
                List<Entity> entities = e.getEntity().getWorld().getEntities();
                for (Entity entity : entities)
                {
                    if (entity instanceof Zombie)
                    {
                        Zombie zombie = (Zombie) entity;
                        if (zombie.getCustomName() != null && zombie.getCustomName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Bob"))
                        {
                            if (Bukkit.getBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance)).isVisible())
                            {
                                Bukkit.getBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance)).setVisible(false);
                                Bukkit.removeBossBar(NamespacedKey.fromString("bob_boss", SkyBlock.mainInstance));
                            }
                            e.getEntity().sendMessage(AddPrefix.addPrefix("Bob has despawned!"));
                            entity.getWorld().strikeLightningEffect(entity.getLocation());
                        }
                    }

                    entity.remove();
                }
            }
        }
    }
}
