package pages;

import components.Sidebar;

import components.ScrollWrapper;
import services.ReadingProgressService;
import models.Book;

import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.rendering.PDFRenderer;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.layout.HBox;

public class ReaderPage {


        private static int currentPage = 0;
        private static String currentBook = "";

    public static void show(
            Stage stage,
            Book book) {


            // =========================================
            // LOAD SAVED PAGE ONLY ON NEW BOOK
            // =========================================

            if (!book.getTitle().equals(currentBook)) {

                    currentPage = ReadingProgressService.getSavedPage(book.getTitle());

                    currentBook = book.getTitle();
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
                        ""));

        // =========================================
        // CONTENT
        // =========================================

        VBox content = new VBox(24);

        // content.setAlignment(
        //                 javafx.geometry.Pos.TOP_CENTER);

        content.setPadding(
                new Insets(30));

        content.setStyle(
                "-fx-background-color: #0F0E17;");

        // =========================================
        // BACK BUTTON
        // =========================================

        Button backBtn = new Button(
                "← Back to Details");

        backBtn.setStyle(

                "-fx-background-color: transparent;" +

                        "-fx-text-fill: #7C3AED;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-cursor: hand;");

        backBtn.setOnAction(e -> {

            BookDetailsPage.show(
                    stage,
                    book);
        });

        // =========================================
        // BOOK TITLE
        // =========================================

        Label title = new Label(
                "📖 " + book.getTitle());

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 38px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // AUTHOR
        // =========================================

        Label author = new Label(
                "by " + book.getAuthor());

        author.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 18px;");

        /// =========================================
        // PDF CONTAINER
        // =========================================

        VBox pdfContainer = new VBox(20);

        pdfContainer.setAlignment(
                        javafx.geometry.Pos.CENTER);


        // =========================================
        // PDF PATH
        // =========================================

        String pdfPath = "src/assets/pdfs/" + book.getPdf();
                System.out.println(pdfPath);

        try {

            PDDocument document =

        Loader.loadPDF(
                new File(pdfPath)
                );

            PDFRenderer renderer = new PDFRenderer(document);

            // =========================================
            // TOTAL PAGES
            // =========================================

            int totalPages = document.getNumberOfPages();

            ReadingProgressService.saveProgress(

                            book.getTitle(),

                            currentPage,

                            totalPages);

            // =========================================
            // SAFETY CHECK
            // =========================================

            if (currentPage >= totalPages) {

                    currentPage = totalPages - 1;
            }

            // =========================================
            // RENDER CURRENT PAGE
            // =========================================

            BufferedImage bufferedImage =

                            renderer.renderImageWithDPI(

                                            currentPage,

                                            100);

            WritableImage image =

                            SwingFXUtils.toFXImage(
                                            bufferedImage,
                                            null);

            ImageView pageView = new ImageView(image);

            pageView.setFitWidth(700);
            pageView.setFitHeight(850);

            pageView.setSmooth(true);
            pageView.setCache(true);

            pageView.setPreserveRatio(true);

            pdfContainer.getChildren()
                            .add(pageView);

            // =========================================
            // PAGE LABEL
            // =========================================

            Label pageLabel = new Label(

                            "Page " + (currentPage + 1) + " / " + totalPages);

            pageLabel.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;"
                );

            // =========================================
            // BUTTONS
            // =========================================

            Button prevBtn = new Button(
                            "◀ Previous");

            Button nextBtn = new Button(
                            "Next ▶");

            // =========================================
            // BUTTON STYLE
            // =========================================

            String btnStyle =

                            "-fx-background-color: #7C3AED;" +

                                            "-fx-text-fill: white;" +

                                            "-fx-font-size: 16px;" +

                                            "-fx-font-weight: bold;" +

                                            "-fx-background-radius: 10;" +

                                            "-fx-cursor: hand;" +

                                            "-fx-padding: 10 18;";

            prevBtn.setStyle(btnStyle);

            nextBtn.setStyle(btnStyle);

            // =========================================
            // BUTTON ACTIONS
            // =========================================

            prevBtn.setOnAction(e -> {

                    if (currentPage > 0) {

                            currentPage--;

                            ReadingProgressService.saveProgress(

                                            book.getTitle(),

                                            currentPage,

                                            totalPages);

                            show(stage, book);
                    }
            });

            nextBtn.setOnAction(e -> {

                    if (currentPage < totalPages - 1) {

                        currentPage++;

                            ReadingProgressService.saveProgress(

                                            book.getTitle(),

                                            currentPage,

                                            totalPages);

                            show(stage, book);
                    }
            });

            // =========================================
            // NAVIGATION BOX
            // =========================================

            HBox navBox = new HBox(20);

            navBox.setAlignment(
                            javafx.geometry.Pos.CENTER);

            navBox.getChildren().addAll(

                            prevBtn,

                            pageLabel,

                            nextBtn);

            pdfContainer.getChildren()
                            .add(navBox);

            document.close();

        } catch (Exception e) {

            e.printStackTrace();

            Label error = new Label(
                    "Failed to load PDF");

            error.setStyle(

                    "-fx-text-fill: red;" +

                            "-fx-font-size: 20px;");

            pdfContainer.getChildren()
                    .add(error);
        }
        

        // =========================================
        // ADD TO CONTENT
        // =========================================

        content.getChildren().addAll(


                title,

                author,

                pdfContainer);
        

        // =========================================
        // SCROLL WRAPPER
        // =========================================

        ScrollWrapper wrapper = new ScrollWrapper(content);


        // =========================================
        // TOP SECTION
        // =========================================

        VBox topSection = new VBox();

        topSection.setPadding(
                        new Insets(15, 20, 15, 20));

        topSection.setStyle(
                        "-fx-background-color: #16162B;");

        topSection.getChildren().add(

                        backBtn);

        // =========================================
        // MAIN AREA
        // =========================================

        BorderPane mainArea = new BorderPane();

        mainArea.setTop(topSection);

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