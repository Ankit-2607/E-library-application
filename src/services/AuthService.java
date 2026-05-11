package services;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import java.util.ArrayList;

public class AuthService {

        // =================================================
        // REGISTER USER
        // =================================================

        public static boolean register(

                        String fullName,

                        String email,

                        String password) {

                try {

                        Connection conn = DatabaseConnection.getConnection();

                        // =========================================
                        // CHECK EXISTING USER
                        // =========================================

                        String checkQuery =

                                        "SELECT * FROM users WHERE email=?";

                        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);

                        checkStmt.setString(
                                        1,
                                        email);

                        ResultSet rs = checkStmt.executeQuery();

                        // =========================================
                        // USER ALREADY EXISTS
                        // =========================================

                        if (rs.next()) {

                                conn.close();

                                return false;
                        }

                        // =========================================
                        // INSERT USER
                        // =========================================

                        String insertQuery =

                                        "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";

                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

                        insertStmt.setString(
                                        1,
                                        fullName);

                        insertStmt.setString(
                                        2,
                                        email);

                        insertStmt.setString(
                                        3,
                                        password);

                        insertStmt.executeUpdate();

                        conn.close();

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return false;
        }

        // =================================================
        // LOGIN
        // =================================================

        public static boolean login(

                        String email,

                        String password) {

                try {

                        Connection conn = DatabaseConnection.getConnection();

                        String query =

                                        "SELECT * FROM users " +
                                                        "WHERE (email=? OR username=?) " +
                                                        "AND password=?";

                        PreparedStatement stmt = conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        email);

                        stmt.setString(
                                        2,
                                        email);

                        stmt.setString(
                                        3,
                                        password);

                        ResultSet rs = stmt.executeQuery();

                        boolean success = rs.next();

                        conn.close();

                        return success;

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return false;
        }

        // =================================================
        // GET USERNAME
        // =================================================

        public static String getUsername(
                        String email) {

                try {

                        Connection conn = DatabaseConnection.getConnection();

                        String query =

                                "SELECT username FROM users " +
                                "WHERE email=? OR username=?";

                        PreparedStatement stmt = conn.prepareStatement(query);

                        stmt.setString(
                                1,
                                email
                                );

                        stmt.setString(
                                2,
                                email
                                );

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {

                                String username = rs.getString("username");

                                conn.close();

                                return username;
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return null;
        }

        // =========================================
        // GET TOTAL USERS
        // =========================================

        public static int getTotalUsers() {

                int total = 0;

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "SELECT COUNT(*) FROM users";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {

                                total = rs.getInt(1);
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return total;
        }

        // =========================================
        // GET ALL USERS
        // =========================================

        public static ArrayList<String> getAllUsers() {

                ArrayList<String> users = new ArrayList<>();

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query = "SELECT username FROM users";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {

                                users.add(

                                                rs.getString(
                                                                "username"));
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return users;
        }

        // =========================================
        // GET BOOKS READ COUNT
        // =========================================

        public static int getBooksReadCount(
                        String username) {

                int total = 0;

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "SELECT COUNT(*) " +

                                                        "FROM library " +

                                                        "WHERE username=?";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        username);

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {

                                total = rs.getInt(1);
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return total;
        }

        // =========================================
        // REMOVE USER
        // =========================================

        public static void removeUser(
                        String username) {

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "DELETE FROM users " +

                                                        "WHERE username=?";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        username);

                        stmt.executeUpdate();

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================
        // GET JOINED DATE
        // =========================================

        public static String getJoinedDate(
                        String username) {

                String joinedDate = "Unknown";

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "SELECT created_at " +

                                                        "FROM users " +

                                                        "WHERE username=?";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        username);

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {

                                joinedDate =

                                                rs.getString(
                                                                "created_at");
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return joinedDate;
        }
}