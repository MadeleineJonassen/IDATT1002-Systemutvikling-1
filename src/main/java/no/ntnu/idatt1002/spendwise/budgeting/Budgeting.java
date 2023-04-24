package no.ntnu.idatt1002.spendwise.budgeting;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates the stage for future budgeting.
 */
public class Budgeting extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {

    Parent root = FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/Budgeting.fxml")));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
