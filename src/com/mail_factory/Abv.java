package com.mail_factory;

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
