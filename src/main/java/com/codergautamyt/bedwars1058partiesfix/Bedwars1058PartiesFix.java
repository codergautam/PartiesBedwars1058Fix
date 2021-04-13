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
            getLogger().info("");
            if (getServer().getPluginManager().getPlugin("Parties") != null) {
                if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                    BedWars bedwarsAPI = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
                    getLogger().info("BedWars1058 and Parties was found. Injecting...");
                    Bukkit.getPluginManager().registerEvents(new OnJoinArena(), this);
                } else {
                    getLogger().severe("Parties wasnt found... disabling");
                    setEnabled(false);
                }
            } else {
                getLogger().severe("Parties wasnt found... disabling");
                setEnabled(false);
            }

        } else {
            getLogger().severe("BedWars1058 wasnt found... disabling");
            setEnabled(false);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
