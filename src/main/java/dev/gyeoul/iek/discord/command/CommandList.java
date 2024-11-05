package dev.gyeoul.iek.discord.command;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandList {
    private final List<Command> list;

    public CommandList(ApplicationContext context) {
        list = context.getBeansOfType(Command.class).values().stream().toList();

    }

    public List<Command> getCommandList() {
        return list;
    }

    public boolean contains(String name) {
        return list.stream()
                .anyMatch(command -> command.getName().equals(name));
    }

    public Command getCommandByName(String name) {
        return list.stream()
                .filter(command -> command.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}


