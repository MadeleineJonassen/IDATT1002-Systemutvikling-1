package no.ntnu.idatt1002.demo.homeButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Button extends Application {
  private BorderPane root;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    root = new BorderPane();

    Image homeImage = new Image("file:../../src/main/resources/home.png");
    ImageView home = new ImageView(homeImage);

    home.setFitHeight(40);
    home.setFitWidth(40);
    home.setPreserveRatio(true);

    primaryStage.setTitle("Trying to make home-button");

    javafx.scene.control.Button button = new javafx.scene.control.Button();
    button.setAlignment(Pos.TOP_LEFT);
    button.setPrefSize(40,40);
    button.setGraphic(home);

    root.getChildren().add(button);

    primaryStage.setScene(new Scene(root, 350,250));
    primaryStage.show();

  }
}
