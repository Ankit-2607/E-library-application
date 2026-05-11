package services;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReadingProgressService {

    // =========================================
    // SAVE READING PROGRESS
    // =========================================

    public static void saveProgress(

                    String bookTitle,

                    int currentPage,
                    int totalPages) {

            try {

                    Connection conn =

                                    DatabaseConnection
                                                    .getConnection();

                    // =========================================
                    // CHECK EXISTING RECORD
                    // =========================================

                    String checkQuery =

                                    "SELECT * FROM reading_progress " +

                                                    "WHERE username=? AND book_title=?";

                    PreparedStatement checkStmt =

                                    conn.prepareStatement(checkQuery);

                    checkStmt.setString(

                                    1,

                                    SessionManager.getCurrentUser());

                    checkStmt.setString(
                                    2,
                                    bookTitle);

                    ResultSet rs = checkStmt.executeQuery();

                    // =========================================
                    // COMPLETION CHECK
                    // =========================================

                    boolean completed =

                                    (currentPage + 1)

                                                    >= totalPages;

                    // =========================================
                    // UPDATE
                    // =========================================

                    if (rs.next()) {

                            String updateQuery =

                                "UPDATE reading_progress " +
                                "SET current_page=?, total_pages=?, completed=? " +
                                "WHERE username=? AND book_title=?";

                            PreparedStatement updateStmt =

                                            conn.prepareStatement(updateQuery);



                            updateStmt.setInt(
                                            1,
                                            currentPage);

                            updateStmt.setInt(
                                            2,
                                            totalPages);
                                updateStmt.setBoolean(
                                            3,
                                            completed);

                            updateStmt.setString(
                                            4,
                                            SessionManager.getCurrentUser());

                            updateStmt.setString(
                                            5,
                                            bookTitle);

                            updateStmt.executeUpdate();

                    }

                    // =========================================
                    // INSERT
                    // =========================================

                    else {

                            String insertQuery =
                                "INSERT INTO reading_progress" +
                                "(username, book_title, current_page, total_pages, completed)" +
                                "VALUES(?, ?, ?, ?, ?)";

                            PreparedStatement insertStmt =
                                        conn.prepareStatement(insertQuery);

                            insertStmt.setString(

                                            1,

                                            SessionManager.getCurrentUser());

                            insertStmt.setString(
                                            2,
                                            bookTitle);

                            insertStmt.setInt(
                                            3,
                                            currentPage);
                        
                            insertStmt.setInt(
                                            4,
                                            totalPages);

                            insertStmt.setBoolean(
                                            5,
                                            completed);

                            insertStmt.executeUpdate();
                    }

                    conn.close();

            } catch (Exception e) {

                    e.printStackTrace();
            }
    }

    // =========================================
    // GET SAVED PAGE
    // =========================================

    public static int getSavedPage(
                    String bookTitle) {

            try {

                    Connection conn =

                                    DatabaseConnection
                                                    .getConnection();

                    String query =

                                    "SELECT current_page " +

                                                    "FROM reading_progress " +

                                                    "WHERE username=? AND book_title=?";

                    PreparedStatement stmt =

                                    conn.prepareStatement(query);

                    stmt.setString(

                                    1,

                                    SessionManager.getCurrentUser());

                    stmt.setString(
                                    2,
                                    bookTitle);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {

                            return rs.getInt(
                                            "current_page");
                    }

                    conn.close();

            } catch (Exception e) {

                    e.printStackTrace();
            }

            return 0;
    }


    // =========================================
    // GET READING PERCENTAGE
    // =========================================

    public static int getReadingPercentage(
                    String bookTitle) {

            try {

                    Connection conn = DatabaseConnection.getConnection();

                    String query =
                                "SELECT current_page, total_pages " +
                                "FROM reading_progress " +
                                "WHERE username=? AND book_title=?";

                    PreparedStatement stmt = conn.prepareStatement(query);

                    stmt.setString(

                                    1,
                                    SessionManager.getCurrentUser());

                    stmt.setString(
                                    2,
                                    bookTitle);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {

                            int currentPage =
                                rs.getInt("current_page");

                            int totalPages =
                                rs.getInt("total_pages");

                            if (totalPages > 0) {
                                return
                                    ((currentPage + 1) * 100) / totalPages;
                            }
                    }

                    conn.close();

            } catch (Exception e) {

                    e.printStackTrace();
            }

            return 0;
    }

    // =========================================
    // GET ALL READING BOOKS
    // =========================================

    public static ArrayList<String> getReadingBooks() {

            ArrayList<String> books = new ArrayList<>();

            try {

                    Connection conn = DatabaseConnection.getConnection();

                    String query =
                                "SELECT DISTINCT book_title " +
                                "FROM reading_progress " +
                                "WHERE username=? AND completed=FALSE";

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
    // GET COMPLETED BOOK COUNT
    // =========================================

    public static int getCompletedBooksCount() {

            try {

                    Connection conn = DatabaseConnection.getConnection();

                    String query =
                        "SELECT COUNT(*) AS total " +
                                "FROM reading_progress " +
                                "WHERE username=? " +
                                "AND completed=TRUE";

                    PreparedStatement stmt = conn.prepareStatement(query);

                    stmt.setString(
                        1,
                        SessionManager.getCurrentUser());

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                            return rs.getInt(
                                "total");
                    }

                    conn.close();

            } catch (Exception e) {

                    e.printStackTrace();
            }

            return 0;
    }

    // =========================================
    // GET COMPLETED BOOKS
    // =========================================

    public static ArrayList<String> getCompletedBooks() {

            ArrayList<String> books = new ArrayList<>();

            try {

                    Connection conn = DatabaseConnection.getConnection();

                    String query =
                        "SELECT DISTINCT book_title " +
                                "FROM reading_progress " +
                                "WHERE username=? " +
                                "AND completed=TRUE";

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
    // GET CURRENT BOOK
    // =========================================

    public static String getCurrentBook() {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String query =
                "SELECT * FROM reading_progress " +
                "WHERE username=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(
                    1,
                    SessionManager.getCurrentUser());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString(
                        "book_title");
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =========================================
    // GET COMPLETED READS
    // =========================================

    public static int getCompletedReads() {

            int total = 0;

            try {

                    Connection conn = DatabaseConnection.getConnection();

                    String query =
                                "SELECT COUNT(DISTINCT book_title) " +
                                "FROM reading_progress " +
                                "WHERE current_page >= total_pages";

                    PreparedStatement stmt = conn.prepareStatement(query);

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