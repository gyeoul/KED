package dev.gyeoul.iek.discord.service;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscordUtilService {
    private final JDA jda;

    public DiscordUtilService(JDA jda) {
        this.jda = jda;
    }

    public boolean isConnected() {
        try {
            jda.awaitStatus(JDA.Status.CONNECTED);
            return true;
        } catch (InterruptedException e) {
            log.error("Discord is not connected.");
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
