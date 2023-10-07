package com.itsziroy.mctoredis.listeners;


import com.itsziroy.bukkitredis.events.player.PlayerJoinEvent;
import com.itsziroy.bukkitredis.events.player.PlayerQuitEvent;
import com.itsziroy.mctoredis.McEventsToRedis;

import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BukkitListener implements Listener {

    McEventsToRedis plugin;

    public BukkitListener(McEventsToRedis plugin) {
        this.plugin = plugin;
    }


    public void onPlayerJoin(PlayerJoinEvent event) {
        org.bukkit.event.player.PlayerJoinEvent original = (org.bukkit.event.player.PlayerJoinEvent) event.getOriginal();
        Player player = original.getPlayer();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(player.getUniqueId());
        event.put("discord_user", new DiscordUser(discord_id));

    }


    public void onPlayerQuit(PlayerQuitEvent event) {
        org.bukkit.event.player.PlayerQuitEvent original = (org.bukkit.event.player.PlayerQuitEvent) event.getOriginal();

        Player player = original.getPlayer();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(player.getUniqueId());
        event.put("discord_user", new DiscordUser(discord_id));
    }
}
