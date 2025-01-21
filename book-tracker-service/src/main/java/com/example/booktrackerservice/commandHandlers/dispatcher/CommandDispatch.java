package com.example.booktrackerservice.commandHandlers.dispatcher;

import com.example.booktrackerservice.commandHandlers.commands.BaseCommand;
import com.example.booktrackerservice.commandHandlers.handlers.CommandHandler;

public interface CommandDispatch {
    <T extends BaseCommand> void regHandler(Class<T> command, CommandHandler<T> handler);
    void dispatch(BaseCommand command);
}
