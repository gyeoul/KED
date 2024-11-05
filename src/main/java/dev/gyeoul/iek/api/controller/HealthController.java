package dev.gyeoul.iek.api.controller;

import dev.gyeoul.iek.discord.service.DiscordUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {
    private final DiscordUtilService discordUtilService;

    @GetMapping("/discord")
    public String discordHealthCheck() {
        var status = discordUtilService.isConnected();
        if (status) return "OK";
        else return "FAIL";
    }

}
