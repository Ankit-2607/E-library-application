package pages;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

import javafx.stage.Stage;

import components.Sidebar;

import services.SessionManager;
import services.AuthService;
import services.BookService;
import services.LibraryService;
import services.ReadingProgressService;
import models.Book;

public class AdminDashboardPage {

    public static void show(Stage stage) {

        // =========================================
        // ADMIN PROTECTION
        // =========================================

        if (!SessionManager.isAdmin()) {

            DashboardPage.show(stage);

            return;
        }

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

        VBox content = new VBox(30);

        content.setPadding(
                new Insets(30));

        // =========================================
        // TITLE
        // =========================================

        Label title = new Label(
                "🛠 Admin Dashboard");

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // STATS ROW
        // =========================================

        HBox statsRow = new HBox(20);

        VBox card1 = createStatCard(
                String.valueOf(
                    BookService.getTotalBooks()
                ),
                "Total Books");

        VBox card2 = createStatCard(
            String.valueOf(
                AuthService.getTotalUsers()
            ),
            "Total Users");

        VBox card3 = createStatCard(
            String.valueOf(
                LibraryService.getActiveReaders()
            ),
            "Active Readers");

        VBox card4 = createStatCard(

            String.valueOf(
                ReadingProgressService.getCompletedReads()
            ),
        "Completed Reads");

        statsRow.getChildren().addAll(

                card1,
                card2,
                card3,
                card4);

        // =========================================
        // ACTION ROW
        // =========================================

        HBox actionRow = new HBox(20);

        Button addBookBtn = createActionButton(
                "➕ Add Book");

        Button usersBtn = createActionButton(
                "👥 Manage Users");
        
        usersBtn.setOnAction(e -> {

            ManageUsersPage.show(stage);
        });

        Button analyticsBtn = createActionButton(
                "📊 Analytics");

        addBookBtn.setOnAction(e -> {

            AddBookPage.show(stage);
        });

        actionRow.getChildren().addAll(

                addBookBtn,

                usersBtn,

                analyticsBtn);

        // =========================================
        // RECENT BOOKS TITLE
        // =========================================

        Label recentTitle = new Label(
                "Recent Books");

        recentTitle.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 24px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // RECENT LIST
        // =========================================

        VBox recentList = new VBox(14);

        for (Book book : BookService.getRecentBooks()) {

            recentList.getChildren().add(

                    createRecentBook(
                            stage,
                            book));
        }

        // =========================================
        // ADD CONTENT
        // =========================================

        content.getChildren().addAll(

                title,

                statsRow,

                actionRow,

                recentTitle,

                recentList);

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
    // STAT CARD
    // =========================================

    private static VBox createStatCard(
            String number,
            String text) {

        VBox card = new VBox(10);

        card.setPrefWidth(220);

        card.setPadding(
                new Insets(20));

        card.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-background-radius: 20;");

        Label numberLabel = new Label(number);

        numberLabel.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 30px;" +

                        "-fx-font-weight: bold;");

        Label textLabel = new Label(text);

        textLabel.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 14px;");

        card.getChildren().addAll(

                numberLabel,
                textLabel);

        return card;
    }

    // =========================================
    // ACTION BUTTON
    // =========================================

    private static Button createActionButton(
            String text) {

        Button btn = new Button(text);

        btn.setPrefSize(220, 60);

        btn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 16;" +

                        "-fx-cursor: hand;");

        return btn;
    }

    // =========================================
    // RECENT BOOK ITEM
    // =========================================

    private static HBox createRecentBook(

            Stage stage,

            Book book) {

        HBox item = new HBox(20);

        item.setPadding(
                new Insets(18));

        item.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-background-radius: 16;");

        Label text = new Label(
                "📘 " + book.getTitle());


        Button editBtn = new Button(
                "Edit");

        editBtn.setStyle(

                "-fx-background-color: #2563EB;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 10;" +

                        "-fx-cursor: hand;");

        editBtn.setOnAction(e -> {

            EditBookPage.show(
                    stage,
                    book);
        });

        text.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;");

        Button deleteBtn = new Button(
                "Delete");

        deleteBtn.setStyle(

                "-fx-background-color: #DC2626;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 10;" +

                        "-fx-cursor: hand;");

        deleteBtn.setOnAction(e -> {

            BookService.deleteBook(
                    book.getTitle());

            AdminDashboardPage.show(stage);
        });

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        item.getChildren().addAll(

                text,

                spacer,

                editBtn,

                deleteBtn);

        return item;
    }
}