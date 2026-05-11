package pages;

import components.Sidebar;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.ArrayList;

import services.AuthService;

public class ManageUsersPage {

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
                "👥 Manage Users");

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // USER LIST
        // =========================================

        VBox usersList = new VBox(14);

        ArrayList<String> users =

                AuthService.getAllUsers();

        for (String username : users) {

            usersList.getChildren().add(

                    createUserCard(
                            stage,
                            username));
        }

        // =========================================
        // ADD CONTENT
        // =========================================

        content.getChildren().addAll(
                backBtn,
                title,

                usersList);

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
    // USER CARD
    // =========================================

    private static HBox createUserCard(

            Stage stage,

            String username) {

        HBox card = new HBox(20);

        card.setPadding(
                new Insets(18));

        card.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-background-radius: 16;");

        // =========================================
        // USERNAME
        // =========================================

        Label userLabel = new Label(
                "👤 " + username);

        userLabel.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // BOOK COUNT
        // =========================================

        int booksRead =

                AuthService.getBooksReadCount(
                        username);


        // =========================================
        // JOINED DATE
        // =========================================

        String joinedDate =

                AuthService.getJoinedDate(
                        username);

        Label joinedLabel = new Label(

                "Joined: " + joinedDate);

        joinedLabel.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 13px;");

        Label booksLabel = new Label(

                "Books Read: " + booksRead);

        booksLabel.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 14px;");

        VBox infoBox = new VBox(6);

        infoBox.getChildren().addAll(

                userLabel,

                joinedLabel,

                booksLabel);

        // =========================================
        // REMOVE BUTTON
        // =========================================

        Button removeBtn = new Button(
                "Remove");

        removeBtn.setStyle(

                "-fx-background-color: #DC2626;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 10;" +

                        "-fx-cursor: hand;");

        removeBtn.setOnAction(e -> {

            AuthService.removeUser(
                    username);

            ManageUsersPage.show(stage);
        });

        // =========================================
        // SPACER
        // =========================================

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // =========================================
        // ADD ALL
        // =========================================

        card.getChildren().addAll(

                infoBox,

                spacer,

                removeBtn);

        return card;
    }
}