package com.mail.db.utilities;

import java.sql.*;

public class MysqlHelper {

    public static Connection dbConnect(String dbUrl) throws SQLException, ClassNotFoundException {
        String[] credentials = dbUrl.split(" ");

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(credentials[0], credentials[1], credentials[2]);
        return connection;
    }


    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Couldn't close MySql connection - " + e);
        }
    }
}
