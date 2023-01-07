package com.mail.factory.models;

import com.mail.db.models.CheckedEmail;

import java.util.ArrayList;
import java.util.List;
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
    private String platform;
    private static final int MINIMUM_MESSAGE_COUNT = 1;
    private static final int GET_ALL_MESSAGES = 0;


    public PopClient(String host, String port, String username, String password, String platform) {
        setHost(host);
        setPort(port);
        setUsername(username);
        setPassword(password);
        setPlatform(platform);
    }


    @Override
    public List<CheckedEmail> retrieveAndClean(int countMessages, boolean cleanFlagedEmails) {
        List<CheckedEmail> checkedMessages = new ArrayList<CheckedEmail>();
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
            emailFolder.open(Folder.READ_ONLY);
//            emailFolder.open(Folder.READ_WRITE);

//            Message[] messages = emailFolder.getMessages();
//            int[] msgnums = {1, 2, 3};
//            Message[] messages = emailFolder.getMessages(msgnums);
            Message[] messages;
            if (countMessages == GET_ALL_MESSAGES) {
                messages = emailFolder.getMessages();
            } else {
                try {
                    messages = emailFolder.getMessages(MINIMUM_MESSAGE_COUNT, countMessages);
                } catch (Exception e) {
                    messages = emailFolder.getMessages();
                }
            }
            System.out.println("messages.length----" + messages.length);

            for (int i=0; i<messages.length; i++) {
                Message message = messages[i];
                CheckedEmail email = new CheckedEmail(0, message.getFrom()[0].toString(), message.getSubject(), message.getSentDate(),
                        message.getContent().toString(), this.platform, this.username);
                checkedMessages.add(email);
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
            emailFolder.close(cleanFlagedEmails);
//            emailFolder.close(true);
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

    @Override
    public String summarizeCheckedEmails(List<CheckedEmail> emailList) {
        StringBuilder str = new StringBuilder();
        for (CheckedEmail email: emailList) {
            str.append("Sender: " + email.getSender() + "; Date: " + email.getSentOn().toString() + "; Subject: " +
                    email.getSubject() + "\n");
        }
        return str.toString();
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

    private void setPlatform(String platform) {
        if (platform == null || platform == "") {
            System.out.println("Platform must be set and cannot be null or empty!");
        }
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }
}
