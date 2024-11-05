package dev.gyeoul.iek.discord.command;

import jakarta.validation.constraints.Size;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import org.jetbrains.annotations.NotNull;

public abstract class Command extends CommandDataImpl {

    protected Command(
            @NotNull String name,
            @Size(min = 1, max = 100)
            @NotNull String description
    ) {
        super(name, description);
        addOption();
    }

    void addOption() {
    }

    public abstract void execute(SlashCommandInteractionEvent event);
}
