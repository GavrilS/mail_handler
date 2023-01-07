package com.mail.factory.models;

import com.mail.db.models.MessageTemplate;

public interface SMTPClientInterface {
    void sendEmail();

    void sendSummary(MessageTemplate messageTemplate);
}
