package dev.gyeoul.iek.discord.command;

import dev.gyeoul.iek.util.UrlGenerator;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.springframework.stereotype.Component;


@Component
public class GenerateProfileLink extends Command {
    public GenerateProfileLink() {
        super("profile", "프로필 링크 생성");
    }

    enum Option {
        NAME("agent", "에이전트명");

        private final String name;
        private final String description;

        Option(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    @Override
    void addOption() {
        this.addOption(OptionType.STRING, Option.NAME.name, Option.NAME.description, true, false);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        var value = event.getOption(Option.NAME.name);
        if (value != null) {
            var agent = value.getAsString();
            var link = "[" + agent + "]" +
                    "(" + UrlGenerator.builder()
                    .type(UrlGenerator.Type.AGENT)
                    .link(agent)
                    .build()
                    .generate() +
                    ")";

            event.reply(link).queue();
        } else {
            event.reply("name is null").queue();
        }
    }
}