package com.itsziroy.mctoredis.listeners;


import com.itsziroy.bukkitredis.events.entity.PlayerDiedEvent;
import com.itsziroy.bukkitredis.events.player.PlayerEvent;
import com.itsziroy.mctoredis.McEventsToRedis;

import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BukkitListener implements Listener {

    McEventsToRedis plugin;

    public BukkitListener(McEventsToRedis plugin) {
        this.plugin = plugin;
    }

    public void onPlayerDeath(PlayerDiedEvent event) {
        PlayerDeathEvent original = (PlayerDeathEvent) event.getOriginal();

        Player player = original.getEntity();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(player.getUniqueId());
        event.put("discord_user", new DiscordUser(discord_id));
    }

    public void onPlayerEvent(PlayerEvent<?> event) {
        org.bukkit.event.player.PlayerEvent original = (org.bukkit.event.player.PlayerEvent) event.getOriginal();

        Player player = original.getPlayer();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(player.getUniqueId());
        event.put("discord_user", new DiscordUser(discord_id));
    }
}
