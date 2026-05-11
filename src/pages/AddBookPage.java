package pages;

import components.Sidebar;
import data.BookData;
import models.Book;
import services.BookService;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class AddBookPage {

    public static void show(Stage stage) {

        // =========================================
        // ROOT
        // =========================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #0F0E17;");

        // =========================================
        // SIDEBAR
        // =========================================

        root.setLeft(

                Sidebar.createSidebar(
                        stage,
                        "admin"));

        // =========================================
        // CONTENT
        // =========================================

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(30));
        
        // =========================================
        // BACK BUTTON
        // =========================================

        Button backBtn = new Button(
                "← Back");

        backBtn.setStyle(

                "-fx-background-color: transparent;" +

                        "-fx-text-fill: #7C3AED;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-cursor: hand;");

        backBtn.setOnAction(e -> {

            AdminDashboardPage.show(stage);
        });

        // =========================================
        // TITLE
        // =========================================

        Label title = new Label(
                "➕ Add New Book");

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // INPUTS
        // =========================================

        TextField titleField = createField(
                "Book Title");

        TextField authorField = createField(
                "Author");

        TextField priceField = createField(
                "Price");

        TextField categoryField = createField(
                "Category");

        TextField imageField = createField(
                "Cover Image Name");

        TextField pdfField = createField(
                "PDF File Name");

        // =========================================
        // DESCRIPTION
        // =========================================

        TextArea descriptionField = new TextArea();

        descriptionField.setPromptText(
                "Book Description");

        descriptionField.setPrefHeight(140);

        descriptionField.setStyle(

                "-fx-control-inner-background: #1A1A2E;" +

                        "-fx-text-fill: white;" +

                        "-fx-highlight-fill: #7C3AED;" +

                        "-fx-highlight-text-fill: white;" +

                        "-fx-background-radius: 14;" +

                        "-fx-font-size: 15px;" +

                        "-fx-focus-color: transparent;" +

                        "-fx-faint-focus-color: transparent;");

        // =========================================
        // SAVE BUTTON
        // =========================================

        Button saveBtn = new Button(
                "💾 Save Book");

        // =========================================
        // SAVE ACTION
        // =========================================

        saveBtn.setOnAction(e -> {

            Book newBook = new Book(

                    titleField.getText(),

                    authorField.getText(),

                    priceField.getText(),

                    "/assets/books/" +

                            imageField.getText(),

                    descriptionField.getText(),

                    categoryField.getText(),
                
                    pdfField.getText());

            BookService.addBook(newBook, pdfField.getText());

            System.out.println(
                    "Book Added: "
                            + titleField.getText());
        });

        // =========================================
        // SAVE BOOK
        // =========================================

        saveBtn.setOnAction(e -> {

            // =========================================
            // CREATE BOOK
            // =========================================

            Book book = new Book(

                    titleField.getText(),

                    authorField.getText(),

                    priceField.getText(),

                    "/assets/books/" +

                            imageField.getText(),

                    descriptionField.getText(),

                    categoryField.getText(),
                    pdfField.getText());

            // =========================================
            // SAVE TO LIST
            // =========================================

            BookData.getBooks().add(book);

            // =========================================
            // CLEAR FIELDS
            // =========================================

            titleField.clear();

            authorField.clear();

            priceField.clear();

            categoryField.clear();

            imageField.clear();

            pdfField.clear();

            descriptionField.clear();

            System.out.println(
                    "Book Added Successfully");
        });

        saveBtn.setPrefHeight(52);

        saveBtn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 14;" +

                        "-fx-cursor: hand;");

        // =========================================
        // ADD ALL
        // =========================================

        content.getChildren().addAll(
                backBtn,
                title,

                titleField,

                authorField,

                priceField,

                categoryField,

                imageField,

                pdfField,

                descriptionField,

                saveBtn);

        root.setCenter(content);

        // =========================================
        // SCENE
        // =========================================

        Scene scene = new Scene(
                root,
                1500,
                900);

        stage.setScene(scene);

        stage.show();
    }

    // =========================================
    // INPUT FIELD
    // =========================================

    private static TextField createField(
            String prompt) {

        TextField field = new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(50);

        field.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-text-fill: white;" +

                        "-fx-prompt-text-fill: #9CA3AF;" +

                        "-fx-background-radius: 14;" +

                        "-fx-font-size: 15px;");

        return field;
    }
}