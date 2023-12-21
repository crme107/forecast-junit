package org.example.junitcleancode.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DATABASE_USERNAME = "system";
    private static final String DATABASE_PASSWORD = "root";

    private ConnectionManager() {}

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
