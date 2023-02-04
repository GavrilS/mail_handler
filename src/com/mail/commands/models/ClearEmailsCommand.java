package com.mail.commands.models;

import com.mail.commands.repository.TempState;

public class ClearEmailsCommand implements CommandInterface {
    private boolean sendSummary;


    public ClearEmailsCommand(boolean sendSummary) {
        setSendSummary(sendSummary);
    }

    public void executeCommand(TempState state) {

    }


    public void executeHelp() {

    }

    private void setSendSummary(boolean sendSummary) {
        this.sendSummary = sendSummary;
    }

    private boolean getSendSummary() {
        return this.sendSummary;
    }
}
