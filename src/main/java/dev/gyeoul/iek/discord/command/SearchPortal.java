package dev.gyeoul.iek.discord.command;

import dev.gyeoul.iek.api.domain.response.PortalQuery;
import dev.gyeoul.iek.api.service.PortalService;
import dev.gyeoul.iek.util.UrlGenerator;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 포탈 검색 명령어를 처리하는 클래스
 */
@Slf4j
@Component
public class SearchPortal extends Command {
    private final PortalService service;

    public SearchPortal(PortalService service) {
        super("search", "검색");
        this.service = service;
    }

    /**
     * 명령어 옵션 설정
     * - portal: 포탈명으로 정확한 검색
     * - similar: 유사한 포탈명으로 검색
     */
    @Override
    void addOption() {
        var likeSearch = createSubcommand("portal", "포탈명");
        var similarSearch = createSubcommand("similar", "유사 검색");

        this.addSubcommands(likeSearch, similarSearch);
    }

    /**
     * 서브커맨드 생성 헬퍼 메소드
     */
    private SubcommandData createSubcommand(String name, String description) {
        return new SubcommandData(name, description)
                .addOption(OptionType.STRING, "name", "포탈명", true, false);
    }

    /**
     * 명령어 실행 처리
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        var cmd = event.getFullCommandName();
        log.info("Executing command: {}", cmd);

        var response = switch (cmd) {
            case "search portal" -> searchPortalsByName(getNameOption(event));
            case "search similar" -> searchSimilarPortalsByName(getNameOption(event));
            default -> MessageCreateData.fromContent("잘못된 명령어입니다");
        };

        event.reply(response).queue();
    }

    /**
     * 이벤트에서 name 옵션 추출
     */
    private String getNameOption(SlashCommandInteractionEvent event) {
        return event.getOption("name", OptionMapping::getAsString);
    }

    /**
     * 유사 포탈 검색
     */
    private MessageCreateData searchSimilarPortalsByName(String query) {
        var results = service.searchSimilarPortalsByName(query);
        return createSearchResponse(query, results);
    }

    /**
     * 정확한 포탈명 검색
     */
    private MessageCreateData searchPortalsByName(String query) {
        var results = service.searchPortalsByName(query);
        return results.isEmpty()
                ? MessageCreateData.fromContent("검색결과가 없습니다")
                : createSearchResponse(query, results);
    }

    /**
     * 검색 결과를 임베드 메시지로 변환
     */
    @NotNull
    private MessageCreateData createSearchResponse(String query, List<PortalQuery> portals) {
        return new MessageCreateBuilder()
                .addContent("`%s` 검색결과".formatted(query))
                .addEmbeds(portals.stream()
                        .map(this::createPortalEmbed)
                        .map(EmbedBuilder::build)
                        .toList())
                .build();
    }

    /**
     * 포탈 정보로 임베드 생성
     */
    private EmbedBuilder createPortalEmbed(PortalQuery portal) {
        return new EmbedBuilder()
                .setTitle(portal.getName())
                .setThumbnail(portal.getImage())
                .addField("", createScannerLink(portal), true)
                .addField("", createIntelMapLink(portal), true);
    }

    /**
     * 스캐너 링크 생성
     */
    private String createScannerLink(PortalQuery portal) {
        var url = UrlGenerator.builder()
                .type(UrlGenerator.Type.PORTAL)
                .link(convertGuid(portal.getGuid()))
                .lat(portal.getLat())
                .lng(portal.getLng())
                .build()
                .generate();
        return "[스캐너](" + url + ")";
    }

    /**
     * 인텔맵 링크 생성
     */
    private String createIntelMapLink(PortalQuery portal) {
        var url = UrlGenerator.builder()
                .type(UrlGenerator.Type.INTEL_MAP)
                .lat(portal.getLat())
                .lng(portal.getLng())
                .build()
                .generate();
        return "[인텔맵](" + url + ")";
    }

    /**
     * GUID 변환
     */
    private String convertGuid(String guid) {
        return guid.replace("-", "") + ".16";
    }
}
