package com.imjake9.startingitems;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class StartingItems extends JavaPlugin {
    
    private static StartingItems plugin;
    
    private PlayerListener playerListener;
    private List<ItemStack> loginItems;
    private List<ItemStack> respawnItems;
    
    public static StartingItems getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
        plugin = this;
        loadConfiguration();
        playerListener = new PlayerListener();
        getServer().getPluginManager().registerEvents(playerListener, this);
    }
    
    @Override
    public void onDisable() {
        plugin = null;
    }
    
    public List<ItemStack> getLoginItems() {
        return loginItems;
    }
    
    public List<ItemStack> getRespawnItems() {
        return respawnItems;
    }
    
    private void loadConfiguration() {
        loginItems = new ArrayList<ItemStack>();
        respawnItems = new ArrayList<ItemStack>();
        
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        config.addDefault("login-items", new ArrayList<String>());
        config.addDefault("respawn-items", new ArrayList<String>());
        List<String> loginList = config.getStringList("login-items");
        List<String> respawnList = config.getStringList("respawn-items");
        
        for (String s : loginList) {
            ItemStack item = parseItem(s);
            if (item != null) loginItems.add(item);
        }
        for (String s : respawnList) {
            ItemStack item = parseItem(s);
            if (item != null) respawnItems.add(item);
        }
        saveConfig();
    }
    
    private ItemStack parseItem(String s) {
        if (s == null || s.length() == 0) return null;
        String[] split = s.split(" ");
        String[] datum = split[0].split(":");
        int id, num = 1;
        short data = 0;
        try {
            id = Integer.parseInt(datum[0]);
            if (datum.length > 1) {
                data = Short.parseShort(datum[1]);
            }
            if (split.length > 1) {
                num = Integer.parseInt(split[1]);
            }
        } catch (NumberFormatException ex) {
            return null;
        }
        ItemStack item = new ItemStack(id, num, data);
        return item;
    }
    
}
