package com.mail.commands.models;

import com.mail.commands.repository.TempState;

public class ClearStateCommand implements CommandInterface {

    private boolean savedEmails;
    private boolean savedMessages;
    private boolean savedConnectors;

    public ClearStateCommand(boolean savedEmails, boolean savedMessages, boolean savedConnectors) {
        this.savedEmails = savedEmails;
        this.savedMessages = savedMessages;
        this.savedConnectors = savedConnectors;
    }

    public ClearStateCommand() {
        this.savedEmails = false;
        this.savedMessages = false;
        this.savedConnectors = false;
    }

    public void executeCommand(TempState state) {
        if (this.savedEmails) {
            state.getCurrentEmails().clear();
        } else if (this.savedMessages) {
            state.getCurrentMessages().clear();
        } else if (this.savedConnectors) {
            state.getCurrentConnectors().clear();
        } else {
            state.getCurrentEmails().clear();
            state.getCurrentMessages().clear();
            state.getCurrentConnectors().clear();
        }
        System.out.println("State cleared...");
    }

    public void executeHelp() {

    }
}
