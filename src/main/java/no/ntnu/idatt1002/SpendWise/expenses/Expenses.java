package no.ntnu.idatt1002.SpendWise.expenses;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class for the expenses page.
 */
public class Expenses extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlUrl = getClass().getResource("/Expenses.fxml");
    primaryStage.setTitle("Expenses");
    Scene scene = FXMLLoader.load(fxmlUrl);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
