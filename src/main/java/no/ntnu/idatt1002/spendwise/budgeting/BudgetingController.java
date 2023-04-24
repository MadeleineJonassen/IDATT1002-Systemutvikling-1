package no.ntnu.idatt1002.spendwise.budgeting;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.spendwise.data.Category;
import no.ntnu.idatt1002.spendwise.data.Register;
import no.ntnu.idatt1002.spendwise.data.RegisterController;
import no.ntnu.idatt1002.spendwise.exceptions.ConformityException;
import no.ntnu.idatt1002.spendwise.exceptions.DuplicateNameException;

/**
 * Class that controls the budgeting window.
 */
public class BudgetingController {
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
  private Stage stage;
  private Scene scene;

  /**
   * Constructor for the budgeting controller.
   *
   * @throws DuplicateNameException If the name of the category is already in the register.
   * @throws IOException            If the file is not found.
   * @throws URISyntaxException     If the URI is not correct.
   * @throws ConformityException    If the data is not correct.
   */
  public BudgetingController() throws DuplicateNameException, IOException,
      URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(
        Objects.requireNonNull(getClass().getClassLoader()
            .getResource("database/register.json")));
  }

  /**
   * Method that fills the tables with income data.
   */
  private void fillTableIncome() {
    List<String> incomeString = categoryRegister.getCategoriesByTransactionType(false);
    List<LocalDate> dates = getDates();
    ArrayList<BudgetingCell> incomeDouble = new ArrayList<>();
    for (String string : incomeString) {
      Category category = categoryRegister.getCategoryByName(string);
      BudgetingCell budgetingCell =
          new BudgetingCell(category.getTotalAmountWithinTimeFrame(
              dates.get(0), dates.get(1)), category);
      incomeDouble.add(budgetingCell);
    }
    ObservableList<BudgetingCell> transactionObservableList =
        FXCollections.observableArrayList(incomeDouble);
    categoryColumnIn.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
    actualAmountColumnIn.setCellValueFactory(new PropertyValueFactory<>("Actual"));
    tableViewIncomeCat.setItems(transactionObservableList);

    ObservableList<BudgetingCell> transactionObservableList2 =
        FXCollections.observableArrayList(incomeDouble);
    expectedAmountColumnIn.setCellValueFactory(new PropertyValueFactory<>("Expected"));
    differenceColumnIn.setCellValueFactory(new PropertyValueFactory<>("Difference"));
    tableViewIncomeDoub.setItems(transactionObservableList2);
  }

  /**
   * Method that fills the tables with expense data.
   */
  private void fillTableExpense() {
    List<String> expenseString = categoryRegister.getCategoriesByTransactionType(true);
    List<LocalDate> dates = getDates();
    ArrayList<BudgetingCell> expenseDouble = new ArrayList<>();
    for (String string : expenseString) {
      Category category = categoryRegister.getCategoryByName(string);
      BudgetingCell budgetingCell =
          new BudgetingCell(category.getTotalAmountWithinTimeFrame(
              dates.get(0), dates.get(1)), category);
      expenseDouble.add(budgetingCell);
    }

    // TODO test if final impacts performance

    final ObservableList<BudgetingCell> transactionObservableList =
        FXCollections.observableArrayList(expenseDouble);
    categoryColumnEx.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
    actualAmountColumnEx.setCellValueFactory(new PropertyValueFactory<>("Actual"));

    final ObservableList<BudgetingCell> transactionsObservableList2 =
        FXCollections.observableArrayList(expenseDouble);
    expectedAmountColumnEx.setCellValueFactory(new PropertyValueFactory<>("Expected"));
    differenceColumnEx.setCellValueFactory(new PropertyValueFactory<>("Difference"));

    tableViewExpenseCat.setItems(transactionObservableList);
    tableViewExpenseDoub.setItems(transactionsObservableList2);
  }

  /**
   * Triggers when the date is chosen.
   */
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

  /**
   * Triggers when the add expense amount button is pressed.
   */
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

  /**
   * Triggers when the add income amount button is pressed.
   */
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

  /**
   * Fills the charts displaying data.
   */
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
   * Button that takes user to income.
   *
   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToIncome(javafx.event.ActionEvent event) throws IOException {
    BorderPane rootSwitchToIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Income.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToIncome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to recurring transactions.
   *
   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToRecurringTransactions(javafx.event.ActionEvent event) throws IOException {
    BorderPane rootSwitchToRecurringTrans = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/RecurringTransactions.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToRecurringTrans);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to edit income.
   *
   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditIncome(javafx.event.ActionEvent event) throws IOException {
    BorderPane rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/EditIncome.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditIncome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * A button that opens the help option menu.
   *
   * @param event - mouse click
   * @throws IOException - if wrong input detected
   */
  public void goHome(javafx.event.ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption =
          new FXMLLoader(getClass().getResource("/HelpScenes/HelpBudgeting.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

  public void openHelpOption(javafx.event.ActionEvent actionEvent) throws IOException {
    FXMLLoader rootSwitchToHelpOption =
            new FXMLLoader(getClass().getResource("/HelpScenes/HelpBudgeting.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }


  // TODO write triggers for button switches in Budgeting.fxml
}
