package no.ntnu.idatt1002.spendwise.home;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * GUI loader for home page.
 */
public class HomePage extends Application {

  Stage window;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {

    window = stage;
    window.setOnCloseRequest(e -> {
      e.consume();
      closeProgram();
    });

    Parent root = FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml")));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private void closeProgram() {
    Boolean answer = ConfirmBox.display("Title", "Are you sure you want to exit?");
    if (answer) {
      window.close();
    }
  }
}