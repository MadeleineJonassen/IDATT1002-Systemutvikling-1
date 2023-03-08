package no.ntnu.idatt1002.demo.data;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FutureBudgetingGUI extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  FutureBudgeting test = new FutureBudgeting(100, 95);

  @Override
  public void start(Stage primaryStage) {
    test.initiateUnused();
    primaryStage.setTitle("Dropdown to select category");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10,10,10,10));
    grid.setHgap(5);
    grid.setVgap(5);

    Text category = new Text("Category:");
    Text expectedOut = new Text("Expected out:");
    Text actualOut = new Text("Actual out:");
    Text difference = new Text("Difference:");
    GridPane.setConstraints(category, 0, 0);
    grid.getChildren().addAll(category);
    GridPane.setConstraints(expectedOut, 1, 0);
    grid.getChildren().addAll(expectedOut);
    GridPane.setConstraints(actualOut, 2, 0);
    grid.getChildren().addAll(actualOut);
    GridPane.setConstraints(difference, 3, 0);
    grid.getChildren().addAll(difference);

    for(int n = 0; n<(test.unusedCategories.size()-1); n++) {

      ChoiceBox cbx = new ChoiceBox(FXCollections.observableArrayList(test.unusedCategories));
      cbx.setValue("Choose category...");
      GridPane.setConstraints(cbx, 0, n + 1);
      grid.getChildren().add(cbx);

      TextField inputFirstCategoryExpected = new TextField();
      inputFirstCategoryExpected.setPromptText("Enter expected output");
      GridPane.setConstraints(inputFirstCategoryExpected, 1, n + 1);
      grid.getChildren().addAll(inputFirstCategoryExpected);

      TextField inputFirstCategoryActual = new TextField("0");
      inputFirstCategoryActual.setPromptText("Enter actual output");
      GridPane.setConstraints(inputFirstCategoryActual, 2, n + 1);
      grid.getChildren().addAll(inputFirstCategoryActual);

      Label result = new Label("ii");

      EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionevent) {
          result.setText(String.valueOf(Integer.valueOf(expectedOut.getText()) - Integer.valueOf(actualOut.getText())));
        }
      };
      GridPane.setConstraints(result, 3, n + 1);
      grid.getChildren().addAll(result);
      inputFirstCategoryActual.setOnAction(event);
    }
    primaryStage.setScene(new Scene(grid, 500, 400));
    primaryStage.show();
  }
}
