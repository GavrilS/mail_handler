package com.mail.db.repositories;

import com.mail.db.models.CheckedEmails;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public interface CheckedEmailsRepository {
    void saveCheckedEmail(CheckedEmails email, String dbUrl) throws SQLException, ClassNotFoundException;

}
