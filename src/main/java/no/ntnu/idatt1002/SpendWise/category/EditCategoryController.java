package no.ntnu.idatt1002.SpendWise.category;

import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.SpendWise.data.Category;
import no.ntnu.idatt1002.SpendWise.data.Register;
import no.ntnu.idatt1002.SpendWise.data.RegisterController;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;
import no.ntnu.idatt1002.SpendWise.home.ConfirmBox;


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
   *
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
      FileWriter file = new FileWriter(
          Objects.requireNonNull(this.getClass().getClassLoader()
              .getResource("database/register.json")).getFile());
      file.write(RegisterController.writeData(register).toString());
      file.close();
    } catch (IOException e) {
      System.out.println("Could not write to database");
      throw new RuntimeException(e);
    }
    categoryList.getItems().clear();
    List<Category> categories = register.getCategories();
    categories.forEach(category -> categoryList.getItems().add(category.getName()));
  }

  /**
   * Trigger when the add category button is pressed.
   *
   * @param actionEvent Mouse click
   * @throws DuplicateNameException If the category name already exists
   * @throws ConformityException    If the category is not an expense or income
   */
  public void addCategoryPressed(ActionEvent actionEvent) throws
      DuplicateNameException, ConformityException {
    if (!categoryName.getText().isEmpty()
        && !(recurringBox.getValue() == null)
        && !(expenseBox.getValue() == null)) {

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

  /**
   * Trigger when the delete category button is pressed.
   *
   * @param actionEvent Mouse click.
   */
  public void deleteCategoryPressed(ActionEvent actionEvent) {
    String selectedItem = categoryList.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      boolean answer = true;

      // Ask the user if they want to delete the category if it has transactions
      if (register.getCategoryByName(selectedItem).getNumberOfTransactions() != 0) {
        answer = ConfirmBox.display("Title",
            "Are you sure you want to delete the category " + selectedItem + " and its "
                + register.getCategoryByName(selectedItem).getNumberOfTransactions()
                + " transaction(s)?");
      }

      if (answer) {
        try {
          register.removeCategoryByString(selectedItem);
        } catch (Exception e) {
          e.printStackTrace();
        }
        updateList();
      }
    }
  }
}
