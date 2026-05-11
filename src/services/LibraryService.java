package services;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class LibraryService {

    // =========================================
    // ADD BOOK
    // =========================================

    public static void addBook(
            String bookTitle) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "INSERT INTO library(username, book_title) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(

                    1,

                    SessionManager.getCurrentUser());

            stmt.setString(
                    2,
                    bookTitle);

            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // CHECK BOOK EXISTS
    // =========================================

    public static boolean hasBook(
            String bookTitle) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "SELECT * FROM library " +

                            "WHERE username=? AND book_title=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(

                    1,

                    SessionManager.getCurrentUser());

            stmt.setString(
                    2,
                    bookTitle);

            ResultSet rs = stmt.executeQuery();

            boolean exists = rs.next();

            conn.close();

            return exists;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // =========================================
    // REMOVE BOOK
    // =========================================

    public static void removeBook(
            String bookTitle) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "DELETE FROM library " +

                            "WHERE username=? AND book_title=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(

                    1,

                    SessionManager.getCurrentUser());

            stmt.setString(
                    2,
                    bookTitle);

            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================
    // TOGGLE BOOK
    // =========================================

    public static void toggleBook(
            String bookTitle) {

        if (hasBook(bookTitle)) {

            removeBook(bookTitle);

        } else {

            addBook(bookTitle);
        }
    }

    // =========================================
    // GET BOOKS
    // =========================================

    public static ArrayList<String> getBooks() {

        ArrayList<String> books = new ArrayList<>();

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =

                    "SELECT * FROM library WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(

                    1,

                    SessionManager.getCurrentUser());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                books.add(

                        rs.getString(
                                "book_title"));
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return books;
    }

    // =========================================
    // GET ACTIVE READERS
    // =========================================

    public static int getActiveReaders() {

        int total = 0;

        try {

            Connection conn =

                    DatabaseConnection
                            .getConnection();

            String query =

                    "SELECT COUNT(DISTINCT username) " +

                            "FROM library";

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
}