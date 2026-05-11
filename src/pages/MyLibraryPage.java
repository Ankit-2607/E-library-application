package pages;

import components.Sidebar;
import components.Topbar;
import data.BookData;
import components.ScrollWrapper;

import services.LibraryService;
import services.NavigationManager;
import services.ThemeManager;
import javafx.geometry.Insets;

import models.Book;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


public class MyLibraryPage {

        public static void show(Stage stage) {

                NavigationManager.setCurrentPage(
                        "library"
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
                                                "library"));

                // =========================================
                // CONTENT
                // =========================================

                VBox content = new VBox(28);

                content.setFillWidth(true);

                content.setMinHeight(1200);

                content.setPadding(
                                new Insets(30));

                content.setStyle(
                        "-fx-background-color: "
                                + ThemeManager.getBackgroundColor()
                                + ";");

                // =========================================
                // TITLE
                // =========================================

                Label title = new Label(
                                "My Library");

                title.setStyle(
                        "-fx-text-fill: " +
                                ThemeManager.getTextColor()
                                + ";" +
                                "-fx-font-size: 36px;" +
                                "-fx-font-weight: bold;"
                        );

                // =========================================
                // SUBTITLE
                // =========================================

                Label subtitle = new Label(
                                "Your owned and reading books");

                subtitle.setStyle(

                                "-fx-text-fill: " +
                                ThemeManager.getSubTextColor()
                                + ";" +
                                "-fx-font-size: 16px;");

                // =========================================
                // GRID
                // =========================================

                FlowPane grid = new FlowPane();

                grid.setHgap(20);

                grid.setVgap(20);

                grid.setPrefWrapLength(1200);

                // =========================================
                // LOAD LIBRARY BOOKS
                // =========================================

                for (String book : LibraryService.getBooks()) {

                        VBox card = createLibraryCard(book);

                        grid.getChildren().add(card);
                }

                // =========================================
                // ADD CONTENT
                // =========================================

                content.getChildren().addAll(

                                title,

                                subtitle,

                                grid);

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
        // LIBRARY CARD
        // =========================================

        private static VBox createLibraryCard(
                        String bookName) {

                VBox card = new VBox();

                card.setPrefSize(220, 300);

                card.setStyle(
                        "-fx-background-color: " +
                                ThemeManager.getCardColor()
                                + ";" +
                                "-fx-background-radius: 20;");

                // =========================================
                // IMAGE
                // =========================================

                String imagePath = "";

for (Book book : BookData.getBooks()) {

    if (book.getTitle().equals(bookName)) {

        imagePath = "file:src" + book.getImage();

        break;
    }
}

Image image = new Image(imagePath);

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

                Label title = new Label(bookName);

                title.setStyle(
                        "-fx-text-fill: " +
                                ThemeManager.getTextColor()
                                + ";" +
                                "-fx-font-size: 16px;" +
                                "-fx-font-weight: bold;");

                Label owned = new Label(
                                "Available in Library");

                owned.setStyle(

                                "-fx-text-fill: " +
                                ThemeManager.getSubTextColor() 
                                + ";"
                        );

                details.getChildren().addAll(

                                title,

                                owned);

                // =========================================
                // ADD TO CARD
                // =========================================

                card.getChildren().addAll(

                                imageView,

                                details);

                return card;
        }
}