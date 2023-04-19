package no.ntnu.idatt1002.demo.category;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;
import no.ntnu.idatt1002.demo.home.ConfirmBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the EditCategory.fxml window.
 */
public class EditCategoryController implements Initializable {
  @FXML
  private ListView<String> categoryList;
  @FXML
  private TextField categoryName;
  @FXML
  private ComboBox<String> recurringBox;
  @FXML
  private ComboBox<String> expenseBox;
  @FXML
  private Button addCategory;
  private Register register;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    recurringBox.getItems().addAll("Yes", "No");
    expenseBox.getItems().addAll("Expense", "Income");

    try {
      register = RegisterController.readData(Objects.requireNonNull(
          getClass().getClassLoader().getResource("database/register.json")));
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    updateList();
  }

  /**
   * Button to go to home page.

   * @param event mouse click
   * @throws IOException if invalid input is detected.
   */
  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  private void updateList() {
    // Also updates the database
    try {
      // TODO make into actual file write
      System.out.println(RegisterController.writeData(register));
    } catch (IOException e){
      System.out.println("Could not write to database. Replace with popup window?");
      throw new RuntimeException(e);
    }
    categoryList.getItems().clear();
    List<Category> categories = register.getCategories();
    categories.forEach(category -> categoryList.getItems().add(category.getName()));
    // TODO clear each field
  }

  public void addCategoryPressed(ActionEvent actionEvent) throws DuplicateNameException, ConformityException {
    if (!categoryName.getText().isEmpty() &&
        !(recurringBox.getValue() == null) &&
        !(expenseBox.getValue() == null)) {

      boolean recurring = recurringBox.getValue().equals("Yes");
      boolean expense = expenseBox.getValue().equals("Expense");

      Category category = new Category(categoryName.getText(), expense, recurring);
      register.addCategory(category);

      categoryName.clear();
      recurringBox.setValue(null);
      expenseBox.setValue(null);
      updateList();
    }
  }

  public void deleteCategoryPressed(ActionEvent actionEvent){
    String selectedItem = categoryList.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      Boolean answer = true;

      // Ask the user if they want to delete the category if it has transactions
      if (register.getCategoryByName(selectedItem).getNumberOfTransactions() != 0){
        answer = ConfirmBox.display("Title",
            "Are you sure you want to delete the category " + selectedItem + " and its " +
                register.getCategoryByName(selectedItem).getNumberOfTransactions() + " transaction(s)?");
      }

      if (answer){
        register.removeCategoryByString(selectedItem);
        updateList();
      }
    }
  }
}
