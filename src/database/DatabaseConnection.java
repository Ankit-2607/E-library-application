package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    // =================================================
    // DATABASE CONFIG
    // =================================================

    private static final String URL = "jdbc:mysql://localhost:3306/bibliox";

    private static final String USER = "root";

    // CHANGE THIS
    private static final String PASSWORD = "ritihon@2611";

    // =================================================
    // GET CONNECTION
    // =================================================

    public static Connection getConnection() {

        try {

            Connection connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

            System.out.println(
                    "");

            return connection;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}