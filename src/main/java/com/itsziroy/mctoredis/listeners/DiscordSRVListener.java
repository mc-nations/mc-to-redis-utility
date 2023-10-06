package com.itsziroy.mctoredis.listeners;

import com.itsziroy.bukkitredis.BukkitRedisPlugin;
import com.itsziroy.mctoredis.events.discordsrv.DiscordAccountLinkedEvent;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.DummyListener;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DiscordSRVListener {

    private DiscordSRV discordSRV;

    private BukkitRedisPlugin bukkitRedis;

    public DiscordSRVListener(DiscordSRV discordSRV, BukkitRedisPlugin bukkitRedis) {
        this.bukkitRedis = bukkitRedis;
        this.discordSRV = discordSRV;

    }

    @Subscribe
    public void onAccountLinked(AccountLinkedEvent event) {
        bukkitRedis.getMessanger().send(new DiscordAccountLinkedEvent(event.getPlayer(), discordSRV.getAccountLinkManager().getDiscordId(event.getPlayer().getUniqueId())));
    }
}
