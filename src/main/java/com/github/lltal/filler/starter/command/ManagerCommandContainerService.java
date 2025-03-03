package com.github.lltal.filler.starter.command;

import com.github.lltal.filler.starter.annotation.CommandNames;
import com.github.lltal.filler.starter.session.SessionManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ManagerCommandContainerService {
    private static final Logger log = LoggerFactory.getLogger(ManagerCommandContainerService.class);
    private final ApplicationContext applicationContext;
    private final HashMap<String, CommandContainer> commandContainerMap;
    private final SessionManagerService sessionManagerService;

    public ManagerCommandContainerService(ApplicationContext applicationContext, @Autowired(required = false) SessionManagerService sessionManagerService) {
        this.applicationContext = applicationContext;
        this.sessionManagerService = sessionManagerService;
        this.commandContainerMap = new HashMap();
    }

    public CommandContainer get(String command, TypeCommand typeCommand) {
        return (CommandContainer)this.commandContainerMap.get(CommandContainer.castCommandNameAndType(command, typeCommand));
    }

    public void put(CommandContainer commandContainer) {
        Iterator var2 = commandContainer.getNamesForManager().iterator();

        while(var2.hasNext()) {
            String name = (String)var2.next();
            this.commandContainerMap.put(name, commandContainer);
        }

    }

    @PostConstruct
    public void initCommands() {
        log.info("Find all commands for system");
        this.commandContainerMap.clear();
        Map<String, Object> withAnnotation = this.applicationContext.getBeansWithAnnotation(CommandNames.class);
        this.loadCommands(new ArrayList(withAnnotation.values()));
    }

    private void loadCommands(List<Object> commands) {
        if (commands.isEmpty()) {
            log.warn("Command not find for bot");
        } else {
            log.info("Find " + commands.size() + " commands...");
            Iterator var2 = commands.iterator();

            while(var2.hasNext()) {
                Object command = var2.next();
                CommandNames commandNames = (CommandNames)command.getClass().getAnnotation(CommandNames.class);
                this.loadTextCommand(commandNames, command);
            }

        }
    }

    private void loadTextCommand(CommandNames commandNames, Object object) {
        String[] names = commandNames.value();
        Logger var10000 = log;
        TypeCommand var10001 = commandNames.type();
        var10000.info("Command {" + var10001 + "} " + Arrays.toString(names) + " in class: " + object.getClass().getName());
        CommandContainer commandContainer = null;
        String[] var5 = names;
        int var6 = names.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String name = var5[var7];
            commandContainer = (CommandContainer)this.commandContainerMap.get(name);
            if (commandContainer != null) {
                break;
            }
        }

        if (commandContainer == null) {
            commandContainer = new CommandContainer(names, commandNames.type());
            this.put(commandContainer);
        }

        commandContainer.setObjectController(object);
    }
}
