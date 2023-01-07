package com.mail.db.repositories;

import com.mail.db.models.CheckedEmail;

import java.sql.*;

import com.mail.db.utilities.*;


public class CheckedEmailsRepositoryImpl implements CheckedEmailsRepository {

    public void saveCheckedEmail(CheckedEmail email, String dbUrl) throws SQLException, ClassNotFoundException {

        Connection connection = MysqlHelper.dbConnect(dbUrl);
//        Statement statement = connection.createStatement();
//        String saveEmailQuery = String.format(
//                "INSERT INTO checked_emails (sender, subject, platform, user, text, sent_on)" +
//                        "VALUES (%s, %s, %s, %s, %s, %s)", email.getSender(), email.getSubject(), email.getPlatform(),
//                    email.getUser(), email.getText(), email.getSentOn());
        String saveEmailQuery = "INSERT INTO checked_emails (sender, subject, platform, user, text, sent_on) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(saveEmailQuery);
        statement.setString(1, email.getSender());
        statement.setString(2, email.getSubject());
        statement.setString(3, email.getPlatform());
        statement.setString(4, email.getUser());
        statement.setString(5, email.getText());
        statement.setDate(6, new Date(email.getSentOn().getTime()));
        System.out.println(statement);
        statement.execute();
        MysqlHelper.closeConnection(connection);
    }


//    public CheckedEmail getEmailBySubject(String subject) {
//
//    }
//
//    public CheckedEmail getEmailBySender(String sender) {
//
//    }
}
