package com.itsziroy.mctoredis;

import com.itsziroy.bukkitredis.BukkitRedisPlugin;
import com.itsziroy.bukkitredis.events.entity.PlayerDiedEvent;
import com.itsziroy.bukkitredis.events.player.PlayerEvent;
import com.itsziroy.mctoredis.listeners.BukkitListener;
import com.itsziroy.mctoredis.listeners.DiscordSRVListener;
import com.itsziroy.mctoredis.listeners.ShrineListener;
import com.itsziroy.shrinerevive.ShrineRevive;
import com.itsziroy.shrinerevive.events.ShrineOfflinePlayerBaseEvent;
import com.itsziroy.shrinerevive.events.ShrineRevivedPlayerEvent;
import com.itsziroy.shrinerevive.events.ShrineTokenPickedUpEvent;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class McEventsToRedis extends JavaPlugin {

    private BukkitRedisPlugin bukkitRedis;
    private DiscordSRV discordSRV;
    private ShrineRevive shrineRevive;

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
        shrineRevive =  (ShrineRevive)  Bukkit.getPluginManager().getPlugin("ShrineRevive");


        BukkitListener bukkitListener = new BukkitListener(this);
        ShrineListener shrineListener = new ShrineListener(this);

        if(discordSRV != null) {
            getLogger().info("Hooked into DiscordSRV");
            DiscordSRV.api.subscribe(new DiscordSRVListener(discordSRV, bukkitRedis));

            // Register Event Listener for bukkit redis
            BukkitRedisPlugin.getEventManager().registerCallback(PlayerEvent.class, bukkitListener::onPlayerEvent);
            BukkitRedisPlugin.getEventManager().registerCallback(PlayerDiedEvent.class, bukkitListener::onPlayerDeath);

            if(shrineRevive != null) {
                getLogger().info("Hooked into ShrineRevive");
                BukkitRedisPlugin.getEventManager().registerCallback(ShrineRevivedPlayerEvent.class, shrineListener::onShrineRevivedPlayerEvent);
                BukkitRedisPlugin.getEventManager().registerCallback(ShrineOfflinePlayerBaseEvent.class, shrineListener::onShrineOfflineBaseEvent);
                BukkitRedisPlugin.getEventManager().registerCallback(ShrineTokenPickedUpEvent.class, shrineListener::onShrineTokenPickedUp);
            }

}

        getServer().getPluginManager().registerEvents(new BukkitListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
