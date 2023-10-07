package com.itsziroy.mctoredis;

import com.itsziroy.bukkitredis.BukkitRedisPlugin;
import com.itsziroy.bukkitredis.events.entity.PlayerDiedEvent;
import com.itsziroy.bukkitredis.events.player.PlayerEvent;
import com.itsziroy.mctoredis.listeners.BukkitListener;
import com.itsziroy.mctoredis.listeners.DiscordSRVListener;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class McEventsToRedis extends JavaPlugin {

    private BukkitRedisPlugin bukkitRedis;
    private DiscordSRV discordSRV;

    public BukkitRedisPlugin getBukkitRedis() {
        return bukkitRedis;
    }

    public DiscordSRV getDiscordSRV() {
        return discordSRV;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        bukkitRedis = (BukkitRedisPlugin) Bukkit.getPluginManager().getPlugin("BukkitRedis");


        discordSRV =  (DiscordSRV)  Bukkit.getPluginManager().getPlugin("DiscordSRV");

        BukkitListener bukkitListener = new BukkitListener(this);

        if(discordSRV != null) {
            DiscordSRV.api.subscribe(new DiscordSRVListener(discordSRV, bukkitRedis));

            // Register Event Listener for bukkit redis
            BukkitRedisPlugin.getEventManager().registerCallback(PlayerEvent.class, bukkitListener::onPlayerEvent);
            BukkitRedisPlugin.getEventManager().registerCallback(PlayerDiedEvent.class, bukkitListener::onPlayerDeath);
        }

        getServer().getPluginManager().registerEvents(new BukkitListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
