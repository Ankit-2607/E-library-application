package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.paint.*;
// import javafx.scene.text.Font;
import services.AuthService;
import services.SessionManager;
// import pages.DashboardPage;
import javafx.stage.Stage;


public class LoginPage {

    public static void show(Stage stage) {

        // ================= ROOT =================
        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #0F0E17;");

        // ================= LEFT SIDE =================
        VBox left = new VBox(20);

        left.setAlignment(Pos.CENTER_LEFT);
        left.setPadding(new Insets(80));

        left.setPrefWidth(700);

        Label logo = new Label("📚 BiblioX");

        logo.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;");

        Label heading = new Label("Welcome Back");

        heading.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;");

        Label sub = new Label(
                "Access thousands of books,\nmanage your library and continue reading.");

        sub.setStyle(
                "-fx-text-fill: #9CA3AF;" +
                        "-fx-font-size: 18px;");

        left.getChildren().addAll(
                logo,
                heading,
                sub);

        // ================= RIGHT SIDE =================
        StackPane right = new StackPane();

        right.setPadding(new Insets(40));

        VBox card = new VBox(20);

        card.setAlignment(Pos.CENTER_LEFT);

        card.setMaxWidth(420);

        card.setPadding(new Insets(40));

        card.setStyle(
                "-fx-background-color: #1A1A2E;" +
                        "-fx-background-radius: 24;");

        Label loginTitle = new Label("Login");

        loginTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;");

        Label loginSub = new Label(
                "Enter your credentials to continue");

        loginSub.setStyle(
                "-fx-text-fill: #9CA3AF;" +
                        "-fx-font-size: 14px;");

        // ================= EMAIL FIELD =================

        TextField username = new TextField();

        VBox emailBox = createInput(
                "Username / Email",
                username);

        // ================= PASSWORD FIELD =================

        PasswordField password = new PasswordField();

        VBox passBox = createInput(
                "Password",
                password);

        // =========================================
        // MESSAGE LABEL
        // =========================================

        Label messageLabel = new Label();

        messageLabel.setStyle(

                        "-fx-text-fill: #EF4444;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;");

        // ================= LOGIN BUTTON =================
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {

            String user = username.getText();

            String pass = password.getText();

                boolean success;

                // =========================================
                // ADMIN LOGIN
                // =========================================

                if (

                user.equals("admin")

                                &&

                                pass.equals("admin123")) {

                        success = true;

                } else {

                        success = AuthService.login(
                                        user,
                                        pass);
                }

            if (success) {
                        String fullName;

                        if (user.equals("admin")) {

                                fullName = "Administrator";

                        } else {

                                fullName = AuthService.getUsername(user);
                        }

                        SessionManager.setCurrentUser(fullName);

                        // =========================================
                        // ADMIN CHECK
                        // =========================================

                        if (
                        user.equals("admin")
                        &&
                        pass.equals("admin123")) {

                                SessionManager.setCurrentRole(
                                                "ADMIN");

                        } else {

                                SessionManager.setCurrentRole(
                                                "USER");
                        }

                DashboardPage.show(stage);
                messageLabel.setText("");

            } else {

                messageLabel.setText("Invalid username or password");
            }
        });

        loginBtn.setPrefWidth(Double.MAX_VALUE);
        loginBtn.setPrefHeight(50);

        loginBtn.setStyle(
                "-fx-background-color: #7C3AED;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;");

        loginBtn.setOnMouseEntered(e -> {

            loginBtn.setStyle(
                    "-fx-background-color: #8B5CF6;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 14;");
        });

        loginBtn.setOnMouseExited(e -> {

            loginBtn.setStyle(
                    "-fx-background-color: #7C3AED;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 14;");
        });

        // ================= REGISTER =================
        Hyperlink register = new Hyperlink(
                "Create new account");

        register.setOnAction(e -> {
            RegisterPage.show(stage);
        });

        register.setStyle(
                "-fx-text-fill: #A78BFA;" +
                        "-fx-font-size: 14px;");

        card.getChildren().addAll(
                loginTitle,
                loginSub,
                emailBox,
                passBox,
                messageLabel,
                loginBtn,
                register);

        right.getChildren().add(card);

        // ================= MAIN LAYOUT =================
        HBox body = new HBox();

        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        body.getChildren().addAll(
                left,
                right);

        root.setCenter(body);

        // ================= SCENE =================
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    // =================================================
    // INPUT FIELD
    // =================================================

    private static VBox createInput(
            String labelText,
            TextField field) {

        VBox box = new VBox(8);

        Label label = new Label(labelText);

        label.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;");

        field.setPrefHeight(48);

        field.setStyle(
                "-fx-background-color: #24243E;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #777;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #2D2D4D;" +
                        "-fx-font-size: 14px;");

        box.getChildren().addAll(
                label,
                field);

        return box;
    }
}