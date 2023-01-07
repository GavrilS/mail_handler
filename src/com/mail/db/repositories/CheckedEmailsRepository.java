package com.mail.db.repositories;

import com.mail.db.models.CheckedEmail;

import java.sql.SQLException;

public interface CheckedEmailsRepository {
    void saveCheckedEmail(CheckedEmail email, String dbUrl) throws SQLException, ClassNotFoundException;

//    CheckedEmail getEmailBySubject(String subject);

//    CheckedEmail getEmailBySender(String sender);
}
