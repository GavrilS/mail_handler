package com.test;

import com.mail.factory.models.PopClient;

import java.util.Scanner;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailTest {

    public static void check(String host, String storeType, String user, String password) {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //retrieve the messages from the folder in an array and print them
//            Message[] messages = emailFolder.getMessages();
            int[] msgnums = {1, 2, 3};
            Message[] messages = emailFolder.getMessages(msgnums);
            System.out.println("messages.length----" + messages.length);

            for (int i=0, n = messages.length; i<10; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Received on: " + message.getReceivedDate());
                System.out.println("Flags: " + message.getFlags());
//                System.out.println("Text: " + message.getContent().toString());
            }

            //close the store and folder objects
            emailFolder.close(false);
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
    }


    public static void main(String[] args) {

        String host = "pop3.abv.bg";
        String mailStoreType = "pop3";

//        String host = "pop.gmail.com";
//        String mailStoreType = "pop3";

        Scanner in = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String username = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();

//        System.out.printf("Username: %s - Password: %s", username, password);

//        check(host, mailStoreType, username, password);
        PopClient abv = new PopClient(host, "995", username, password);
//        abv.retrieveAndClean(10, false);
//        abv.retrieveAndClean(0, false);
//        abv.retrieveAndClean(10000, false);
        abv.retrieveAndClean(3, false);
    }

}
