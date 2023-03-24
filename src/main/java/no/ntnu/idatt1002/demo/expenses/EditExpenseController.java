package no.ntnu.idatt1002.demo.expenses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditExpenseController implements Initializable {
  @FXML
  private DatePicker datePicker;
  @FXML
  private TextField expenseName;
  @FXML
  private TextField amount;
  @FXML
  private ComboBox categoryBox;
  @FXML
  private TextField notes;
  @FXML
  private Button addExpense;
  public void addExpensePressed() {
  }
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryBox.getItems().addAll("Food", "Clothes", "Rent", "Pets");
  }
}
