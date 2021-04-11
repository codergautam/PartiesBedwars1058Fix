package com.codergautamyt.bedwars1058partiesfix;

import com.andrei1058.bedwars.api.BedWars;
import com.codergautamyt.bedwars1058partiesfix.events.OnJoinArena;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars1058PartiesFix extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getPluginManager().isPluginEnabled("BedWars1058")) {
            if (getServer().getPluginManager().getPlugin("Parties") != null) {
                if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                    getLogger().severe("BedWars1058 or Parties was not found. Disabling...");
                    setEnabled(false);
                    Bukkit.getPluginManager().registerEvents(new OnJoinArena(), this);
                }
            }

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
