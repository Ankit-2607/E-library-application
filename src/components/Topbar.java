package components;

import services.NavigationManager;

import pages.BrowsePage;
import pages.WishlistPage;
import pages.MyLibraryPage;
import pages.ProfilePage;
import pages.DashboardPage;
import pages.LoginPage;
import services.SessionManager;
import services.ThemeManager;
// import pages.BookDetailsPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.stage.Stage;

public class Topbar {

        public static HBox createTopbar(
                        Stage stage) {

                // =========================================
                // TOPBAR
                // =========================================

                HBox topbar = new HBox(18);

                topbar.setAlignment(Pos.CENTER_LEFT);

                topbar.setPadding(
                                new Insets(18, 30, 18, 30));

                topbar.setPrefHeight(85);

                topbar.setStyle(

                                "-fx-background-color: "
                                                + ThemeManager.getCardColor()
                                                + ";" +

                                                "-fx-border-color: #232336;" +

                                                "-fx-border-width: 0 0 1 0;");

                // =========================================
                // SEARCH FIELD
                // =========================================

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Search books...");

                searchField.setPrefWidth(350);

                searchField.setPrefHeight(42);

                searchField.setStyle(

                                "-fx-background-color: "
                                                + ThemeManager.getBackgroundColor()
                                                + ";" +

                                                "-fx-background-radius: 12;" +

                                                "-fx-text-fill: "
                                                + ThemeManager.getTextColor()
                                                + ";" +

                                                "-fx-prompt-text-fill: "
                                                + ThemeManager.getSubTextColor()
                                                + ";" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-padding: 0 14;");

                // =========================================
                // SPACER
                // =========================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =========================================
                // NOTIFICATION BUTTON
                // =========================================

                Button notifyBtn = createIconButton("🔔");

                // =========================================
                // NOTIFICATION MENU
                // =========================================

                ContextMenu notificationMenu = new ContextMenu();

                // =========================================
                // ITEMS
                // =========================================

                MenuItem n1 = new MenuItem(
                                "New books added");

                MenuItem n2 = new MenuItem(
                                "Wishlist updated");

                MenuItem n3 = new MenuItem(
                                "Trending books updated");

                // =========================================
                // ADD ITEMS
                // =========================================

                notificationMenu.getItems().addAll(

                                n1,

                                n2,

                                n3);

                notifyBtn.setOnAction(e -> {

                        notificationMenu.show(

                                        notifyBtn,

                                        javafx.geometry.Side.BOTTOM,

                                        0,

                                        10);
                });

                // =========================================
                // THEME BUTTON
                // =========================================

                Button themeBtn = createIconButton(

                                ThemeManager.isDarkMode()
                                                ? "🌙"
                                                : "☀");

                // =========================================
                // THEME ACTION
                // =========================================

                themeBtn.setOnAction(e -> {

                        ThemeManager.toggleTheme();

                        String currentPage =

                                        NavigationManager
                                                        .getCurrentPage();

                        // =========================================
                        // RELOAD CURRENT PAGE
                        // =========================================

                        switch (currentPage) {

                                case "dashboard":

                                        DashboardPage.show(stage);

                                        break;

                                case "browse":

                                        BrowsePage.show(stage);

                                        break;

                                case "wishlist":

                                        WishlistPage.show(stage);

                                        break;

                                case "library":

                                        MyLibraryPage.show(stage);

                                        break;

                                case "details":

                                        break;
                        }
                });

                // =========================================
                // PROFILE BUTTON
                // =========================================

                Button profileBtn = new Button(

                                "👤 " +

                                                SessionManager.getCurrentUser());

                profileBtn.setStyle(

                                "-fx-background-color: "
                                                + ThemeManager.getBackgroundColor()
                                                + ";" +

                                                "-fx-background-radius: 12;" +

                                                "-fx-text-fill: "
                                                + ThemeManager.getTextColor()
                                                + ";" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-font-weight: bold;" +

                                                "-fx-padding: 10 18;" +

                                                "-fx-cursor: hand;");

                // =========================================
                // PROFILE MENU
                // =========================================

                ContextMenu profileMenu = new ContextMenu();

                // =========================================
                // MENU ITEMS
                // =========================================

                MenuItem profileItem = new MenuItem("My Profile");

                profileItem.setOnAction(e -> {

                        ProfilePage.show(stage);
                });

                MenuItem settingsItem = new MenuItem("Settings");

                MenuItem logoutItem = new MenuItem("Logout");

                // =========================================
                // LOGOUT ACTION
                // =========================================

                logoutItem.setOnAction(e -> {

                        SessionManager.logout();

                        LoginPage.show(stage);
                });

                // =========================================
                // ADD ITEMS
                // =========================================

                profileMenu.getItems().addAll(

                                profileItem,

                                settingsItem,

                                logoutItem);

                // =========================================
                // OPEN MENU
                // =========================================

                profileBtn.setOnAction(e -> {

                        profileMenu.show(

                                        profileBtn,

                                        javafx.geometry.Side.BOTTOM,

                                        0,

                                        10);
                });

                // =========================================
                // ADD ALL
                // =========================================

                topbar.getChildren().addAll(

                                searchField,

                                spacer,

                                notifyBtn,

                                themeBtn,

                                profileBtn);

                return topbar;
        }

        // =========================================
        // ICON BUTTON
        // =========================================

        private static Button createIconButton(
                        String icon) {

                Button btn = new Button(icon);

                btn.setPrefSize(42, 42);

                btn.setStyle(

                                "-fx-background-color: "
                                                + ThemeManager.getBackgroundColor()
                                                + ";" +

                                                "-fx-background-radius: 12;" +

                                                "-fx-text-fill: "
                                                + ThemeManager.getTextColor()
                                                + ";" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-cursor: hand;");

                return btn;
        }
}