package com.mail.db.repositories;

import com.mail.db.models.CheckedEmails;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

public class CheckedEmailsRepositoryImpl implements CheckedEmailsRepository {

    public void saveCheckedEmail(Message message, String platform, String user) throws MessagingException, IOException {

        CheckedEmails email = new CheckedEmails(0, message.getFrom().toString(), message.getSubject(), message.getSentDate(),
                    message.getContent().toString(), platform, user);


    }
}
