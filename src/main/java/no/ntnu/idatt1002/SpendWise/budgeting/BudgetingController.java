package no.ntnu.idatt1002.demo.budgeting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Double.parseDouble;

public class BudgetingController implements Initializable {
  @FXML
  private TextField expectedAmountExpenseField;
  @FXML
  private TextField expectedAmountIncomeField;
  @FXML
  private TextField totalActual;
  private double totalActualDoub;
  @FXML
  private TextField totalExpected;
  private double totalExpectedDoub;
  @FXML
  private TextField totalDifference;
  private double totalDifferenceDoub;
  @FXML
  private DatePicker datePicker;
  @FXML
  private TableView<BudgetingCell> tableViewIncomeCat;
  @FXML
  private TableColumn<BudgetingCell, String> categoryColumnIn;
  @FXML
  private TableColumn<BudgetingCell, Double> actualAmountColumnIn;
  @FXML
  private TableView<BudgetingCell> tableViewIncomeDoub;
  @FXML
  private TableColumn<BudgetingCell, Double> expectedAmountColumnIn;
  @FXML
  private TableColumn<BudgetingCell, Double> differenceColumnIn;
  @FXML
  private TableView<BudgetingCell> tableViewExpenseCat;
  @FXML
  private TableColumn<BudgetingCell, String> categoryColumnEx;
  @FXML
  private TableColumn<BudgetingCell, Double> actualAmountColumnEx;
  @FXML
  private TableView<BudgetingCell> tableViewExpenseDoub;
  @FXML
  private TableColumn<BudgetingCell, Double> expectedAmountColumnEx;
  @FXML
  private TableColumn<BudgetingCell, Double> differenceColumnEx;
  @FXML
  private BarChart<String, Number> budgetingChart;
  private LocalDate dateChosen;
  private Register categoryRegister;

  public BudgetingController() throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(getClass().getClassLoader().getResource("database/register.json"));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  private void fillTableIncome() {
    List<String> incomeString = categoryRegister.getCategoriesByTransactionType(false);
    ArrayList<Category> incomeCategories = new ArrayList<>();
    ArrayList<BudgetingCell> incomeDouble = new ArrayList<>();
    for (String string : incomeString) {
      Category category = categoryRegister.getCategoryByName(string);
      incomeCategories.add(category);
      BudgetingCell budgetingCell = new BudgetingCell(category.getTotalAmount(), category);
      incomeDouble.add(budgetingCell);
    }
    ObservableList<BudgetingCell> transactionObservableList = FXCollections.observableArrayList(incomeDouble);
    categoryColumnIn.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
    actualAmountColumnIn.setCellValueFactory(new PropertyValueFactory<>("Actual"));

    ObservableList<BudgetingCell> transactionObservableList2 = FXCollections.observableArrayList(incomeDouble);
    expectedAmountColumnIn.setCellValueFactory(new PropertyValueFactory<>("Expected"));
    differenceColumnIn.setCellValueFactory(new PropertyValueFactory<>("Difference"));

    tableViewIncomeCat.setItems(transactionObservableList);
    tableViewIncomeDoub.setItems(transactionObservableList2);
  }

  private void fillTableExpense() {
    List<String> expenseString = categoryRegister.getCategoriesByTransactionType(true);
    ArrayList<BudgetingCell> expenseDouble = new ArrayList<>();
    ArrayList<Category> expenseCategories = new ArrayList<>();
    for (String string : expenseString) {
      Category category = categoryRegister.getCategoryByName(string);
      expenseCategories.add(category);
      BudgetingCell budgetingCell = new BudgetingCell(category.getTotalAmount(), category);
      expenseDouble.add(budgetingCell);
    }
    ObservableList<BudgetingCell> transactionObservableList = FXCollections.observableArrayList(expenseDouble);
    categoryColumnEx.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
    actualAmountColumnEx.setCellValueFactory(new PropertyValueFactory<>("Actual"));

    ObservableList<BudgetingCell> transactionsObservableList2 = FXCollections.observableArrayList(expenseDouble);
    expectedAmountColumnEx.setCellValueFactory(new PropertyValueFactory<>("Expected"));
    differenceColumnEx.setCellValueFactory(new PropertyValueFactory<>("Difference"));

    tableViewExpenseCat.setItems(transactionObservableList);
    tableViewExpenseDoub.setItems(transactionsObservableList2);
  }

  public void dateChosen() {
    dateChosen = datePicker.getValue();
    fillTableExpense();
    fillTableIncome();
    updateTotal();
    tableViewExpenseDoub.refresh();
    tableViewIncomeDoub.refresh();
    tableViewExpenseCat.refresh();
    tableViewIncomeCat.refresh();

  }

  public void addExpenseAmountPressed() {
    BudgetingCell selectedItem = tableViewExpenseDoub.getSelectionModel().getSelectedItem();
    try {
      Double.parseDouble(expectedAmountExpenseField.getText());
    } catch (Exception e) {
      expectedAmountExpenseField.clear();
      expectedAmountExpenseField.setPromptText("Please enter a number.");
      return;
    }
    if (expectedAmountExpenseField.getText().isEmpty()) {
      expectedAmountExpenseField.setPromptText("Please enter amount.");
      return;
    }
    if (selectedItem == null) {
      System.out.println("Not selected expense");
      expectedAmountExpenseField.clear();
      expectedAmountExpenseField.setPromptText("Please choose a row to update.");
      return;
    }
    double expected = Double.parseDouble(expectedAmountExpenseField.getText());
    selectedItem.setExpected(expected);
    expectedAmountExpenseField.clear();
    System.out.println("Added: " + expected);
    tableViewExpenseDoub.refresh();
  }
  public void addIncomeAmountPressed() {
    BudgetingCell selectedItem = tableViewIncomeDoub.getSelectionModel().getSelectedItem();
    try {
      Double.parseDouble(expectedAmountIncomeField.getText());
    } catch (Exception e) {
      expectedAmountIncomeField.clear();
      expectedAmountIncomeField.setPromptText("Please enter a number.");
      return;
    }
    if (expectedAmountIncomeField.getText().isEmpty()) {
      expectedAmountIncomeField.setPromptText("Please enter amount.");
      return;
    }
    if (selectedItem == null) {
      System.out.println("Not selected income");
      expectedAmountIncomeField.clear();
      expectedAmountIncomeField.setPromptText("Please choose a row to update.");
      return;
    }
    double expected = Double.parseDouble(expectedAmountIncomeField.getText());
    selectedItem.setExpected(expected);
    expectedAmountIncomeField.clear();
    System.out.println("Added: " + expected);
    tableViewIncomeDoub.refresh();
  }
  private void updateTotal() {
    for (BudgetingCell item : tableViewIncomeCat.getItems()) {
      totalActualDoub += item.getActual();
      totalDifferenceDoub += item.getDifference();
      totalExpectedDoub += item.getExpected();
    }
    for (BudgetingCell item : tableViewExpenseCat.getItems()) {
      totalActualDoub -= item.getActual();
      totalDifferenceDoub += item.getDifference();
      totalExpectedDoub -= item.getExpected();
    }
    totalActual.setText(Double.toString(totalActualDoub));
    totalDifference.setText(Double.toString(totalDifferenceDoub));
    totalExpected.setText(Double.toString(totalExpectedDoub));
  }

  /**
   * Home button.

   * @param event mouse click on button.
   * @throws IOException if wrong input is detected.
   */
  @FXML
  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }
}
