package com.mail.db.utilities;

import com.mail.db.models.EmailServerConnector;
import com.mail.db.models.MessageTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Email {

    public static EmailServerConnector setConnector() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set up email server connector. The connector requires the following data in the specified format:" +
                "\nUsername, Password, Platform, Host, Port, SecureConnection(true/false)");
        boolean flag = false;
        String data = "";
        while(!flag) {
            try {
                data = reader.readLine();
            }
            catch (IOException e) {
                System.out.println("The program couldn't process your input! Try again please!");
            }
            if (!data.equals("")) {
                flag = true;
            }
        }
        String[] connectorFields = data.split(", ");
        EmailServerConnector connector = new EmailServerConnector(connectorFields[0], connectorFields[1], connectorFields[2],
                connectorFields[3], connectorFields[4], Boolean.valueOf(connectorFields[5]));
        return connector;
    }

    public static MessageTemplate setMessage() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set up the message template you want to use. It should be in the following format:\n" +
                "Sender, Recipient, Subject");
        boolean flag = false;
        String data = "";
        while(!flag) {
            try {
                data = reader.readLine();
            }
            catch (IOException e) {
                System.out.println("The program couldn't process your input! Try again please!");
            }
            if (!data.equals("")) {
                flag = true;
            }
        }
        String[] messageFields = data.split(", ");
        MessageTemplate messageTemplate = new MessageTemplate(messageFields[0], messageFields[1], messageFields[2]);
        return messageTemplate;
    }
}
