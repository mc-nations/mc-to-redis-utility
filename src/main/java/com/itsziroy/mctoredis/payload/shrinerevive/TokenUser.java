package com.itsziroy.mctoredis.payload.shrinerevive;

import com.itsziroy.mctoredis.payload.discordsrv.DiscordUser;
import com.itsziroy.mctoredis.payload.discordsrv.MinecraftUser;

public record TokenUser(MinecraftUser minecraft_user, DiscordUser discord_user) {
}
