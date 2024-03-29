package com.mail.factory.constructors;

import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.PopClient;

public class PopConstructorImpl implements PopConstructorInterface {

    public PopConstructorImpl() {
        // Empty PopConstructor constructor
    }

    public PopClient returnPopClient(EmailServerConnector connector) {
        switch(connector.getPlatform().toLowerCase()) {
            default:
                return new PopClient(connector);
        }
    }
}
