package com.mail.factory.models;

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



public class PopClient implements PopClientInterface {

    private EmailServerConnector emailConnector;
    private static final int MINIMUM_MESSAGE_COUNT = 1;
    private static final int GET_ALL_MESSAGES = 0;


    public PopClient(EmailServerConnector emailConnector) {
        setEmailConnector(emailConnector);
    }


    @Override
    public List<CheckedEmail> retrieveAndClean(int countMessages, boolean cleanFlagedEmails) {
        List<CheckedEmail> checkedMessages = new ArrayList<CheckedEmail>();
        try {
            Properties properties = new Properties();

            properties.put("mail.pop3.host", this.emailConnector.getHost());
            properties.put("mail.pop3.port", this.emailConnector.getPort());
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(this.emailConnector.getHost(), this.emailConnector.getUsername(), this.emailConnector.getPassword());

            //create folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
//            emailFolder.open(Folder.READ_WRITE);

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
                        message.getContent().toString(), this.getEmailConnector().getPlatform(), this.getEmailConnector().getUsername());
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

    private void setEmailConnector(EmailServerConnector emailConnector) {
        if (emailConnector == null) {
            System.out.println("The email connector cannot be null!");
        }
        this.emailConnector = emailConnector;
    }

    public EmailServerConnector getEmailConnector() {
        return this.emailConnector;
    }
}
