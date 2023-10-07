package com.itsziroy.mctoredis.events.discordsrv;

import org.bukkit.OfflinePlayer;

public class DiscordAccountLinkedEvent extends DiscordBaseEvent {

    public DiscordAccountLinkedEvent(OfflinePlayer player, String discord_id) {
        super("discord_account_linked", player, discord_id);
    }
}
