package com.occydaboss.skyblock.listeners;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.Level;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class CobbleGenListener implements Listener
{
    @EventHandler
    public void onFromTo(BlockFromToEvent event){
        Material type = event.getBlock().getType();
        if (type == Material.WATER || type == Material.LEGACY_STATIONARY_WATER || type == Material.LAVA || type == Material.LEGACY_STATIONARY_LAVA){
            Block b = event.getToBlock();
            if (b.getType() == Material.AIR){
                if (generatesCobble(type, b)){
                    event.setCancelled(true);

                    Random random = new Random();
                    int randomChance = random.nextInt(100);

                    Player player = Bukkit.getPlayer(UUID.fromString(b.getWorld().getName()));

                    BukkitRunnable cobble = new BukkitRunnable() {
                        @Override
                        public void run() {
                            b.getWorld().playSound(b.getLocation(),
                                    Sound.BLOCK_FIRE_EXTINGUISH,
                                    SoundCategory.BLOCKS,
                                    0.5F,
                                    1);


                            for (int counter = 0; counter < 8; ++counter)
                            {
                                b.getWorld().spawnParticle(Particle.SMOKE_LARGE,
                                        b.getX() + Math.random(),
                                        b.getY() + 1 + Math.random(),
                                        b.getZ() + Math.random(),
                                        1,
                                        0,
                                        0,
                                        0,
                                        0);
                            }

                            if (Level.getPlayerLevel(player) == 0)
                            {
                                if (randomChance <= 40) {
                                    b.setType(Material.COBBLESTONE);
                                } else if (randomChance <= 60) {
                                    b.setType(Material.SANDSTONE);
                                } else if (randomChance <= 75) {
                                    b.setType(Material.COAL_ORE);
                                } else if (randomChance <= 85) {
                                    b.setType(Material.IRON_ORE);
                                } else if (randomChance <= 90) {
                                    b.setType(Material.GOLD_ORE);
                                } else if (randomChance <= 95) {
                                    b.setType(Material.LAPIS_ORE);
                                } else if (randomChance <= 97) {
                                    b.setType(Material.REDSTONE_ORE);
                                } else if (randomChance <= 99) {
                                    b.setType(Material.EMERALD_ORE);
                                } else if (randomChance <= 100) {
                                    b.setType(Material.DIAMOND_ORE);
                                }
                            }
                            else if (Level.getPlayerLevel(player) == 1) {
                                if (randomChance <= 30) {
                                    b.setType(Material.COBBLESTONE);
                                } else if (randomChance <= 50) {
                                    b.setType(Material.SANDSTONE);
                                } else if (randomChance <= 65) {
                                    b.setType(Material.COAL_ORE);
                                } else if (randomChance <= 75) {
                                    b.setType(Material.IRON_ORE);
                                } else if (randomChance <= 85) {
                                    b.setType(Material.GOLD_ORE);
                                } else if (randomChance <= 90) {
                                    b.setType(Material.LAPIS_ORE);
                                } else if (randomChance <= 93) {
                                    b.setType(Material.REDSTONE_ORE);
                                } else if (randomChance <= 97) {
                                    b.setType(Material.EMERALD_ORE);
                                } else if (randomChance <= 100) {
                                    b.setType(Material.DIAMOND_ORE);
                                }
                            }
                            else if (Level.getPlayerLevel(player) == 2) {
                                if (randomChance <= 20) {
                                    b.setType(Material.COBBLESTONE);
                                } else if (randomChance <= 30) {
                                    b.setType(Material.SANDSTONE);
                                } else if (randomChance <= 40) {
                                    b.setType(Material.RED_SANDSTONE);
                                } else if (randomChance <= 65) {
                                    b.setType(Material.COAL_ORE);
                                } else if (randomChance <= 75) {
                                    b.setType(Material.IRON_ORE);
                                } else if (randomChance <= 85) {
                                    b.setType(Material.GOLD_ORE);
                                } else if (randomChance <= 90) {
                                    b.setType(Material.LAPIS_ORE);
                                } else if (randomChance <= 93) {
                                    b.setType(Material.REDSTONE_ORE);
                                } else if (randomChance <= 97) {
                                    b.setType(Material.EMERALD_ORE);
                                } else if (randomChance <= 100) {
                                    b.setType(Material.DIAMOND_ORE);
                                }
                            }
                        }
                    };
                    cobble.runTaskLater(SkyBlock.mainInstance, 5);
                }
            }
        }
    }

    private final BlockFace[] faces = new BlockFace[]{
            BlockFace.SELF,
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    };

    public boolean generatesCobble(Material type, Block b){
        Material mirrorID1 = (type == Material.WATER || type == Material.LEGACY_STATIONARY_WATER ? Material.LAVA : Material.WATER);
        Material mirrorID2 = (type == Material.WATER || type == Material.LEGACY_STATIONARY_WATER ? Material.LEGACY_STATIONARY_LAVA : Material.LEGACY_STATIONARY_WATER);
        for (BlockFace face : faces){
            Block r = b.getRelative(face, 1);
            if (r.getType() == mirrorID1 || r.getType() == mirrorID2){
                return true;
            }
        }
        return false;
    }
}
