package com.mail.factory.constructors;

import com.mail.db.models.EmailServerConnector;
import com.mail.factory.models.SMTPClient;

public interface SMTPConstructorInterface {
    SMTPClient returnSMTPClient(EmailServerConnector connector);
}
