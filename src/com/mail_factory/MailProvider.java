package com.mail_factory;

public abstract class MailProvider implements MailProviderInterface {
    public abstract String connect();

    public abstract String query();

    public abstract String close();
}
