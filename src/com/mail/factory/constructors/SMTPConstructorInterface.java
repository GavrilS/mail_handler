package com.mail.factory.constructors;

import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.SMTPClient;

public interface SMTPConstructorInterface {
    SMTPClient returnSMTPClient(EmailServerConnector connector);
}
