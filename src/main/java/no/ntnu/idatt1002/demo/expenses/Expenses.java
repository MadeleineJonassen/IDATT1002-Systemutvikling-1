package no.ntnu.idatt1002.demo.expenses;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Loader for the Expenses.fxml window.
 */
public class Expenses extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlUrl = getClass().getResource("/Expenses.fxml");
    primaryStage.setTitle("Expenses");
    Scene scene = FXMLLoader.load(fxmlUrl);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }


}

