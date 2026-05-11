package services;

public class ThemeManager {

    // =========================================
    // THEME STATE
    // =========================================

    private static boolean darkMode = true;

    // =========================================
    // CHECK MODE
    // =========================================

    public static boolean isDarkMode() {

        return darkMode;
    }

    // =========================================
    // TOGGLE THEME
    // =========================================

    public static void toggleTheme() {

        darkMode = !darkMode;
    }

    // =========================================
    // BACKGROUND COLOR
    // =========================================

    public static String getBackgroundColor() {

        if (darkMode) {

            return "#0F0E17";
        }

        return "#F3F4F6";
    }

    // =========================================
    // CARD COLOR
    // =========================================

    public static String getCardColor() {

        if (darkMode) {

            return "#1A1A2E";
        }

        return "#FFFFFF";
    }

    // =========================================
    // TEXT COLOR
    // =========================================

    public static String getTextColor() {

        if (darkMode) {

            return "white";
        }

        return "#111827";
    }

    // =========================================
    // SUBTEXT COLOR
    // =========================================

    public static String getSubTextColor() {

        if (darkMode) {

            return "#9CA3AF";
        }

        return "#7C3AED";
    }
}