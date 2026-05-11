package components;

import pages.BrowsePage;
import pages.WishlistPage;
import pages.MyLibraryPage;
import javafx.geometry.Insets;
import services.SessionManager;
import pages.AdminDashboardPage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import pages.DashboardPage;

public class Sidebar {

        public static VBox createSidebar(
                        Stage stage,
                        String activePage) {

                // =========================================
                // SIDEBAR CONTAINER
                // =========================================

                VBox sidebar = new VBox(18);

                sidebar.setPadding(
                                new Insets(25));

                sidebar.setPrefWidth(240);

                sidebar.setStyle(
                                "-fx-background-color: #161625;");

                // =========================================
                // LOGO
                // =========================================

                Label logo = new Label(
                                "📚 BiblioX");

                logo.setStyle(
                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: bold;");

                // =========================================
                // MENU BUTTONS
                // =========================================

                Button dashboardBtn = createButton(
                                "Dashboard");

                Button browseBtn = createButton(
                                "Browse Books");

                browseBtn.setOnAction(e -> {

                        BrowsePage.show(stage);
                });

                Button wishlistBtn = createButton(
                                "Wishlist");

                wishlistBtn.setOnAction(e -> {

                        WishlistPage.show(stage);
                });

                Button libraryBtn = createButton(
                                "My Library");

                // =========================================
                // ADMIN BUTTON
                // =========================================

                Button adminBtn = createButton(
                                "🛠 Admin Panel");

                adminBtn.setOnAction(e -> {

                        AdminDashboardPage.show(stage);
                });

                libraryBtn.setOnAction(e -> {

                        MyLibraryPage.show(stage);
                });

                // =========================================
                // ACTIVE BUTTON
                // =========================================

                if (activePage.equals(
                                "dashboard")) {

                        dashboardBtn.setStyle(

                                        "-fx-background-color: #7C3AED;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-padding: 14 18;");
                }

                // =========================================
                // ACTIVE BROWSE BUTTON
                // =========================================

                if (activePage.equals(
                                "browse")) {

                        browseBtn.setStyle(

                                        "-fx-background-color: #7C3AED;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-padding: 14 18;");
                }

                if (activePage.equals(
                                "wishlist")) {

                        wishlistBtn.setStyle(

                                        "-fx-background-color: #7C3AED;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-padding: 14 18;");
                }


                if (activePage.equals(
                                "library")) {

                        libraryBtn.setStyle(

                                        "-fx-background-color: #7C3AED;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-padding: 14 18;");
                }

                // =========================================
                // ACTIVE ADMIN BUTTON
                // =========================================

                if (activePage.equals(
                                "admin")) {

                        adminBtn.setStyle(

                                        "-fx-background-color: #7C3AED;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-padding: 14 18;");
                }

                // =========================================
                // BUTTON ACTIONS
                // =========================================

                dashboardBtn.setOnAction(e -> {

                        DashboardPage.show(stage);
                });

                // =========================================
                // PUSH LOGOUT DOWN
                // =========================================

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                // =========================================
                // LOGOUT BUTTON
                // =========================================

                Button logoutBtn = createButton(
                                "🚪 Logout");

                // =========================================
                // ADD ALL
                // =========================================

                sidebar.getChildren().addAll(

                                logo,

                                dashboardBtn,
                                browseBtn,
                                wishlistBtn,
                                libraryBtn);

                // =========================================
                // ADMIN ONLY
                // =========================================

                if (SessionManager.isAdmin()) {

                        sidebar.getChildren().add(
                                        adminBtn);
                }

                sidebar.getChildren().addAll(

                                spacer,

                                logoutBtn);

                return sidebar;
        }

        // =========================================
        // BUTTON UI
        // =========================================

        private static Button createButton(
                        String text) {

                Button btn = new Button(text);

                btn.setMaxWidth(Double.MAX_VALUE);

                btn.setStyle(

                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D1D5DB;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 14 18;" +
                                                "-fx-cursor: hand;");

                return btn;
        }
}