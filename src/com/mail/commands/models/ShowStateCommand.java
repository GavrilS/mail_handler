package com.mail.commands.models;

import com.mail.commands.repository.TempState;

import java.util.List;

public class ShowStateCommand implements CommandInterface {
    private boolean savedEmails;
    private boolean savedMessages;
    private boolean savedConnectors;

    public ShowStateCommand(boolean savedEmails, boolean savedMessages, boolean savedConnectors) {
        this.savedEmails = savedEmails;
        this.savedMessages = savedMessages;
        this.savedConnectors = savedConnectors;
    }

    public ShowStateCommand() {
        this.savedEmails = false;
        this.savedMessages = false;
        this.savedConnectors = false;
    }

    public void executeCommand(TempState state) {
        if (this.savedEmails) {
            printState(state.getCurrentEmails());
        } else if (this.savedMessages) {
            printState(state.getCurrentMessages());
        } else if (this.savedConnectors) {
            printState(state.getCurrentConnectors());
        } else {
            System.out.println("To see a specific state you need to add the name to the command:\n" +
                    "   show <the state to be seen: emails/messages/connectors>");
        }
    }

    public <T> void printState(List<T> list) {
        if (list == null || list.size() == 0) {
            System.out.println("Nothing to show. Empty list...");
        }
        System.out.println("ID: ITEM");
        for(int i=0; i<list.size(); i++) {
            System.out.println(String.valueOf(i) + ": " + list.get(i).toString());
        }
    }

    public void executeHelp() {

    }
}
