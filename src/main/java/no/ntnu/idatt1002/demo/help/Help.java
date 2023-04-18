package no.ntnu.idatt1002.demo.help;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Opens the help option
 */
public class Help extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlUrl = getClass().getResource("/HelpScenes/HelpHome.fxml");
    primaryStage.setTitle("Help");
    Scene scene = FXMLLoader.load(fxmlUrl);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}

