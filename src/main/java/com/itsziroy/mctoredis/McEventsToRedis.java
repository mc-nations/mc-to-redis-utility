package com.itsziroy.mctoredis;

import com.itsziroy.bukkitredis.BukkitRedisPlugin;
import com.itsziroy.mctoredis.listeners.DiscordSRVListener;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McEventsToRedis extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BukkitRedisPlugin bukkitRedis = (BukkitRedisPlugin) Bukkit.getPluginManager().getPlugin("BukkitRedis");


        DiscordSRV discordSRV =  (DiscordSRV)  Bukkit.getPluginManager().getPlugin("DiscordSRV");
        if(discordSRV != null) {
            DiscordSRV.api.subscribe(new DiscordSRVListener(discordSRV, bukkitRedis));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
