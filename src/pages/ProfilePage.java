package pages;

import components.Sidebar;
import components.Topbar;
import services.UserService;
import javafx.stage.FileChooser;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import services.ThemeManager;
import services.SessionManager;

public class ProfilePage {

    public static void show(Stage stage) {

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
                        "profile"));

        // =========================================
        // TOPBAR
        // =========================================

        root.setTop(
                Topbar.createTopbar(stage));

        // =========================================
        // CONTENT
        // =========================================

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(40));

        // =========================================
        // TITLE
        // =========================================

        Label title = new Label(
                "My Profile");

        title.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        // =========================================
        // USERNAME
        // =========================================

        TextField usernameField = new TextField(
                SessionManager.getCurrentUser());

        // =========================================
        // EMAIL
        // =========================================

        TextField emailField = new TextField(
                UserService.getEmail(
                        SessionManager.getCurrentUser()));

        emailField.setPromptText(
                "Enter email");

        // =========================================
        // PHONE
        // =========================================

        TextField phoneField = new TextField(
                UserService.getPhone(
                        SessionManager.getCurrentUser()));

        phoneField.setPromptText(
                "Enter phone number");

        // =========================================
        // INPUT STYLING
        // =========================================

        String inputStyle =

                "-fx-background-color: #1A1A2E;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 12;" +

                        "-fx-padding: 14;" +

                        "-fx-font-size: 15px;";

        usernameField.setStyle(inputStyle);

        emailField.setStyle(inputStyle);

        phoneField.setStyle(inputStyle);

        usernameField.setMaxWidth(400);

        emailField.setMaxWidth(400);

        phoneField.setMaxWidth(400);

        // =========================================
        // SAVE BUTTON
        // =========================================
        ImageView profileImage = new ImageView();

        final String[] selectedImagePath = { null };

        Button saveBtn = new Button(
                "Save Changes");

        saveBtn.setOnAction(e -> {

            UserService.updateProfile(

                    SessionManager.getCurrentUser(),

                    usernameField.getText(),

                    emailField.getText(),

                    phoneField.getText());
                    
            SessionManager.setCurrentUser(

                    usernameField.getText());


            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);

            alert.setContentText(
                    "Profile updated successfully!");

            alert.showAndWait();
        });

        saveBtn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 12;" +

                        "-fx-padding: 12 24;" +

                        "-fx-cursor: hand;");

        // =========================================
        // PROFILE HEADER CARD
        // =========================================

        HBox profileHeader = new HBox(40);

        profileHeader.setPadding(
                new Insets(30));

        profileHeader.setStyle(

                "-fx-background-color: #111827;" +

                        "-fx-background-radius: 24;");

        // =========================================
        // LEFT PROFILE SECTION
        // =========================================

        HBox leftProfile = new HBox(25);

        leftProfile.setAlignment(Pos.CENTER_LEFT);

        // =========================================
        // PROFILE IMAGE
        // =========================================


        // =========================================
        // SAVE BUTTON
        // =========================================

        String savedImage =

                UserService.getProfileImage(
                        SessionManager.getCurrentUser());

        try {

            if (savedImage != null &&
                    !savedImage.isEmpty()) {

                profileImage.setImage(

                        new Image(
                                new File(savedImage)
                                        .toURI()
                                        .toString()));

            } else {

                profileImage.setImage(

                        new Image(
                                "file:src/assets/profile/default.png"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        profileImage.setFitWidth(110);

        profileImage.setFitHeight(110);


        // =========================================
        // PROFILE CONTAINER
        // =========================================

        StackPane profileContainer = new StackPane();

        profileContainer.setMinSize(150, 150);

        profileContainer.setMaxSize(150, 150);

        profileContainer.setPrefSize(150, 150);

        // =========================================
        // CIRCLE BORDER
        // =========================================

        Circle borderCircle = new Circle(66);

        borderCircle.setStyle(

                "-fx-fill: transparent;" +

                        "-fx-stroke: #7C3AED;" +

                        "-fx-stroke-width: 3;");

        /// =========================================
        // PROFILE IMAGE SIZE
        // =========================================

        profileImage.setFitWidth(124);

        profileImage.setFitHeight(124);

        // =========================================
        // PERFECT CIRCLE CLIP
        // =========================================

        Circle imageClip = new Circle(62, 62, 62);

        profileImage.setClip(imageClip);
        

        // =========================================
        // CAMERA BUTTON
        // =========================================

        Button cameraBtn = new Button("📷");

        cameraBtn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-background-radius: 100;" +

                        "-fx-font-size: 18px;" +

                        "-fx-pref-width: 45px;" +

                        "-fx-pref-height: 45px;" +

                        "-fx-cursor: hand;");

        // SAME ACTION AS CHANGE PHOTO
        cameraBtn.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle(
                    "Choose Profile Image");

            fileChooser.getExtensionFilters().addAll(

                    new FileChooser.ExtensionFilter(
                            "Image Files",
                            "*.png",
                            "*.jpg",
                            "*.jpeg"));

            File selectedFile =

                    fileChooser.showOpenDialog(
                            stage);

            if (selectedFile != null) {

                Image image = new Image(

                        selectedFile.toURI().toString());

                profileImage.setImage(image);

                selectedImagePath[0] = selectedFile.getAbsolutePath();
            }
        });

        // =========================================
        // POSITION CAMERA BUTTON
        // =========================================

        StackPane.setAlignment(
                cameraBtn,
                Pos.BOTTOM_RIGHT);

        // =========================================
        // ADD TO CONTAINER
        // =========================================

        profileContainer.getChildren().addAll(

                borderCircle,

                profileImage);

        StackPane cameraWrapper = new StackPane(cameraBtn);

        cameraWrapper.setMaxSize(
                Region.USE_PREF_SIZE,
                Region.USE_PREF_SIZE);

        StackPane.setAlignment(
                cameraWrapper,
                Pos.BOTTOM_RIGHT);

        cameraWrapper.setTranslateX(-8);

        cameraWrapper.setTranslateY(-8);

        profileContainer.getChildren().add(
                cameraWrapper);


        // =========================================
        // SAVE IMAGE BUTTON
        // =========================================

        Button saveImageBtn = new Button(
                "Save Image");

        saveImageBtn.setStyle(

                "-fx-background-color: #7C3AED;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-size: 14px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 12;" +

                        "-fx-padding: 10 20;" +

                        "-fx-cursor: hand;");

        // =========================================
        // SAVE PROFILE IMAGE
        // =========================================

        saveImageBtn.setOnAction(e -> {

            if (selectedImagePath[0] != null) {

                UserService.saveProfileImage(

                        SessionManager.getCurrentUser(),

                        selectedImagePath[0]);

                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);

                alert.setContentText(
                        "Profile image updated!");

                alert.showAndWait();
            }
        });

        // =========================================
        // MAIN IMAGE SECTION
        // =========================================

        VBox imageSection = new VBox(15);

        imageSection.setAlignment(Pos.CENTER);

        imageSection.getChildren().addAll(

                profileContainer,

                saveImageBtn);
        // =========================================
        // USER INFO
        // =========================================

        VBox userInfo = new VBox(10);

        Label bigUsername = new Label(
                SessionManager.getCurrentUser());

        bigUsername.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 34px;" +

                        "-fx-font-weight: bold;");

        Label roleLabel = new Label(
                "Book lover • Dreamer • Learner");

        roleLabel.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 15px;");

        Label joinedLabel = new Label(
                "📅 Joined May 2026");

        joinedLabel.setStyle(

                "-fx-text-fill: #D1D5DB;" +

                        "-fx-font-size: 14px;");

        Label countryLabel = new Label(
                "📍 India");

        countryLabel.setStyle(

                "-fx-text-fill: #D1D5DB;" +

                        "-fx-font-size: 14px;");

        userInfo.getChildren().addAll(

                bigUsername,

                roleLabel,

                joinedLabel,

                countryLabel);

        leftProfile.getChildren().addAll(

                imageSection,

                userInfo);

        // =========================================
        // RIGHT STATS SECTION
        // =========================================

        HBox statsBox = new HBox(20);

        statsBox.setAlignment(Pos.CENTER_RIGHT);

        // =========================================
        // CARD 1
        // =========================================

        VBox stat1 = createMiniCard(
                "24",
                "Books Read");

        // =========================================
        // CARD 2
        // =========================================

        VBox stat2 = createMiniCard(
                "8",
                "Wishlist");

        // =========================================
        // CARD 3
        // =========================================

        VBox stat3 = createMiniCard(
                "12",
                "Completed");

        statsBox.getChildren().addAll(

                stat1,

                stat2,

                stat3);

        // =========================================
        // PUSH RIGHT
        // =========================================

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // =========================================
        // ADD HEADER
        // =========================================

        profileHeader.getChildren().addAll(

                leftProfile,

                spacer,

                statsBox);

        // =========================================
        // ADD ALL
        // =========================================

        content.getChildren().addAll(

                title,

                profileHeader,

                usernameField,

                emailField,

                phoneField,

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
    // MINI CARD
    // =========================================

    private static VBox createMiniCard(
            String number,
            String text) {

        VBox card = new VBox(10);

        card.setAlignment(Pos.CENTER);

        card.setPadding(
                new Insets(20));

        card.setPrefWidth(140);

        card.setStyle(

                "-fx-background-color: #1A1A2E;" +

                        "-fx-background-radius: 20;");

        Label numberLabel = new Label(
                number);

        numberLabel.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 30px;" +

                        "-fx-font-weight: bold;");

        Label textLabel = new Label(
                text);

        textLabel.setStyle(

                "-fx-text-fill: #9CA3AF;" +

                        "-fx-font-size: 14px;");

        card.getChildren().addAll(

                numberLabel,

                textLabel);

        return card;
    }

}