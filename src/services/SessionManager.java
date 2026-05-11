package services;

public class SessionManager {

    // =========================================
    // CURRENT USER
    // =========================================

    private static String currentUser;

    private static String currentRole = "USER";
    // =========================================
    // SET USER
    // =========================================

    public static void setCurrentUser(
            String username) {

        currentUser = username;
    }

    // =========================================
    // GET USER
    // =========================================

    public static String getCurrentUser() {

        return currentUser;
    }

    // =========================================
    // SET ROLE
    // =========================================

    public static void setCurrentRole(
            String role) {

        currentRole = role;
    }

    // =========================================
    // GET ROLE
    // =========================================

    public static String getCurrentRole() {

        return currentRole;
    }

    // =========================================
    // IS ADMIN
    // =========================================

    public static boolean isAdmin() {

        return currentRole.equals(
                "ADMIN");
    }

    // =========================================
    // LOGOUT
    // =========================================

    public static void logout() {

        currentUser = null;
        currentRole = "USER";
    }
}