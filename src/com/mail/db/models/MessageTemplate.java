package com.mail.db.models;

public class MessageTemplate {
    private String sender;
    private String recipients;
    private String subject;
    private String text;

    public MessageTemplate(String sender, String recipients, String subject) {
        setSender(sender);
        setRecipients(recipients);
        setSubject(subject);
    }

    private void setSender(String sender) {
        if (sender == null || sender == "") {
            System.out.println("Sender cannot be null or empty!");
        }
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    private void setRecipients(String recipients) {
        if (recipients == null || recipients == "") {
            System.out.println("Recipients cannot be null or empty!");
        }
        this.recipients = recipients;
    }

    public String getRecipients() {
        return this.recipients;
    }

    public void setSubject(String subject) {
        if (subject == null || subject == "") {
            System.out.println("Subject cannot be null or empty!");
        }
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
