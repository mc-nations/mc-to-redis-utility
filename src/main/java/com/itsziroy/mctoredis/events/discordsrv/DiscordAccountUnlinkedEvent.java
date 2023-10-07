package com.itsziroy.mctoredis.events.discordsrv;

import org.bukkit.OfflinePlayer;

public class DiscordAccountUnlinkedEvent extends DiscordBaseEvent {

    public DiscordAccountUnlinkedEvent(OfflinePlayer player, String discord_id) {
        super("discord_account_unlinked", player, discord_id);
    }
}
