package com.mail_factory_models;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags;

//import java.time.LocalDateTime;


public class PopClient implements PopClientInterface {

    private String host;
    private String port;
    private String username;
    private String password;


    public PopClient(String host, String port, String username, String password) {
        setHost(host);
        setPort(port);
        setUsername(username);
        setPassword(password);
    }


    public ArrayList<Message> retrieveAndClean(int countMessages) {
        ArrayList<Message> checkedMessages = new ArrayList<Message>();
        try {
            Properties properties = new Properties();

            properties.put("mail.pop3.host", this.host);
            properties.put("mail.pop3.port", this.port);
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(this.host, this.username, this.password);

            //create folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

//            Message[] messages = emailFolder.getMessages();
//            int[] msgnums = {1, 2, 3};
//            Message[] messages = emailFolder.getMessages(msgnums);
            if (countMessages == 0) {
                countMessages = 1;
            }
            Message[] messages = emailFolder.getMessages(1, countMessages);
            System.out.println("messages.length----" + messages.length);

            for (int i=0; i<messages.length; i++) {
                Message message = messages[i];
                checkedMessages.add(message);
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Received on: " + message.getReceivedDate());
                System.out.println("Sent on: " + message.getSentDate());
                System.out.println(message.getSentDate().getClass());
                System.out.println("Flags: " + message.getFlags());
//                System.out.println("Text: " + message.getContent().toString());
            }

            Flags deleted = new Flags(Flags.Flag.DELETED);
            emailFolder.setFlags(messages, deleted, true);

            //close the store and folder objects
//            emailFolder.close(false);
            emailFolder.close(true);
            store.close();
        }
        catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return checkedMessages;
    }

    public String makeSummaryPerDay() {
        return "This is a list of the emails for a particular day!";
    }

    private void setHost(String host) {
        if (host == null || host == "") {
            System.out.println("Host must be set and cannot be null or empty!");
        }
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }

    private void setPort(String port) {
        if (port == null || port == "") {
            System.out.println("Port must be set and cannot be null or empty!");
        }
        this.port = port;
    }

    public String getPort() {
        return this.port;
    }

    private void setUsername(String username) {
        if (username == null || username == "") {
            System.out.println("Username must be set and cannot be null or empty!");
        }
        this.username = username;
    }

    private String getUsername() {
        return this.username;
    }

    private void setPassword(String password) {
        if (password == null || password == "") {
            System.out.println("Password must be set and cannot be null or empty!");
        }
        this.password = password;
    }

    private String getPassword() {
        return this.password;
    }
}
