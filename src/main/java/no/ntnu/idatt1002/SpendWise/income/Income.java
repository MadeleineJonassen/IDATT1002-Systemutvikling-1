package no.ntnu.idatt1002.SpendWise.income;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML loader for income GUI.
 */
public class Income extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlUrl = getClass().getResource("/Income.fxml");
    primaryStage.setTitle("Income");
    Scene scene = FXMLLoader.load(fxmlUrl);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}

