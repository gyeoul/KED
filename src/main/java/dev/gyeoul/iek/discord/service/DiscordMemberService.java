package dev.gyeoul.iek.discord.service;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.utils.concurrent.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DiscordMemberService {
    private final JDA jda;

    public DiscordMemberService(JDA jda) {
        this.jda = jda;
    }

    /**
     * 특정 길드의 모든 멤버를 조회합니다.
     *
     * @param guildId 길드 ID
     * @return 멤버 목록, 길드를 찾을 수 없거나 오류 발생시 빈 리스트 반환
     */
    public List<Member> getAllMembersFromGuild(String guildId) {
        return getGuildMembers(guildId);
    }

    /**
     * 길드 멤버를 조회하는 공통 메소드
     *
     * @param guildId 길드 ID
     * @return 멤버 목록
     */
    private List<Member> getGuildMembers(String guildId) {
        return findGuild(guildId)
                .map(Guild::loadMembers)
                .map(Task::get)
                .orElse(Collections.emptyList());
    }

    /**
     * 길드 ID로 길드를 찾습니다.
     *
     * @param guildId 길드 ID
     * @return Optional<Guild>
     */
    private Optional<Guild> findGuild(String guildId) {
        var guild = jda.getGuildById(guildId);
        if (guild == null) {
            log.warn("길드를 찾을 수 없습니다. ID: {}", guildId);
        }
        return Optional.ofNullable(guild);
    }
}
