package com.example.bookstorageservice.commandHandlers.dispatcher;

import com.example.bookstorageservice.commandHandlers.command.BaseCommand;
import com.example.bookstorageservice.commandHandlers.handlers.CommandHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookCommandDispatcher implements CommandDispatch{
    private final Map<Class<? extends BaseCommand>, CommandHandler<? extends BaseCommand>> handlers = new HashMap<>();

    public BookCommandDispatcher(List<CommandHandler<? extends BaseCommand>> commandHandlers){
        for(CommandHandler<? extends BaseCommand> handler:commandHandlers){
            Class<? extends BaseCommand> commandType = getCommandType(handler);
            handlers.put(commandType, handler);
        }
    }
    private Class<? extends BaseCommand> getCommandType(CommandHandler<? extends BaseCommand> handler){
        ParameterizedType gotCommandType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
        return (Class<? extends BaseCommand>) gotCommandType.getActualTypeArguments()[0];
    }

    @Override
    public <T extends BaseCommand> void regCommand(Class<T> command, CommandHandler<T> handler) {
        handlers.computeIfAbsent(command, ch->handler);
    }

    @Override
    public void dispatch(BaseCommand baseCommand) {
        CommandHandler<BaseCommand> handler = (CommandHandler<BaseCommand>) handlers.get(baseCommand.getClass());
        if(handler==null) throw  new RuntimeException("No registered command handler");
        handler.execute(baseCommand);
    }
}
