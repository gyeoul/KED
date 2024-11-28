package dev.gyeoul.iek.config;

import dev.gyeoul.iek.discord.SlashCommandListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static net.dv8tion.jda.api.requests.GatewayIntent.*;

@Configuration
@RequiredArgsConstructor
public class JdaConfig {
    @Value("${discord.bot.token}")
    private String token;
    private final SlashCommandListener commandListener;

    @Bean
    public JDA jda() {
        return JDABuilder.createDefault(token)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(List.of(
                        GUILD_MESSAGES,
                        GUILD_MEMBERS,
                        GUILD_PRESENCES,
                        MESSAGE_CONTENT,
                        GUILD_VOICE_STATES
                ))
                .addEventListeners(commandListener)
                .build();
    }
}