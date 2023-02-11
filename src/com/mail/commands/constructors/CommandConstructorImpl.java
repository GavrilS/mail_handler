package com.mail.commands.constructors;

import com.mail.commands.models.*;

public class CommandConstructorImpl implements CommandConstructorInterface {

    public CommandConstructorImpl() {
        // Default CommandConstructorImpl constructor
    }

    public CommandInterface returnCommand(String command) {
        switch(command.toLowerCase()) {
            case "check emails":
                return new ClearEmailsCommand(false);
            case "clear emails":
                return new ClearEmailsCommand(true);
            case "create connector":
                return new CreateConnectorCommand(false);
            case "load connector from file":
                return new CreateConnectorCommand(true);
            case "create message":
                return new CreateMessageCommand(false);
            case "load message from file":
                return new CreateMessageCommand(true);
            case "send email summary":
                return new SendSummaryCommand();
            case "check emails help":
                return new ClearEmailsCommand(false);
            case "clear emails help":
                return new ClearEmailsCommand(true);
            case "create connector help":
                return new CreateConnectorCommand(false);
            case "load connector from file help":
                return new CreateConnectorCommand(true);
            case "create message help":
                return new CreateMessageCommand(false);
            case "load message from file help":
                return new CreateMessageCommand(true);
            case "send email summary help":
                return new SendSummaryCommand();
            default:
                return null;
        }
    }
}
