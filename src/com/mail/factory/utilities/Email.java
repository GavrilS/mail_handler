package com.mail.factory.utilities;

import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.MessageTemplate;

import javax.mail.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Email {
    private static final String CONNECTOR_FILE = "";
    private static final String MESSAGE_FILE = "";

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

    public static List<EmailServerConnector> loadConnectorsFromFile() throws FileNotFoundException {
        List<EmailServerConnector> connectors = new ArrayList<EmailServerConnector>();
        File myFile = new File(CONNECTOR_FILE);
        Scanner reader = new Scanner(myFile);
        while(reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] fields = data.split(", ");
            if (fields.length < 6 || fields.length > 6) {
                continue;
            }
            EmailServerConnector connector = new EmailServerConnector(
                    fields[0], fields[1], fields[2], fields[3], fields[4], Boolean.valueOf(fields[5])
            );
            connectors.add(connector);
        }
        return connectors;
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

    public static List<MessageTemplate> loadMessagesFromFile() throws FileNotFoundException {
        List<MessageTemplate> messages = new ArrayList<MessageTemplate>();
        File myFile = new File(MESSAGE_FILE);
        Scanner reader = new Scanner(myFile);
        while(reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] fields = data.split(", ");
            if (fields.length < 3 || fields.length > 3) {
                continue;
            }
            MessageTemplate message = new MessageTemplate(
                fields[0], fields[1], fields[2]
            );
            messages.add(message);
        }
        return messages;
    }
}
