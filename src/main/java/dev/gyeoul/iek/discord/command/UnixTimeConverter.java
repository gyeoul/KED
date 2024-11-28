package dev.gyeoul.iek.discord.command;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class UnixTimeConverter extends Command {
    private static final String ISO_REGEX_FULL = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d\\.\\d+";
    private static final String ISO_REGEX_WITHOUT_MS = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d";
    private static final String ISO_REGEX_WITHOUT_SEC = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d";
    private static final String UNIXTIME = "unixtime";

    public UnixTimeConverter() {
        super(UNIXTIME, "시간 형식 변환");
    }

    @Override
    void addOption() {
        var toUTC = new SubcommandData("to_utc", "유닉스 시간을 UTC로 변환");
        var toKST = new SubcommandData("to_kst", "유닉스 시간을 한국 시간으로 변환");
        var fromISO = new SubcommandData("from_iso", "UTC시간을 unixtime으로 변환");

        toUTC.addOption(OptionType.INTEGER, UNIXTIME, "1765432198765 형식의 unixtime", true, false);
        toKST.addOption(OptionType.INTEGER, UNIXTIME, "1765432198765 형식의 unixtime", true, false);
        fromISO.addOption(OptionType.STRING, "iso", "2024-11-11T12:34:56.789 형식의 ISO 문자열", true, false);

        this.addSubcommands(toUTC, toKST, fromISO);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        var cmd = event.getFullCommandName();
        log.info("Executing command: {}", cmd);
        var response = switch (cmd) {
            case "unixtime to_utc" -> toUtc(event.getOption(UNIXTIME, OptionMapping::getAsLong));
            case "unixtime to_kst" -> toKst(event.getOption(UNIXTIME, OptionMapping::getAsLong));
            case "unixtime from_iso" -> fromIso(event.getOption("iso", OptionMapping::getAsString));
            default -> MessageCreateBuilder.from(MessageCreateData.fromContent("잘못된 명령어입니다"));
        };
        if (response != null) {
            event.reply(response.build()).queue();
        } else {
            event.reply("잘못된 명령어입니다").queue();
        }
    }

    private MessageCreateBuilder fromIso(String iso) {
        MessageCreateBuilder builder = new MessageCreateBuilder();

        if (iso == null || iso.isEmpty() || !(iso.matches(ISO_REGEX_FULL) || iso.matches(ISO_REGEX_WITHOUT_MS) || iso.matches(ISO_REGEX_WITHOUT_SEC))) {
            log.info("ISO Format: {}", iso);
            return builder.addContent("Invalid ISO date format.");
        }
        String message = String.format("UTC: `%s`", LocalDateTime.parse(iso, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toEpochSecond(ZoneOffset.UTC)) +
                String.format("KST: `%s`", LocalDateTime.parse(iso, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toEpochSecond(ZoneOffset.ofHours(9)));

        return builder.addContent(message);
    }

    private MessageCreateBuilder toKst(Long unixtime) {
        MessageCreateBuilder builder = new MessageCreateBuilder();
        if (unixtime == null) return builder.addContent("unixtime is null");
        if (unixtime < 0) return builder.addContent("unixtime is negative");
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixtime, 0, ZoneOffset.ofHours(9));
        return builder.addContent("KST: `"+ dateTime +"`");
    }

    private MessageCreateBuilder toUtc(Long unixtime) {
        MessageCreateBuilder builder = new MessageCreateBuilder();
        if (unixtime == null) return builder.addContent("unixtime is null");
        if (unixtime < 0) return builder.addContent("unixtime is negative");
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixtime, 0, ZoneOffset.UTC);
        return builder.addContent("UTC: `"+ dateTime +"`");
    }

}