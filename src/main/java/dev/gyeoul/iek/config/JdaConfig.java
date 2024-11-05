package dev.gyeoul.iek.config;

import dev.gyeoul.iek.discord.SlashCommandListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JdaConfig {
    @Value("${discord.bot.token}")
    private String token;
    private final SlashCommandListener commandListener;

    @Bean
    public JDA jda() {
        return JDABuilder.createDefault(token)
                .enableIntents(List.of(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT
                ))
                .addEventListeners(commandListener)
                .build();
    }
}