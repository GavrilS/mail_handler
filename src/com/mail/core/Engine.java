package com.mail.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mail.commands.constructors.CommandConstructorImpl;
import com.mail.commands.constructors.CommandConstructorInterface;
import com.mail.commands.models.CommandInterface;
import com.mail.commands.utilities.CommandUtility;

public class Engine {

    public static void run() {
        boolean flag = true;

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

                CommandConstructorInterface commandConstructor = new CommandConstructorImpl();
                CommandInterface commandImpl = commandConstructor.returnCommand(command);
                if (commandImpl == null) {
                    System.out.println("Invalid command! Please try again...");
                    continue;
                }
                commandImpl.executeCommand();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
