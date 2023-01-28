package com.mail.commands.repository;

import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class TempState {
    private List<EmailServerConnector> connectors;
    private List<MessageTemplate> messages;

    public TempState() {
        this.connectors = new ArrayList<EmailServerConnector>();
        this.messages = new ArrayList<MessageTemplate>();
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

    public List<EmailServerConnector> getCurrentConnectors() {
        return this.connectors;
    }

    public List<MessageTemplate> getCurrentMessages() {
        return this.messages;
    }
}
