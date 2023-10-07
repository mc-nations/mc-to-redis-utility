package com.itsziroy.mctoredis.events.discordsrv;

import com.itsziroy.bukkitredis.events.Event;
import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import com.itsziroy.mctoredis.payload.discordsrv.MinecraftUser;
import org.bukkit.OfflinePlayer;

public class DiscordBaseEvent extends Event {

    public DiscordUser discord_user;
    public MinecraftUser minecraft_user;
    public DiscordBaseEvent(String name, OfflinePlayer player, String discord_id) {
        super(name);
        discord_user = new DiscordUser(discord_id);
        minecraft_user = new MinecraftUser(player.getUniqueId().toString(), player.getName());
    }
}
