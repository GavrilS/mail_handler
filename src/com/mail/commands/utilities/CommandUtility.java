package com.mail.commands.utilities;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommandUtility {

    public static boolean verifyCommand(String command) {
        if(command.equals("")) {
          return false;
        }
        command = command.toLowerCase();
        List<String> commandList = getCommands();
        if (commandList.contains(command)) {
            return true;
        } else {
            System.out.printf("Command '%s' is not recognised! The available commands are: \n", command);
            for (String commandOption: commandList) {
                System.out.println("    " + commandOption);
            }
            return false;
        }
    }

    private static List<String> getCommands() {
        List<String> commandList = Stream.of(
                "send summary only",
                "clear emails",
                "clear emails with summary",
                "create connector",
                "load connector from file",
                "create message",
                "load connectors from file",
                "load messages from file",
                "help",
                "exit"
        ).collect(Collectors.toList());
        return commandList;
    }
}
