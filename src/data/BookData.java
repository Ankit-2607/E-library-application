package data;

import java.util.ArrayList;

import models.Book;

public class BookData {

    // =========================================
    // GLOBAL BOOK LIST
    // =========================================

    private static ArrayList<Book> books = new ArrayList<>();

    // =========================================
    // GET BOOKS
    // =========================================

    public static ArrayList<Book> getBooks() {

        // =========================================
        // PREVENT DUPLICATES
        // =========================================

        if (!books.isEmpty()) {

            return books;
        }

        // =========================================
        // DEFAULT BOOKS
        // =========================================

        books.add(

                new Book(

                        "Atomic Habits",

                        "James Clear",

                        "$19.99",

                        "/assets/books/atomichabits.jpg",

                        "Tiny changes, remarkable results.",

                        "Self Help",
                        "atomichabits.pdf"));

        books.add(

                new Book(

                        "Sapiens",

                        "Yuval Noah Harari",

                        "$24.99",

                        "/assets/books/sapiens.jpg",

                        "History of humankind.",

                        "History",
                        "sapiens.pdf"));

        books.add(

                new Book(

                        "Dune",

                        "Frank Herbert",

                        "$29.99",

                        "/assets/books/dune.jpg",

                        "Epic science fiction novel.",

                        "Science Fiction",

                        "dune.pdf"));

        books.add(

                new Book(

                        "Harry Potter",

                        "J.K. Rowling",

                        "$18.99",

                        "/assets/books/harrypotter.jpg",

                        "Wizarding world adventure.",

                        "Fantasy",
                        "harrypotter.pdf"));

        books.add(

                new Book(

                        "The Alchemist",

                        "Paulo Coelho",

                        "$14.99",

                        "/assets/books/alchemist.jpg",

                        "Journey of destiny.",

                        "Self Help",
                        "alchemist.pdf"));

        books.add(

                new Book(

                        "Rich Dad Poor Dad",

                        "Robert Kiyosaki",

                        "$21.99",

                        "/assets/books/richdadpoordad.jpg",

                        "Financial education book.",

                        "Finance",
                        "richdadpoordad.pdf"));

        return books;
    }
}