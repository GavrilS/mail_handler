package com.mail_factory_models;

public class Abv extends MailProvider {

//    public Abv() {
//
//    }

    public String connect(){
        return "Connected successfully!";
    }

    public String query() {
        return "Querying...";
    }

    public String close() {
        return "Closed connection!";
    }
}
