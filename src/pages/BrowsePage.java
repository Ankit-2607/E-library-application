package pages;

import components.Sidebar;
import components.Topbar;
import components.ScrollWrapper;
// import pages.BookDetailsPage;
import services.WishlistService;
import services.LibraryService;
import services.NavigationManager;
import services.ThemeManager;

import services.BookService;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;

import models.Book;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BrowsePage {

        public static void show(Stage stage) {
                NavigationManager.setCurrentPage(
                        "browse"
                );

                // =========================================
                // ROOT
                // =========================================

                BorderPane root = new BorderPane();

                root.setStyle(
                        "-fx-background-color: "
                                + ThemeManager.getBackgroundColor()
                                + ";");

                // =========================================
                // SIDEBAR
                // =========================================

                root.setLeft(

                                Sidebar.createSidebar(
                                                stage,
                                                "browse"));

                // =========================================
                // CONTENT
                // =========================================

                VBox content = new VBox(28);
                content.setFillWidth(true);

                content.setMinHeight(1200);

                content.setPadding(
                                new Insets(30));

                content.setStyle(
                                "-fx-background-color:"
                                + ThemeManager.getBackgroundColor()
                                + ";");

                // =========================================
                // PAGE TITLE
                // =========================================

                Label title = new Label(
                                "Browse Books");

                title.setStyle(

                                "-fx-text-fill: " +
                                                ThemeManager.getTextColor()
                                                + ";" + 
                                                "-fx-font-size: 36px;" +
                                                "-fx-font-weight: bold;");

                // =========================================
                // SUBTITLE
                // =========================================

                Label subtitle = new Label(
                                "Explore trending and popular books");

                subtitle.setStyle(
                        "-fx-text-fill: " +
                                 ThemeManager.getTextColor()
                                + ";" + 
                                "-fx-font-size: 16px;"
                        );

                // =========================================
                // SEARCH FIELD
                // =========================================

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Search books...");

                searchField.setPrefHeight(45);

                searchField.setMaxWidth(400);

                searchField.setStyle(

                                "-fx-background-color:" +
                                ThemeManager.getCardColor()
                                + ";" +
                                "-fx-text-fill: " +
                                ThemeManager.getTextColor()
                                + ";" + 
                                "-fx-prompt-text-fill: " +
                                ThemeManager.getSubTextColor()
                                + ";" +
                                "-fx-background-radius: 14;" +
                                "-fx-font-size: 14px;");



                // =========================================
                // FILTER ROW
                // =========================================

                HBox filterRow = new HBox(14);

                // =========================================
                // FILTER BUTTONS
                // =========================================

                Button allBtn = createFilterButton("All");

                Button selfHelpBtn = createFilterButton("Self Help");

                Button historyBtn = createFilterButton("History");

                Button fictionBtn = createFilterButton("Science Fiction");

                Button fantasyBtn = createFilterButton("Fantasy");

                Button financeBtn = createFilterButton("Finance");

                // =========================================
                // ADD FILTERS
                // =========================================

                filterRow.getChildren().addAll(

                                allBtn,

                                selfHelpBtn,

                                historyBtn,

                                fictionBtn,

                                fantasyBtn,

                                financeBtn);


                

                // =========================================
                // BOOK GRID
                // =========================================

                FlowPane bookGrid = new FlowPane();

                bookGrid.setHgap(20);

                bookGrid.setVgap(20);

                bookGrid.setPrefWrapLength(1200);

                bookGrid.setPadding(
                                new Insets(10, 0, 20, 0));

                // =========================================
                // BOOK LIST
                // =========================================

                ArrayList<Book> books = BookService.getBooks();

                // =========================================
                // FILTER ACTIONS
                // =========================================

                allBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "All",
                                        stage);
                });

                selfHelpBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "Self Help",
                                        stage);
                });

                historyBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "History",
                                        stage);
                });

                fictionBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "Science Fiction",
                                        stage);
                });

                fantasyBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "Fantasy",
                                        stage);
                });

                financeBtn.setOnAction(e -> {

                        filterBooks(
                                        bookGrid,
                                        books,
                                        "Finance",
                                        stage);
                });

                // =========================================
                // BOOK CARDS
                // =========================================

                for (Book book : books) {

                        bookGrid.getChildren().add(

                                createBookCard(
                                        stage,
                                        book));
                }


                // =========================================
                // SEARCH FUNCTIONALITY
                // =========================================

                searchField.textProperty().addListener(

                                (observable, oldValue, newValue) -> {

                                        bookGrid.getChildren().clear();

                                        for (Book book : books) {

                                                boolean matchesSearch =

                                                                book.getTitle()

                                                                                .toLowerCase()

                                                                                .contains(

                                                                                                newValue
                                                                                                                .toLowerCase());

                                                if (matchesSearch) {

                                                        bookGrid.getChildren().add(

                                                                        createBookCard(
                                                                                        stage,
                                                                                        book));
                                                }
                                        }
                                });

                // =========================================
                // ADD TO CONTENT
                // =========================================

                content.getChildren().addAll(

                        title,

                        subtitle,

                        searchField,

                        filterRow,

                        bookGrid);

                // =========================================
                // SCROLL WRAPPER
                // =========================================

                ScrollWrapper wrapper = new ScrollWrapper(content);

                // =========================================
                // MAIN AREA
                // =========================================

                BorderPane mainArea = new BorderPane();

                mainArea.setTop(
                                Topbar.createTopbar(stage));

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

        // =========================================
        // BOOK CARD
        // =========================================

        private static VBox createBookCard(
                        Stage stage,
                        Book book) {

                String titleText = book.getTitle();
                String authorText = book.getAuthor();
                String priceText = book.getPrice();
                String imageName = book.getImage();

                VBox card = new VBox();

                card.setOnMouseClicked(e -> {

                        BookDetailsPage.show(
                                        stage,
                                        book);
                });

                card.setPrefSize(220, 320);

                card.setStyle(

                        "-fx-background-color: "
                        + ThemeManager.getCardColor()
                        + ";" + 
                        "-fx-background-radius: 20;");

                // =========================================
                // IMAGE SECTION
                // =========================================

                // =========================================
                // IMAGE
                // =========================================

                Image image = new Image(
                                "file:src" + imageName);

                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(220);

                imageView.setFitHeight(210);

                imageView.setPreserveRatio(false);

                // =========================================
                // DETAILS
                // =========================================

                VBox details = new VBox(8);

                details.setPadding(
                                new Insets(16));

                // TITLE

                Label title = new Label(titleText);

                title.setStyle(
                        "-fx-text-fill: " +
                                ThemeManager.getTextColor()
                                + ";" +
                                "-fx-font-size: 16px;" +
                                "-fx-font-weight: bold;"
                        );

                // AUTHOR

                Label author = new Label(authorText);

                author.setStyle(
                        "-fx-text-fill: "
                        + ThemeManager.getTextColor()
                        + ";" 
                        );
                // PRICE

                Label price = new Label(priceText);

                price.setStyle(

                                "-fx-text-fill: #7C3AED;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;");

                // =========================================
                // WISHLIST BUTTON
                // =========================================

                Button wishlistBtn;

                if (WishlistService.hasBook(titleText)) {

                        wishlistBtn = new Button("♥");

                } else {

                        wishlistBtn = new Button("♡");
                }

                wishlistBtn.setStyle(

                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #7C3AED;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                // =========================================
                // BUTTON ACTION
                // =========================================

                wishlistBtn.setOnAction(e -> {

                        WishlistService.toggleBook(
                                        titleText);

                        if (WishlistService.hasBook(titleText)) {

                                wishlistBtn.setText("♥");

                        } else {

                                wishlistBtn.setText("♡");
                        }
                        e.consume();
                });

                // =========================================
                // LIBRARY BUTTON
                // =========================================

                Button libraryBtn;

                if (LibraryService.hasBook(titleText)) {

                        libraryBtn = new Button("✓");

                } else {

                        libraryBtn = new Button("📚");
                }

                libraryBtn.setStyle(

                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #10B981;" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                // =========================================
                // BUTTON ACTION
                // =========================================

                libraryBtn.setOnAction(e -> {

                        LibraryService.toggleBook(
                                        titleText);

                        if (LibraryService.hasBook(titleText)) {

                                libraryBtn.setText("✓");

                        } else {

                                libraryBtn.setText("📚");
                        }
                        e.consume();
                });

                // =========================================
                // PRICE + BUTTON ROW
                // =========================================

                HBox bottomRow = new HBox();

                bottomRow.setSpacing(10);

                bottomRow.getChildren().addAll(

                                price,

                                wishlistBtn,

                                libraryBtn);

                // =========================================
                // ADD DETAILS
                // =========================================

                details.getChildren().addAll(

                                title,

                                author,

                                bottomRow);

                // =========================================
                // ADD TO CARD
                // =========================================

                card.getChildren().addAll(

                                imageView,

                                details);

                return card;
        }


        // =========================================
        // FILTER BUTTON
        // =========================================

        private static Button createFilterButton(
                        String text) {

                Button btn = new Button(text);

                btn.setStyle(

                        "-fx-background-color: " +
                                ThemeManager.getCardColor()
                                + ";" +                                        "-fx-text-fill: " +
                                ThemeManager.getTextColor()
                                + ";" +
                                "-fx-font-size: 14px;" +
                                "-fx-background-radius: 12;" +
                                "-fx-padding: 10 18;" +
                                "-fx-cursor: hand;");

                return btn;
        }


        // =========================================
        // FILTER BOOKS
        // =========================================

        private static void filterBooks(

                        FlowPane bookGrid,

                        ArrayList<Book> books,

                        String category,

                        Stage stage) {

                bookGrid.getChildren().clear();

                for (Book book : books) {

                        if (

                        category.equals("All")

                                        ||

                                        book.getCategory()
                                                        .equals(category)) {

                                bookGrid.getChildren().add(

                                                createBookCard(
                                                                stage,
                                                                book));
                        }
                }
        }
}