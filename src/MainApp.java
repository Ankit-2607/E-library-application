import javafx.application.Application;
import javafx.stage.Stage;
import pages.LoginPage;
import database.DatabaseConnection;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseConnection.getConnection();

        stage.setTitle(AppConfig.APP_NAME);

        stage.setWidth(AppConfig.WIDTH);
        stage.setHeight(AppConfig.HEIGHT);

        stage.setMinWidth(1400);
        stage.setMinHeight(800);

        // OPEN LOGIN PAGE
        LoginPage.show(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}