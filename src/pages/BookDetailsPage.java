package pages;

import components.Sidebar;
import components.ScrollWrapper;
// import pages.BrowsePage;
import services.WishlistService;
import services.LibraryService;
import services.NavigationManager;
// import pages.ReaderPage;
import services.ThemeManager;
import models.Book;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class BookDetailsPage {

    public static void show(Stage stage, Book book) {

        NavigationManager.setCurrentPage(
                "details");

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
                        ""));


        VBox pageContent = new VBox(20);

        pageContent.setPadding(
                new Insets(30));

        pageContent.setStyle(
                "-fx-background-color: #0F0E17;");

        
        // =========================================
        // CONTENT
        // =========================================

        HBox content = new HBox(40);


        content.setStyle(
                "-fx-background-color: #0F0E17;");

        
                
            // =========================================
        // BACK BUTTON
        // =========================================

        Button backBtn = new Button(
                "← Back");

        backBtn.setStyle(

                "-fx-background-color: transparent;" +

                        "-fx-text-fill: #7C3AED;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-cursor: hand;");

        // =========================================
        // ACTION
        // =========================================

        backBtn.setOnAction(e -> {

            BrowsePage.show(stage);
        });

        // =========================================
        // BOOK IMAGE
        // =========================================

        Image image = new Image(

                "file:src" + book.getImage());

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(320);

        imageView.setFitHeight(480);

        imageView.setPreserveRatio(false);

        // =========================================
        // DETAILS
        // =========================================

        VBox details = new VBox(20);

        // TITLE

        Label title = new Label(
                book.getTitle());

        title.setStyle(

                "-fx-text-fill: white;" +
                        "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;");

        // AUTHOR

        Label author = new Label(
                "by " + book.getAuthor());

        author.setStyle(

                "-fx-text-fill: #9CA3AF;" +
                        "-fx-font-size: 20px;");

        // RATING

        Label rating = new Label(
                "⭐ 4.8 Rating");

        rating.setStyle(

                "-fx-text-fill: #FBBF24;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        // PRICE

        Label price = new Label(
                book.getPrice());

        price.setStyle(

                "-fx-text-fill: #10B981;" +
                        "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;");

        // DESCRIPTION

        Label description = new Label(

                book.getDescription());

        description.setWrapText(true);

        description.setMaxWidth(600);

        description.setStyle(

                "-fx-text-fill: #D1D5DB;" +
                        "-fx-font-size: 16px;" +
                        "-fx-line-spacing: 6;");

        // =========================================
        // BUTTONS
        // =========================================

        HBox buttonRow = new HBox(20);

        // WISHLIST BUTTON

        Button wishlistBtn;

        if (WishlistService.hasBook(
                book.getTitle())) {

            wishlistBtn = new Button(
                    "♥ Added");

        } else {

            wishlistBtn = new Button(
                    "♡ Wishlist");
        }

        wishlistBtn.setOnAction(e -> {

            WishlistService.toggleBook(
                    book.getTitle());

            if (WishlistService.hasBook(
                    book.getTitle())) {

                wishlistBtn.setText(
                        "♥ Added");

            } else {

                wishlistBtn.setText(
                        "♡ Wishlist");
            }
        });

        wishlistBtn.setStyle(

                "-fx-background-color: #7C3AED;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;" +
                        "-fx-padding: 14 24;" +
                        "-fx-cursor: hand;");

        // LIBRARY BUTTON

        Button libraryBtn;

        if (LibraryService.hasBook(
                book.getTitle())) {

            libraryBtn = new Button(
                    "✓ Added");

        } else {

            libraryBtn = new Button(
                    "📚 Add to Library");
        }


        libraryBtn.setOnAction(e -> {

            LibraryService.toggleBook(
                    book.getTitle());

            if (LibraryService.hasBook(
                    book.getTitle())) {

                libraryBtn.setText(
                        "✓ Added");

            } else {

                libraryBtn.setText(
                        "📚 Add to Library");
            }
        });

        libraryBtn.setStyle(

                "-fx-background-color: #10B981;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;" +
                        "-fx-padding: 14 24;" +
                        "-fx-cursor: hand;");

        // =========================================
        // READ BUTTON
        // =========================================

        Button readBtn = new Button(
                "📖 Read Now");

        readBtn.setOnAction(e -> {

            ReaderPage.show(
                    stage,
                    book);
        });    

        readBtn.setStyle(

                "-fx-background-color: #2563EB;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 14;" +

                        "-fx-padding: 14 24;" +

                        "-fx-cursor: hand;");

        buttonRow.getChildren().addAll(

                wishlistBtn,

                libraryBtn,

                readBtn);

        // =========================================
        // ADD DETAILS
        // =========================================

        details.getChildren().addAll(

                title,

                author,

                rating,

                price,

                description,

                buttonRow);

        // =========================================
        // ADD CONTENT
        // =========================================

        content.getChildren().addAll(

                imageView,

                details);

        pageContent.getChildren().addAll(

                backBtn,

                content);

        // =========================================
        // SCROLL WRAPPER
        // =========================================

        ScrollWrapper wrapper = new ScrollWrapper(pageContent);

        // =========================================
        // MAIN AREA
        // =========================================

        BorderPane mainArea = new BorderPane();

        mainArea.setStyle(

        "-fx-background-color: "
        + ThemeManager.getBackgroundColor()
        + ";");

        mainArea.setCenter(
                wrapper.getPane());

        // =========================================
        // CENTER
        // =========================================

        root.setCenter(mainArea);

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


}