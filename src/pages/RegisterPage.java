package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import services.AuthService;

public class RegisterPage {

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

        Label heading = new Label("Create Account");

        heading.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;");

        Label sub = new Label(
                "Create your digital library account\nand start reading today.");

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

        card.setMaxWidth(450);

        card.setPadding(new Insets(40));

        card.setStyle(
                "-fx-background-color: #1A1A2E;" +
                        "-fx-background-radius: 24;");

        // ================= TITLE =================
        Label registerTitle = new Label("Register");

        registerTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;");

        Label registerSub = new Label(
                "Fill all details to create your account");

        registerSub.setStyle(
                "-fx-text-fill: #9CA3AF;" +
                        "-fx-font-size: 14px;");

        // ================= INPUTS =================

        TextField fullName = new TextField();

        VBox nameBox = createInput(
                "Full Name",
                fullName);

        // EMAIL
        TextField username = new TextField();

        VBox emailBox = createInput(
                "Email",
                username);

        // PASSWORD
        PasswordField password = new PasswordField();

        VBox passBox = createInput(
                "Password",
                password);

        // CONFIRM PASSWORD
        PasswordField confirmPassword = new PasswordField();

        VBox confirmBox = createInput(
                "Confirm Password",
                confirmPassword);

                // ================= MESSAGE LABEL =================

        Label message = new Label();

        message.setStyle(
                        "-fx-text-fill: #EF4444;" +
                                        "-fx-font-size: 14px;" +
                                        "-fx-font-weight: bold;");

        // ================= REGISTER BUTTON =================

        Button registerBtn = new Button("Create Account");

        registerBtn.setOnAction(e -> {

                String full = fullName.getText();

                String user = username.getText();

                String pass = password.getText();

                String confirm = confirmPassword.getText();

                // =========================================
                // VALIDATION
                // =========================================

                if (

                full.isEmpty()

                                ||

                                user.isEmpty()

                                ||

                                pass.isEmpty()

                                ||

                                confirm.isEmpty()) {

                        message.setText(
                                        "Fill all fields");

                        return;
                }

                // =========================================
                // PASSWORD MATCH
                // =========================================

                if (!pass.equals(confirm)) {

                        message.setText(
                                        "Passwords do not match");

                        return;
                }

                // =========================================
                // REGISTER
                // =========================================

                boolean success = AuthService.register(

                                full,

                                user,

                                pass);

            if (success) {

                        message.setStyle(
                                "-fx-text-fill: #10B981;" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;");

                message.setText(
                        "Account created successfully!");

            } else {

                        message.setStyle(
                                        "-fx-text-fill: #EF4444;" +
                                        "-fx-font-size: 14px;" +
                                        "-fx-font-weight: bold;");

                message.setText(
                        "User already exists");
            }
        });

        registerBtn.setPrefWidth(Double.MAX_VALUE);
        registerBtn.setPrefHeight(50);

        registerBtn.setStyle(
                "-fx-background-color: #7C3AED;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;");

        registerBtn.setOnMouseEntered(e -> {

            registerBtn.setStyle(
                    "-fx-background-color: #8B5CF6;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 14;");
        });

        registerBtn.setOnMouseExited(e -> {

            registerBtn.setStyle(
                    "-fx-background-color: #7C3AED;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 14;");
        });

        // ================= LOGIN LINK =================
        Hyperlink loginLink = new Hyperlink(
                "Already have an account? Login");

        loginLink.setStyle(
                "-fx-text-fill: #A78BFA;" +
                        "-fx-font-size: 14px;");

        loginLink.setOnAction(e -> {
            LoginPage.show(stage);
        });

        // ================= ADD =================
        card.getChildren().addAll(
                registerTitle,
                registerSub,
                nameBox,
                emailBox,
                passBox,
                confirmBox,
                message,
                registerBtn,
                loginLink);

        right.getChildren().add(card);

        // ================= BODY =================
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