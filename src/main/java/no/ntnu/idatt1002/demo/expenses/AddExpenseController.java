package no.ntnu.idatt1002.demo.expenses;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller for the AddExpense.fxml window.
 */
public class AddExpenseController {
  @FXML
  private DatePicker datePicker;
  @FXML
  private TextField expenseName;
  @FXML
  private TextField amount;
  @FXML
  private ComboBox category;
  @FXML
  private TextField notes;

  /**
   * Method for handling button that is used to go to the home page.
   *
   * @param event The ActionEvent that triggered the method
   * @throws IOException If SpendWiseHomePage.fxml is not found in resources
   */
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }
}
