package com.mail.factory.models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SMTPClient implements SMTPClientInterface {

    private EmailServerConnector emailConnector;

    public SMTPClient(EmailServerConnector emailConnector) {
        setEmailConnector(emailConnector);
    }

    @Override
    public void sendEmail() {
        System.out.println("Hello");
    }

    @Override
    public void sendSummary(MessageTemplate messageTemplate) {
        // The messageTemplate object contains the sender, the recipients, the subject and the text

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", this.emailConnector.getSecureConnection());
        props.put("mail.smtp.host", this.emailConnector.getHost());
        props.put("mail.smtp.port", this.emailConnector.getPort());

        // Get the session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getEmailConnector().getUsername(), getEmailConnector().getPassword());
                    }
                });


        session.setDebug(true);

        try {
            // Create default message object
            Message message = new MimeMessage(session);

            // Set headers
            message.setFrom(new InternetAddress(messageTemplate.getSender()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(messageTemplate.getRecipients()));
            message.setSubject(messageTemplate.getSubject());
            ((MimeMessage) message).setText(messageTemplate.getText());

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully...");
        }
        catch(MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setEmailConnector(EmailServerConnector emailConnector) {
        if (emailConnector == null) {
            System.out.println("The connector cannot be null!");
        }
        this.emailConnector = emailConnector;
    }

    public EmailServerConnector getEmailConnector() {
        return this.emailConnector;
    }
}
