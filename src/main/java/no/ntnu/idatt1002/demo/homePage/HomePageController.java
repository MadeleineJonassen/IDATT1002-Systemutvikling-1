package no.ntnu.idatt1002.demo.homePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {

  private Stage stage;
  private Scene scene;
  private Scene root;

  public void switchToExpenses(ActionEvent event) throws IOException {
    root = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Expenses.fxml"))));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //scene = new Scene(root);
    stage.setScene(root);
    stage.show();
  }

}
