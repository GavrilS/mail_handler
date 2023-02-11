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
            case "remove old emails":
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
            case "remove old emails help":
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
            case "show emails":
            case "show emails help":
                return new ShowStateCommand(true, false, false);
            case "show messages":
            case "show messages help":
                return new ShowStateCommand(false, true, false);
            case "show connectors":
            case "show connectors help":
                return new ShowStateCommand(false, false, true);
            case "show":
            case "show help":
                return new ShowStateCommand(false, false, false);
            case "clear emails":
            case "clear emails help":
                return new ClearStateCommand(true, false, false);
            case "clear messages":
            case "clear messages help":
                return new ClearStateCommand(false, true, false);
            case "clear connectors":
            case "clear connectors help":
                return new ClearStateCommand(false, false, true);
            case "clear":
            case "clear help":
                return new ClearStateCommand(false, false, false);
            default:
                return null;
        }
    }
}
