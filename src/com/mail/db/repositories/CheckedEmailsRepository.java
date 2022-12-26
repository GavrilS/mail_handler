package com.mail.db.repositories;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

public interface CheckedEmailsRepository {
    void saveCheckedEmail(Message message, String platform, String user) throws MessagingException, IOException;


}
