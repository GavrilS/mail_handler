package com.mail.commands.models;

public class ClearEmailsCommand implements CommandInterface {
    private boolean sendSummary;


    public ClearEmailsCommand(boolean sendSummary) {
        setSendSummary(sendSummary);
    }

    public void executeCommand() {

    }

    private void setSendSummary(boolean sendSummary) {
        this.sendSummary = sendSummary;
    }

    private boolean getSendSummary() {
        return this.sendSummary;
    }
}
