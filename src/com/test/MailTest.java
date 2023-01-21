package com.test;

import com.mail.db.models.CheckedEmail;
import com.mail.db.models.EmailServerConnector;
import com.mail.db.models.MessageTemplate;
import com.mail.factory.models.PopClient;
import com.mail.factory.models.SMTPClient;

import java.sql.SQLException;
import java.util.List;

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


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String username = args[0];
        String password = args[1];
        System.out.println("USername is: " + username);
        System.out.println("Password is: " + password);
        String popHost = "pop3.abv.bg";
        String smtpHost = "smtp.abv.bg";
        EmailServerConnector abvPopConnector = new EmailServerConnector(username, password, "abv.bg", popHost,
                    "995", true);
        EmailServerConnector abvSmtpConnector = new EmailServerConnector(username, password, "abv.bg", smtpHost,
                    "465", true);

        MessageTemplate messageTemplate = new MessageTemplate("gavr1lll@abv.bg", "gavr1lll@abv.bg",
                    "Testing smtp client with daily summary!");

        PopClient abv = new PopClient(abvPopConnector);
        List<CheckedEmail> emails = abv.retrieveAndClean(1, false);
        String summarizedEmails = abv.summarizeCheckedEmails(emails);
        messageTemplate.setText(summarizedEmails);
        System.out.println("Summary of checked emails: " + summarizedEmails);

        SMTPClient abvSMTP = new SMTPClient(abvSmtpConnector);
        abvSMTP.sendSummary(messageTemplate);
//        CheckedEmailsRepositoryImpl repository = new CheckedEmailsRepositoryImpl();
//        String dbUrl = "jdbc:mysql://localhost:3306/email_archives root newpass";
//        for (CheckedEmail email: emails) {
//            repository.saveCheckedEmail(email, dbUrl);
//        }
    }

}
