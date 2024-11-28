package dev.gyeoul.iek.api.controller;

import dev.gyeoul.iek.discord.service.DiscordMemberService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discord")
@RequiredArgsConstructor
public class DiscordController {
    private final DiscordMemberService discordMemberService;

    @GetMapping("/members/{guildId}")
    public List<String> getMembers(@PathVariable String guildId) {
        return discordMemberService.getAllMembersFromGuild(guildId)
                .stream().map(Member::getEffectiveName).toList();
    }
}