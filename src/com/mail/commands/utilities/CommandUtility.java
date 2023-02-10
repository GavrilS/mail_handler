package com.mail.commands.utilities;


import com.mail.factory.models.CheckedEmail;
import com.mail.factory.models.EmailServerConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public static EmailServerConnector chooseConnector(List<EmailServerConnector> connectors) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Available connectors:");
        for (int i=0; i<connectors.size(); i++) {
            System.out.println(i + ". " + connectors.get(i).toString());
        }
        System.out.println("Choose which connector to use:");
        try {
            int conId = Integer.parseInt(reader.readLine());
            return connectors.get(conId);
        } catch (Exception e) {
            System.out.println("Something went wrong! Please try again...");
            e.printStackTrace();
        }
        return null;
    }


    public static String summarizeCheckedEmails(List<CheckedEmail> emailList) {
        StringBuilder str = new StringBuilder();
        for (CheckedEmail email: emailList) {
            str.append("Sender: " + email.getSender() + "; Date: " + email.getSentOn().toString() + "; Subject: " +
                    email.getSubject() + "\n");
        }
        return str.toString();
    }
}
