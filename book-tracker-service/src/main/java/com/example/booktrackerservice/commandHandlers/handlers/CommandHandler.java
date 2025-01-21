package com.example.booktrackerservice.commandHandlers.handlers;

import com.example.booktrackerservice.commandHandlers.commands.BaseCommand;

public interface CommandHandler<T extends BaseCommand>{
    void execute(T command);
}
