package tim.javafx.challange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 300);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        Data.getInstance().load();
    }

    @Override
    public void stop() throws Exception {
        Data.getInstance().save();

    }

    public static void main(String[] args) {
        launch();
    }
}