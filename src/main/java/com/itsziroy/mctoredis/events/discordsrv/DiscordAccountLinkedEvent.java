package com.itsziroy.mctoredis.events.discordsrv;

import com.itsziroy.bukkitredis.events.Event;
import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import com.itsziroy.mctoredis.payload.discordsrv.MinecraftUser;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.OfflinePlayer;

public class DiscordAccountLinkedEvent extends Event {

    public DiscordUser discord_user;
    public MinecraftUser minecraft_user;
    public DiscordAccountLinkedEvent(OfflinePlayer player, String discord_id) {
        super("discord_account_linked");
         discord_user = new DiscordUser(discord_id);
         minecraft_user = new MinecraftUser(player.getUniqueId().toString(), player.getName());
    }
}
