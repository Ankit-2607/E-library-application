package services;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    // =========================================
    // GET EMAIL
    // =========================================

    public static String getEmail(
            String username) {

        String email = "";

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "SELECT email FROM users " +
                            "WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                email = rs.getString("email");
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return email;
    }

    // =========================================
    // GET PHONE
    // =========================================

    public static String getPhone(
            String username) {

        String phone = "";

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "SELECT phone FROM users " +
                            "WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                phone = rs.getString("phone");
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return phone;
    }

    // =========================================
    // SAVE PROFILE IMAGE
    // =========================================

    public static void saveProfileImage(
            String username,
            String imagePath) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "UPDATE users " +

                            "SET profile_image=? " +

                            "WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(
                    1,
                    imagePath);

            stmt.setString(
                    2,
                    username);

            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // GET PROFILE IMAGE
    // =========================================

    public static String getProfileImage(
            String username) {

        String image = "";

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "SELECT profile_image " +

                            "FROM users " +

                            "WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(
                    1,
                    username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                image = rs.getString(
                        "profile_image");
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return image;
    }

    // =========================================
    // UPDATE PROFILE
    // =========================================

    public static void updateProfile(

            String currentUsername,

            String newUsername,

            String email,

            String phone) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            // =========================================
            // UPDATE USERS TABLE
            // =========================================

            String userQuery =

                    "UPDATE users " +

                            "SET username=?, " +

                            "email=?, " +

                            "phone=? " +

                            "WHERE username=?";

            PreparedStatement userStmt =

                    conn.prepareStatement(userQuery);

            userStmt.setString(1, newUsername);

            userStmt.setString(2, email);

            userStmt.setString(3, phone);

            userStmt.setString(4, currentUsername);

            userStmt.executeUpdate();

            // =========================================
            // UPDATE WISHLIST TABLE
            // =========================================

            String wishlistQuery =

                    "UPDATE wishlist " +

                            "SET username=? " +

                            "WHERE username=?";

            PreparedStatement wishlistStmt =

                    conn.prepareStatement(wishlistQuery);

            wishlistStmt.setString(1, newUsername);

            wishlistStmt.setString(2, currentUsername);

            wishlistStmt.executeUpdate();

            // =========================================
            // UPDATE LIBRARY TABLE
            // =========================================

            String libraryQuery =

                    "UPDATE library " +

                            "SET username=? " +

                            "WHERE username=?";

            PreparedStatement libraryStmt =

                    conn.prepareStatement(libraryQuery);

            libraryStmt.setString(1, newUsername);

            libraryStmt.setString(2, currentUsername);

            libraryStmt.executeUpdate();

            // =========================================
            // UPDATE READING PROGRESS
            // =========================================

            String progressQuery =

                    "UPDATE reading_progress " +

                            "SET username=? " +

                            "WHERE username=?";

            PreparedStatement progressStmt =

                    conn.prepareStatement(progressQuery);

            progressStmt.setString(1, newUsername);

            progressStmt.setString(2, currentUsername);

            progressStmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}