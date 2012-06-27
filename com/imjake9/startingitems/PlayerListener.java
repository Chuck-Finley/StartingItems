package com.imjake9.startingitems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerListener implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getFirstPlayed() == 0 || System.currentTimeMillis() - e.getPlayer().getFirstPlayed() < 1000) {
            e.getPlayer().getInventory().addItem(StartingItems.getPlugin().getLoginItems().toArray(new ItemStack[0]));
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        e.getPlayer().getInventory().addItem(StartingItems.getPlugin().getRespawnItems().toArray(new ItemStack[0]));
    }
    
}
