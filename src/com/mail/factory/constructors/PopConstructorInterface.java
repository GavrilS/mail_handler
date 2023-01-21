package com.mail.factory.constructors;

import com.mail.db.models.EmailServerConnector;
import com.mail.factory.models.PopClient;

public interface PopConstructorInterface {
    PopClient returnPopClient(EmailServerConnector connector);
}
