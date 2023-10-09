package com.itsziroy.mctoredis.listeners;

import com.itsziroy.bukkitredis.events.SimplePlayer;
import com.itsziroy.mctoredis.EventKeys;
import com.itsziroy.mctoredis.McEventsToRedis;
import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import com.itsziroy.mctoredis.payload.discordsrv.MinecraftUser;
import com.itsziroy.mctoredis.payload.shrinerevive.TokenUser;
import com.itsziroy.shrinerevive.events.ShrineOfflinePlayerBaseEvent;
import com.itsziroy.shrinerevive.events.ShrineRevivedPlayerEvent;
import com.itsziroy.shrinerevive.events.ShrineTokenPickedUpEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ShrineListener{
    McEventsToRedis plugin;

    public ShrineListener(McEventsToRedis plugin) {
        this.plugin = plugin;
    }

    public void onShrineOfflineBaseEvent(ShrineOfflinePlayerBaseEvent event) {
        OfflinePlayer tokenPlayer = event.getTokenPlayer();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(tokenPlayer.getUniqueId());
        event.put(EventKeys.KEY_DISCORD_USER, new DiscordUser(discord_id));
    }

    public void onShrineRevivedPlayerEvent(ShrineRevivedPlayerEvent event) {
        SimplePlayer simplePlayer = event.getSimplePlayer();

        String discord_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(UUID.fromString(simplePlayer.id()));
        event.put(EventKeys.KEY_DISCORD_USER, new DiscordUser(discord_id));
    }

    public  void onShrineTokenPickedUp(ShrineTokenPickedUpEvent event) {
        Player actionPlayer = event.getActionPlayer();
        OfflinePlayer tokenPlayer = event.getTokenPlayer();
        event.remove(ShrineOfflinePlayerBaseEvent.KEY_TOKEN_USER);
        event.remove(EventKeys.KEY_DISCORD_USER);

        String action_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(actionPlayer.getUniqueId());
        String token_id = this.plugin.getDiscordSRV().getAccountLinkManager().getDiscordId(tokenPlayer.getUniqueId());

        MinecraftUser action_mc_user = new MinecraftUser(actionPlayer.getUniqueId().toString(), actionPlayer.getName());
        MinecraftUser token_mc_user = new MinecraftUser(tokenPlayer.getUniqueId().toString(), tokenPlayer.getName());

        event.put(EventKeys.KEY_ACTION_USER, new TokenUser(action_mc_user, new DiscordUser(action_id)));
        event.put(EventKeys.KEY_TOKEN_USER, new TokenUser(token_mc_user, new DiscordUser(token_id)));
    }
}
