package com.mail.commands.constructors;

import com.mail.commands.models.ClearEmailsCommand;
import com.mail.commands.models.CommandInterface;

public class CommandConstructorImpl implements CommandConstructorInterface {

    public CommandConstructorImpl() {
        // Default CommandConstructorImpl constructor
    }

    public CommandInterface returnCommand(String command) {
        switch(command.toLowerCase()) {
            case "clear emails":
                return new ClearEmailsCommand(false);
            case "clear emails with summary":
                return new ClearEmailsCommand(true);
            default:
                return null;
        }
    }
}
