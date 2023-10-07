package com.itsziroy.mctoredis.listeners;

import com.itsziroy.bukkitredis.BukkitRedisPlugin;
import com.itsziroy.mctoredis.events.discordsrv.DiscordAccountLinkedEvent;
import com.itsziroy.mctoredis.events.discordsrv.DiscordAccountUnlinkedEvent;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import github.scarsz.discordsrv.api.events.AccountUnlinkedEvent;


public class DiscordSRVListener {

    private final DiscordSRV discordSRV;

    private final BukkitRedisPlugin bukkitRedis;

    public DiscordSRVListener(DiscordSRV discordSRV, BukkitRedisPlugin bukkitRedis) {
        this.bukkitRedis = bukkitRedis;
        this.discordSRV = discordSRV;

    }

    @Subscribe
    public void onAccountLinked(AccountLinkedEvent event) {
        bukkitRedis.getMessanger().send(new DiscordAccountLinkedEvent(event.getPlayer(), discordSRV.getAccountLinkManager().getDiscordId(event.getPlayer().getUniqueId())));
    }
    @Subscribe
    public void onAccountUnlinked(AccountUnlinkedEvent event) {
        bukkitRedis.getMessanger().send(new DiscordAccountUnlinkedEvent(event.getPlayer(), event.getDiscordId()));
    }
}
