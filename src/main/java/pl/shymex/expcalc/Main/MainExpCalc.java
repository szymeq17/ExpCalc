package pl.shymex.expcalc.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainExpCalc extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/expcalc.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }
}
