package com.mail.commands.repository;

import com.mail.factory.models.CheckedEmail;
import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class TempState {
    private List<EmailServerConnector> connectors;
    private List<MessageTemplate> messages;
    private List<CheckedEmail> emails;
    private static TempState object;

    private TempState() {
        this.connectors = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    public static TempState getInstance() {
        if (object == null) {
            object = new TempState();
        }
        return object;
    }

    public void addConnector(EmailServerConnector e) {
        this.connectors.add(e);
    }

    public void addConnectors(List<EmailServerConnector> connectorList) {
        for (EmailServerConnector e: connectorList) {
            this.connectors.add(e);
        }
    }

    public void addMessage(MessageTemplate m) {
        this.messages.add(m);
    }

    public void addMessages(List<MessageTemplate> messageList) {
        for (MessageTemplate m: messageList) {
            this.messages.add(m);
        }
    }

    public void addEmail(CheckedEmail e) {
        this.emails.add(e);
    }

    public void addEmails(List<CheckedEmail> emailList) {
        for (CheckedEmail e: emailList) {
            this.emails.add(e);
        }
    }

    public List<EmailServerConnector> getCurrentConnectors() {
        return this.connectors;
    }

    public List<MessageTemplate> getCurrentMessages() {
        return this.messages;
    }

    public List<CheckedEmail> getCurrentEmails() {
        return this.emails;
    }
}
