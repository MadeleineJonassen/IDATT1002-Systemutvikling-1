package no.ntnu.idatt1002.SpendWise.expenses;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.SpendWise.data.*;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;

/**
 * Controller for the AddExpense.fxml window.
 */

public class EditExpenseController implements Initializable {
  private Scene scene;
  private Stage stage;
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

  public EditExpenseController() throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(
        Objects.requireNonNull(getClass().getClassLoader().getResource("database/register.json")));
    retrievedCategories = categoryRegister.getCategoriesByTransactionType(true);
    categories = new ArrayList<>();
    for (String category : retrievedCategories) {
      if (categoryRegister.getCategoryByName(category).isRecurring()) {
        category += " (recurring)";
      }
      categories.add(category);
    }
  }

  public void deleteExpensePressed() throws ConformityException {
    Transaction selectedItem = tableView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      categoryRegister.removeTransaction(selectedItem);
      tableView.getItems().remove(selectedItem);
    }

    writeData();
  }

  public void addExpensePressed() throws ConformityException, IOException {
    try {
      Double.parseDouble(amount.getText());
    } catch (NumberFormatException nfe) {
      amount.clear();
      amount.setPromptText("Please enter a number");
      return;
    }
    if (!isAmountEmpty() && !isNameEmpty() && !isCategoryBoxEmpty() && !isDateNotChosen()) {
      String name = expenseName.getText();
      double expenseAmount = Double.parseDouble(amount.getText());
      String categoryFetched = (String) categoryBox.getValue();
      String categoryChosen = categoryFetched.split(" ")[0].strip();
      String expenseNotes = notes.getText();
      String date = datePicker.getValue().toString();
      boolean isRecurring = categoryRegister.getCategoryByName(categoryChosen).isRecurring();

      if (isRecurring) {
        String recurringDate = date.substring(8);
        System.out.println(recurringDate);
        RecurringExpense newExpense = new RecurringExpense(name, expenseNotes, recurringDate,expenseAmount);
        categoryRegister.addTransactionToCategory(newExpense, categoryChosen);
        writeData();
      } else {
        Expense expense = new Expense(name, expenseNotes, date, expenseAmount);
        categoryRegister.addTransactionToCategory(expense, categoryChosen);
        writeData();
      }
      expenseName.clear();
      amount.clear();
      notes.clear();
      categoryBox.valueProperty().set(null);

    } else {
      if (isAmountEmpty()) {
        amount.setPromptText("AMOUNT HERE");
      }
      if (isNameEmpty()) {
        expenseName.setPromptText("EXPENSE NAME HERE");
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

  private void writeData(){
    try {
      FileWriter file = new FileWriter(
          Objects.requireNonNull(this.getClass().getClassLoader()
              .getResource("database/register.json")).getFile());
      file.write(RegisterController.writeData(categoryRegister).toString());
      file.close();
    } catch (IOException e){
      System.out.println("Could not write to database");
      throw new RuntimeException(e);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    categoryBox.getItems().addAll(categories);
    fillTableView();
  }
  public void fillTableView() {
    List<Transaction> transactions = categoryRegister.getTransactionByTransactionType(true);
    ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList(transactions);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
    tableView.setItems(transactionObservableList);
  }

  private boolean isNameEmpty() {
    return ((expenseName.getText() == null) || (expenseName.getText().isEmpty()));
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

  public void editCategoryButtonPushed(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToEditCategory = new FXMLLoader(getClass().getResource("/EditCategory.fxml"));
    Parent rootEditCategory = (Parent) rootSwitchToEditCategory.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootEditCategory));
    stage.show();
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

  /**
   * Button that takes user to income.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToIncome(ActionEvent event) throws IOException {
    BorderPane rootSwitchToIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Income.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToIncome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to recurring transactions.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToRecurringTransactions(ActionEvent event) throws IOException {
    BorderPane rootSwitchToRecurringTrans = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/RecurringTransactions.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToRecurringTrans);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to edit income.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditIncome(ActionEvent event) throws IOException {
    BorderPane rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/EditIncome.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditIncome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to future budgeting.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToFutureBudgeting(ActionEvent event) throws IOException {
    BorderPane rootSwitchToFutureBudgeting = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/FutureBudgeting.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToFutureBudgeting);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * A button that opens the help option menu.
   * @param event - mouse click
   * @throws IOException - if wrong input detected
   */
  public void openHelpEdit(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption = new FXMLLoader(getClass().getResource("/HelpScenes/HelpEdit.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }


}
