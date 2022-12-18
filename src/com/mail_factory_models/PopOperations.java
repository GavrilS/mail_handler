package com.mail_factory_models;

public class PopOperations implements PopOperationsInterface {


    public String retrieveMessages() {
        return "Retrieving messages!";
    }

    public String cleanFolder() {
        return "Folder cleaned!";
    }
}
