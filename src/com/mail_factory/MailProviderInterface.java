package com.mail_factory;

public interface MailProviderInterface {
    String connect();
    String query();
    String close();
}
