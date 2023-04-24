package no.ntnu.idatt1002.SpendWise.budgeting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import no.ntnu.idatt1002.SpendWise.data.Category;
import no.ntnu.idatt1002.SpendWise.data.Register;
import no.ntnu.idatt1002.SpendWise.data.RegisterController;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
  private double expectedIncome;
  private double expectedExpense;
  private double actualIncome;
  private double actualExpense;
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
  private PieChart expectedPie;
  @FXML
  private PieChart actualPie;
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
    List<LocalDate> dates = getDates();
    ArrayList<BudgetingCell> incomeDouble = new ArrayList<>();
    for (String string : incomeString) {
      Category category = categoryRegister.getCategoryByName(string);
      BudgetingCell budgetingCell = new BudgetingCell(category.getTotalAmountWithinTimeFrame(dates.get(0), dates.get(1)), category);
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
    List<LocalDate> dates = getDates();
    ArrayList<BudgetingCell> expenseDouble = new ArrayList<>();
    for (String string : expenseString) {
      Category category = categoryRegister.getCategoryByName(string);
      BudgetingCell budgetingCell = new BudgetingCell(category.getTotalAmountWithinTimeFrame(dates.get(0), dates.get(1)), category);
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
    fillCharts();
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
    updateTotal();
    fillCharts();
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
      expectedAmountIncomeField.clear();
      expectedAmountIncomeField.setPromptText("Please choose a row to update.");
      return;
    }
    double expected = Double.parseDouble(expectedAmountIncomeField.getText());
    selectedItem.setExpected(expected);
    expectedAmountIncomeField.clear();
    tableViewIncomeDoub.refresh();
    updateTotal();
    fillCharts();
  }

  public void fillCharts() {
    ObservableList<PieChart.Data> piechartExpectedData = FXCollections.observableArrayList();
    piechartExpectedData.add(new PieChart.Data("Expected expenses", expectedExpense));
    piechartExpectedData.add(new PieChart.Data("Expected income", expectedIncome));
    expectedPie.setData(piechartExpectedData);

    ObservableList<PieChart.Data> piechartActualData = FXCollections.observableArrayList();
    piechartActualData.add(new PieChart.Data("Actual expenses", actualExpense));
    piechartActualData.add(new PieChart.Data("Actual income", actualIncome));
    actualPie.setData(piechartActualData);
  }

  private void updateTotal() {
    for (BudgetingCell item : tableViewIncomeCat.getItems()) {
      actualIncome += item.getActual();
      expectedIncome += item.getExpected();
      totalActualDoub += item.getActual();
      totalDifferenceDoub += item.getDifference();
      totalExpectedDoub += item.getExpected();
    }
    for (BudgetingCell item : tableViewExpenseCat.getItems()) {
      actualExpense += item.getActual();
      expectedExpense += item.getExpected();
      totalActualDoub -= item.getActual();
      totalDifferenceDoub += item.getDifference();
      totalExpectedDoub -= item.getExpected();
    }
    totalActual.setText(Double.toString(totalActualDoub));
    totalDifference.setText(Double.toString(totalDifferenceDoub));
    totalExpected.setText(Double.toString(totalExpectedDoub));
  }

  private List<LocalDate> getDates() {
    Calendar c = Calendar.getInstance();
    ArrayList<LocalDate> dates = new ArrayList<>();
    Month month = dateChosen.getMonth();
    int year = dateChosen.getYear();
    int res = c.getActualMaximum(Calendar.DAY_OF_MONTH);

    LocalDate startDate = LocalDate.of(year, month.getValue(), 1);
    dates.add(startDate);
    LocalDate endDate = LocalDate.of(year, month.getValue(), res);
    dates.add(endDate);
    return dates;
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
