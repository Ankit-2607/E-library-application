package components;

import javafx.scene.Node;

import javafx.scene.control.ScrollPane;

import services.ThemeManager;

public class ScrollWrapper {

    private ScrollPane pane;

    public ScrollWrapper(
            Node content) {

        pane = new ScrollPane();

        pane.setContent(content);

        // =========================================
        // SETTINGS
        // =========================================

        pane.setFitToWidth(true);

        pane.setFitToHeight(true);

        pane.setPannable(true);

        // =========================================
        // STYLE
        // =========================================

        pane.setStyle(

                "-fx-background: "
                        + ThemeManager.getBackgroundColor()
                        + ";" +

                        "-fx-background-color: "
                        + ThemeManager.getBackgroundColor()
                        + ";" +

                        "-fx-control-inner-background: "
                        + ThemeManager.getBackgroundColor()
                        + ";");
    }

    public ScrollPane getPane() {

        return pane;
    }
}