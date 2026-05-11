package services;

import database.DatabaseConnection;

import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class BookService {

        // =========================================
        // ADD BOOK
        // =========================================

        public static void addBook(
                        Book book,
                        String pdf) {

                try {

                        Connection conn = DatabaseConnection.getConnection();

                        String query =
                                "INSERT INTO books " +
                                        "(title, author, price, image, description, category, pdf) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement stmt = conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        book.getTitle());

                        stmt.setString(
                                        2,
                                        book.getAuthor());

                        stmt.setString(
                                        3,
                                        book.getPrice());

                        stmt.setString(
                                        4,
                                        book.getImage());

                        stmt.setString(
                                        5,
                                        book.getDescription());

                        stmt.setString(
                                        6,
                                        book.getCategory());

                        stmt.setString(
                                        7,
                                        pdf);

                        stmt.executeUpdate();

                        conn.close();

                        System.out.println(
                                        "Book saved to database!");

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================
        // GET BOOKS
        // =========================================

        public static ArrayList<Book> getBooks() {

                ArrayList<Book> books = new ArrayList<>();

                try {

                        Connection conn = DatabaseConnection.getConnection();

                        String query = "SELECT * FROM books";

                        PreparedStatement stmt = conn.prepareStatement(query);

                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {

                                Book book = new Book(

                                                rs.getString("title"),

                                                rs.getString("author"),

                                                rs.getString("price"),

                                                rs.getString("image"),

                                                rs.getString("description"),

                                                rs.getString("category"),

                                                rs.getString("pdf"));

                                book.setId(

                                                rs.getInt("id"));

                                books.add(book);
                        }
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return books;

        
        }


        // =========================================
        // DELETE BOOK
        // =========================================

        public static void deleteBook(
                        String title) {

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "DELETE FROM books " +

                                                        "WHERE title=?";

                        PreparedStatement stmt =

                                        conn.prepareStatement(
                                                        query);

                        stmt.setString(
                                        1,
                                        title);

                        stmt.executeUpdate();

                        conn.close();

                        System.out.println(
                                        "Book deleted!");

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }


        // =========================================
        // UPDATE BOOK
        // =========================================

        public static void updateBook(

                        int id,

                        Book book) {

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "UPDATE books SET " +

                                                        "title=?, " +

                                                        "author=?, " +

                                                        "price=?, " +

                                                        "image=?, " +

                                                        "description=?, " +

                                                        "category=?, " +

                                                        "pdf=? " +

                                                        "WHERE id=?";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        stmt.setString(
                                        1,
                                        book.getTitle());

                        stmt.setString(
                                        2,
                                        book.getAuthor());

                        stmt.setString(
                                        3,
                                        book.getPrice());

                        stmt.setString(
                                        4,
                                        book.getImage());

                        stmt.setString(
                                        5,
                                        book.getDescription());

                        stmt.setString(
                                        6,
                                        book.getCategory());

                        stmt.setString(
                                        7,
                                        book.getPdf());

                        stmt.setInt(
                                        8,
                                        id);

                        stmt.executeUpdate();

                        conn.close();

                        System.out.println(
                                        "Book updated!");

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================
        // GET TOTAL BOOKS
        // =========================================

        public static int getTotalBooks() {

                int total = 0;

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "SELECT COUNT(*) FROM books";

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
        // GET RECENT BOOKS
        // =========================================

        public static ArrayList<Book> getRecentBooks() {

                ArrayList<Book> books = new ArrayList<>();

                try {

                        Connection conn =

                                        DatabaseConnection
                                                        .getConnection();

                        String query =

                                        "SELECT * FROM books " +

                                                        "ORDER BY id DESC " +

                                                        "LIMIT 5";

                        PreparedStatement stmt =

                                        conn.prepareStatement(query);

                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {

                                Book book = new Book(

                                                rs.getString("title"),

                                                rs.getString("author"),

                                                rs.getString("price"),

                                                rs.getString("image"),

                                                rs.getString("description"),

                                                rs.getString("category"),

                                                rs.getString("pdf"));

                                book.setId(
                                                rs.getInt("id"));

                                books.add(book);
                        }

                        conn.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return books;
        }

}