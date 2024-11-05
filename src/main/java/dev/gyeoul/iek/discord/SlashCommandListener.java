package dev.gyeoul.iek.discord;

import dev.gyeoul.iek.discord.command.CommandList;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SlashCommandListener extends ListenerAdapter {
    private final CommandList commandList;

    public SlashCommandListener(CommandList commandList) {
        this.commandList = commandList;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;
        if (!commandList.contains(event.getName())) return;
        commandList.getCommandByName(event.getName()).execute(event);
    }

}
