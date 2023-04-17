package no.ntnu.idatt1002.demo.income;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.*;
import no.ntnu.idatt1002.demo.data.Income;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

/**
 * GUI controller for edit income page.
 */
public class EditIncomeController implements Initializable {
  @FXML
  private GridPane gridPane;
  @FXML
  private DatePicker datePicker;
  @FXML
  private TextField incomeName;
  @FXML
  private TextField amount;
  @FXML
  private ComboBox categoryBox;
  @FXML
  private TextField notes;
  @FXML
  private Button addIncome;
  @FXML
  private Button deleteIncome;
  @FXML
  private TableView<Transaction> tableView;
  @FXML
  private TableColumn<Transaction, String> nameColumn;
  @FXML
  private TableColumn<Transaction, Double> amountColumn;
  @FXML
  private TableColumn<Transaction, String> categoryColumn;

  private Register categoryRegister;
  private List<String> categories;
  private List<String> retrievedCategories;

  public EditIncomeController() throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(getClass().getClassLoader().getResource("database/register.json"));
    retrievedCategories = categoryRegister.getCategoriesByTransactionType(false);
    categories = new ArrayList<>();
    for (String category : retrievedCategories) {
      if (categoryRegister.getCategoryByName(category).isRecurring()) {
        category += " (recurring)";
      }
      categories.add(category);
    }
  }

  public void deleteIncomePressed() throws ConformityException {
    Transaction selectedItem = tableView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      categoryRegister.removeTransaction(selectedItem);
      tableView.getItems().remove(selectedItem);
    }
  }
  public void addIncomePressed() throws IOException, ConformityException {
    if (!isAmountEmpty() && !isNameEmpty() && !isCategoryBoxEmpty() && !isDateNotChosen()) {
      String name = incomeName.getText();
      double incomeAmount = Double.parseDouble(amount.getText());
      String categoryFetched = (String) categoryBox.getValue();
      String categoryChosen = categoryFetched.split(" ")[0].strip();
      String incomeNotes = notes.getText();
      String date = datePicker.getValue().toString();
      boolean isRecurring = categoryRegister.getCategoryByName(categoryChosen).isRecurring();

      if (isRecurring) {
        String recurringDate = date.substring(8);
        System.out.println(recurringDate);
        RecurringIncome newIncome = new RecurringIncome(name, incomeNotes, recurringDate,incomeAmount);
        categoryRegister.addTransactionToCategory(newIncome, categoryChosen);
        System.out.println(RegisterController.writeData(categoryRegister));
      } else {
        Income income = new Income(name, incomeNotes, date, incomeAmount);
        categoryRegister.addTransactionToCategory(income, categoryChosen);
        System.out.println(RegisterController.writeData(categoryRegister));
      }
      incomeName.clear();
      amount.clear();
      notes.clear();
      categoryBox.valueProperty().set(null);

    } else {
      if (isAmountEmpty()) {
        amount.setPromptText("AMOUNT HERE");
      }
      if (isNameEmpty()) {
        incomeName.setPromptText("INCOME NAME HERE");
      }
      if (isCategoryBoxEmpty()) {
        categoryBox.setPromptText("CHOOSE CATEGORY");
      }
      if (isDateNotChosen()) {
        datePicker.setPromptText("CHOSE DATE");
      }
    }
    fillTableView();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryBox.getItems().addAll(categories);
    fillTableView();
  }

  public void fillTableView() {
    List<Transaction> transactions = categoryRegister.getTransactionByTransactionType(false);
    ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList(transactions);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
    tableView.setItems(transactionObservableList);
  }

  /**
   * Home button.

   * @param event mouse click.
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


  private boolean isNameEmpty() {
    return ((incomeName.getText() == null) || (incomeName.getText().isEmpty()));
  }
  private boolean isAmountEmpty() {
    return ((amount.getText() == null) || (amount).getText().isEmpty());
  }
  private boolean isCategoryBoxEmpty() {
    return categoryBox.getSelectionModel().isEmpty();
  }
  private boolean isDateNotChosen() {
    return (datePicker.getValue() == null);
  }
}
