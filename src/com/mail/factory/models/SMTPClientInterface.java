package com.mail.factory.models;

public interface SMTPClientInterface {
    void sendEmail();

    void sendSummary(MessageTemplate messageTemplate);
}
