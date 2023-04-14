package no.ntnu.idatt1002.demo.expenses;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

/**
 * Controller for the AddExpense.fxml window.
 */

public class EditExpenseController implements Initializable {
  @FXML
  private GridPane gridPane;
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
  private Register categoryRegister;
  private List<Category> categories;

  public EditExpenseController() throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(getClass().getClassLoader().getResource("database/register.json"));
  }

  public void addExpensePressed() {
  }

  /**
   * Method for handling button that is used to go to the home page.
   *
   * @param event The ActionEvent that triggered the method
   * @throws IOException If SpendWiseHomePage.fxml is not found in resources
   */

  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryBox.getItems().addAll("Food", "Clothes", "Rent", "Pets");
  }

  private boolean isNameEmpty() {
    return ((expenseName.getText() == null) || (expenseName.getText().isEmpty()));
  }
  private boolean isAmountEmpty() {
    return ((amount.getText() == null) || (amount).getText().isEmpty());
  }
  private void isCategoryChosen() {

  }
}
