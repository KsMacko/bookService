package com.example.booktrackerservice.commandHandlers.dispatcher;

import com.example.booktrackerservice.commandHandlers.commands.BaseCommand;
import com.example.booktrackerservice.commandHandlers.handlers.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandBookTrackerDispatcher implements CommandDispatch{
    private final Map<Class<? extends BaseCommand>, CommandHandler<? extends BaseCommand>> handlers = new HashMap<>();
    @Autowired
    public CommandBookTrackerDispatcher(List<CommandHandler<? extends BaseCommand>> commandHandlers){
        for(CommandHandler<? extends BaseCommand> handler: commandHandlers){
            Class<? extends BaseCommand> commandType = getCommandType(handler);
            handlers.put(commandType, handler);
        }
    }

    private Class<? extends BaseCommand> getCommandType(CommandHandler<? extends BaseCommand> handler){
        ParameterizedType gotType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
        return (Class<? extends BaseCommand>) gotType.getActualTypeArguments()[0];
    }
    @Override
    public <T extends BaseCommand> void regHandler(Class<T> command, CommandHandler<T> handler) {
        handlers.computeIfAbsent(command,ch->handler);
    }

    @Override
    public void dispatch(BaseCommand command) {
        CommandHandler<BaseCommand> handler = (CommandHandler<BaseCommand>) handlers.get(command.getClass());
        if(handler==null) throw  new RuntimeException("Handler is not defined");
        handler.execute(command);
    }
}
