package com.mail.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mail.commands.constructors.CommandConstructorImpl;
import com.mail.commands.constructors.CommandConstructorInterface;
import com.mail.commands.models.CommandInterface;
import com.mail.commands.repository.TempState;
import com.mail.commands.utilities.CommandUtility;

public class Engine {

    public static void run() {
        boolean flag = true;
        CommandConstructorInterface commandConstructor = new CommandConstructorImpl();
        TempState currentState = TempState.getInstance();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(flag) {

            // Read User command
            try {
                String command = reader.readLine();
                boolean commandStatus = CommandUtility.verifyCommand(command);
                if (!commandStatus) {
                    continue;
                } else if (command.toLowerCase().equals("exit")) {
                    System.out.println("Received exit signal! Exiting...");
                    flag = false;
                    continue;
                }


                CommandInterface commandImpl = commandConstructor.returnCommand(command);
                if (commandImpl == null) {
                    System.out.println("Invalid command! Please try again...");
                    continue;
                }
                String helpCommand = "help";
                if (command.toLowerCase().contains(helpCommand)) {
                    commandImpl.executeHelp();
                } else {
                    commandImpl.executeCommand(currentState);
                }
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
