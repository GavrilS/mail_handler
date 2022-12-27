package com.mail.db.models;

//import java.time.LocalDateTime;
import java.util.Date;

public class CheckedEmails {
    private int id;
    private String sender;
    private String subject;
//    private LocalDateTime sentOn;
    private Date sentOn;
    private String text;
    private String platform;
    private String user;

    public CheckedEmails(int id, String sender, String subject, Date sentOn, String text, String platform, String user) {
        setId(id);
        setSender(sender);
        setSubject(subject);
        setSentOn(sentOn);
        setText(text);
        setPlatform(platform);
        setUser(user);
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    private void setSender(String sender) {
        if (sender == null || sender == "") {
            System.out.println("Sender must not be null or empty!");
        }
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    private void setSubject(String subject) {
        if (subject == null || subject == "") {
            System.out.println("Subject must not be null or empty!");
        }
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    private void setSentOn(Date sentOn) {
        if (sentOn == null) {
            System.out.println("Date cannot be null!");
        }
        this.sentOn = sentOn;
    }

    public Date getSentOn() {
        return this.sentOn;
    }

    private void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    private void setPlatform(String platform) {
        if (platform == null || platform == "") {
            System.out.println("Platform must not be null or empty!");
        }
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }

    private void setUser(String user) {
        if (user == null || user == "") {
            System.out.println("User must not be null or empty!");
        }
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }
}
