package com.mail.factory.constructors;

import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.SMTPClient;

public class SMTPConstructorImpl implements SMTPConstructorInterface {

    public SMTPConstructorImpl() {
        // Empty SMTPConstructor constructor
    }

    public SMTPClient returnSMTPClient(EmailServerConnector connector) {
        switch(connector.getPlatform().toLowerCase()) {
            default:
                return new SMTPClient(connector);
        }
    }
}
