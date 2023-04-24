package no.ntnu.idatt1002.SpendWise.help;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Opens the help option.
 */
public class Help extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlUrl = getClass().getResource("/HelpScenes/HelpHome.fxml");
    primaryStage.setTitle("Help");
    Scene scene = FXMLLoader.load(fxmlUrl);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}

