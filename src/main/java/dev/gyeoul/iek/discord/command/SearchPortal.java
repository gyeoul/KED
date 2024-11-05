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


@Slf4j
@Component
public class SearchPortal extends Command {
    private final PortalService service;

    public SearchPortal(
            PortalService service) {
        super("search", "검색");
        this.service = service;
    }

    @Override
    void addOption() {
        var likeSearch = new SubcommandData("portal","포탈명");
        var similarSearch = new SubcommandData("similar","유사 검색");
        likeSearch.addOption(OptionType.STRING, "name", "포탈명", true, false);
        similarSearch.addOption(OptionType.STRING, "name", "포탈명", true, false);
        this.addSubcommands(likeSearch);
        this.addSubcommands(similarSearch);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        var cmd = event.getFullCommandName();
        log.info(cmd);
        switch (cmd) {
            case "search portal" -> event.reply(
                    searchPortalsByName(
                            event.getOption("name", OptionMapping::getAsString)
                    )).queue();
            case "search similar" -> event.reply(
                    searchSimilarPortalsByName(
                            event.getOption("name", OptionMapping::getAsString)
                    )).queue();
            default -> event.reply("").queue();
        }
    }

    private MessageCreateData searchSimilarPortalsByName(String query) {
        var list = service.searchSimilarPortalsByName(query);
        return portalSearchEmbed(query, list);
    }

    private MessageCreateData searchPortalsByName(String query) {
        var list = service.searchPortalsByName(query);
        if (list.isEmpty()) {
            return MessageCreateData.fromContent("검색결과가 없습니다");
        }
        return portalSearchEmbed(query, list);
    }

    @NotNull
    private MessageCreateData portalSearchEmbed(String query, List<PortalQuery> list) {
        return new MessageCreateBuilder()
                .addContent("`%s` 검색결과".formatted(query))
                .addEmbeds(list.stream()
                        .map(portalQuery -> new EmbedBuilder()
                                .setTitle(portalQuery.getName())
                                .setThumbnail(portalQuery.getImage())
                                .addField("",
                                        "[스캐너]" +
                                                "(" + UrlGenerator.builder()
                                                .type(UrlGenerator.Type.PORTAL)
                                                .link(convertGuid(portalQuery.getGuid()))
                                                .lat(portalQuery.getLat())
                                                .lng(portalQuery.getLng())
                                                .build()
                                                .generate() +
                                                ")",
                                        true)
                                .addField("",
                                        "[인텔맵]" +
                                                "(" + UrlGenerator.builder()
                                                .type(UrlGenerator.Type.INTEL_MAP)
                                                .lat(portalQuery.getLat())
                                                .lat(portalQuery.getLng())
                                                .build()
                                                .generate() +
                                                ")",
                                        true)
                                .build())
                        .toList())
                .build();
    }

    private String convertGuid(String guid) {
        return guid.replace("-", "") + ".16";
    }
}
