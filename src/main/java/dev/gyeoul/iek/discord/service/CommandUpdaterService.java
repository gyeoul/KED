package dev.gyeoul.iek.discord.service;

import dev.gyeoul.iek.discord.command.CommandList;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandUpdaterService {
    private final JDA jda;
    private final CommandList commandList;

    public CommandUpdaterService(JDA jda, CommandList commandList) throws InterruptedException {
        this.jda = jda.awaitReady();
        this.commandList = commandList;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent ignoredEvent) {
        var guild = jda.getGuilds().stream()
                .map(Guild::updateCommands);
        var action = guild
                .map(commandListUpdateAction -> commandListUpdateAction.addCommands(commandList.getCommandList()));
        action.forEach(CommandListUpdateAction::queue);
    }
}
