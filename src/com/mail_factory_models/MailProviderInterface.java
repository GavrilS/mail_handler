package com.mail_factory_models;

public interface MailProviderInterface {
    String connect();
    String query();
    String close();
}
