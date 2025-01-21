package com.example.bookstorageservice.commandHandlers.dispatcher;

import com.example.bookstorageservice.commandHandlers.command.BaseCommand;
import com.example.bookstorageservice.commandHandlers.handlers.CommandHandler;

public interface CommandDispatch {
    <T extends BaseCommand> void regCommand(Class<T> command, CommandHandler<T> handler);
    void dispatch(BaseCommand baseCommand);
}
