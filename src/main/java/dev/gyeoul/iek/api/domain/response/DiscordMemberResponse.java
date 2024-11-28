package dev.gyeoul.iek.api.domain.response;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;

@Getter
public class DiscordMemberResponse {

    public DiscordMemberResponse(@NotNull Member member) {
        member.getNickname();
        member.getRoles();
        member.getEffectiveName();
        member.getUser();
        member.getTimeJoined();
        member.getTimeBoosted();
        member.getOnlineStatus();

    }
}
