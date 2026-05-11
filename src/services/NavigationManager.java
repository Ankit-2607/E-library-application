package services;

public class NavigationManager {

    // =========================================
    // CURRENT PAGE
    // =========================================

    private static String currentPage = "dashboard";

    // =========================================
    // SET PAGE
    // =========================================

    public static void setCurrentPage(
            String page) {

        currentPage = page;
    }

    // =========================================
    // GET PAGE
    // =========================================

    public static String getCurrentPage() {

        return currentPage;
    }
}