package pages;

import components.Sidebar;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import models.Book;

import services.BookService;

public class EditBookPage {

    public static void show(

            Stage stage,

            Book book) {

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
                "✏ Edit Book");

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // INPUTS
        // =========================================

        TextField titleField = new TextField(
                book.getTitle());

        TextField authorField = new TextField(
                book.getAuthor());

        TextField priceField = new TextField(
                book.getPrice());

        TextField categoryField = new TextField(
                book.getCategory());

        TextField imageField = new TextField(
                book.getImage()
                        .replace("/assets/books/", ""));

        TextField pdfField = new TextField(
                book.getPdf());

        // =========================================
        // DESCRIPTION
        // =========================================

        TextArea descriptionField = new TextArea(
                book.getDescription());

        descriptionField.setPrefHeight(140);

        // =========================================
        // STYLE INPUTS
        // =========================================

        styleField(titleField);
        styleField(authorField);
        styleField(priceField);
        styleField(categoryField);
        styleField(imageField);
        styleField(pdfField);

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
        // UPDATE BUTTON
        // =========================================

        Button updateBtn = new Button(
                "💾 Update Book");

        updateBtn.setPrefHeight(52);

        updateBtn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 14;" +

                        "-fx-cursor: hand;");

        // =========================================
        // UPDATE ACTION
        // =========================================

        updateBtn.setOnAction(e -> {

            Book updatedBook = new Book(

                    titleField.getText(),

                    authorField.getText(),

                    priceField.getText(),

                    "/assets/books/" +

                            imageField.getText(),

                    descriptionField.getText(),

                    categoryField.getText(),

                    pdfField.getText());

            updatedBook.setId(
                    book.getId());

            BookService.updateBook(

                    book.getId(),

                    updatedBook);

            AdminDashboardPage.show(stage);
        });

        // =========================================
        // ADD CONTENT
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

                updateBtn);

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
    // STYLE FIELD
    // =========================================

    private static void styleField(
            TextField field) {

        field.setPrefHeight(50);

        field.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 14;" +

                        "-fx-font-size: 15px;");
    }
}