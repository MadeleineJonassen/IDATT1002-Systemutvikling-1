package no.ntnu.idatt1002.demo.data;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Dropdown extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  FutureBudgeting test = new FutureBudgeting(100, 95);

  @Override
  public void start(Stage primaryStage) {
    test.initiateUnused();
    primaryStage.setTitle("Dropdown to select category");
    ChoiceBox cbx = new ChoiceBox(FXCollections.observableArrayList(test.unusedCategories));
    //cbx.getItems().addAll("Housing", "Other...");

    StackPane root = new StackPane();
    root.getChildren().add(cbx);

    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
  }
}
