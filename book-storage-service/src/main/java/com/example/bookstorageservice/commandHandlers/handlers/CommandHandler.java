package com.example.bookstorageservice.commandHandlers.handlers;

import com.example.bookstorageservice.commandHandlers.command.BaseCommand;

public interface CommandHandler<T extends BaseCommand>{
    void execute(T command);
}
