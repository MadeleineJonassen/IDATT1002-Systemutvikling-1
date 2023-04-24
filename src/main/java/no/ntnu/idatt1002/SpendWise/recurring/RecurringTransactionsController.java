package no.ntnu.idatt1002.SpendWise.recurring;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.SpendWise.data.Expense;
import no.ntnu.idatt1002.SpendWise.data.Register;
import no.ntnu.idatt1002.SpendWise.data.RegisterController;
import no.ntnu.idatt1002.SpendWise.data.Transaction;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;

/**
 * Controller for recurring transactions FXML.
 */
public class RecurringTransactionsController implements Initializable {
  private Stage stage;
  private Scene scene;
  @FXML
  private Label monthName;
  @FXML
  private DatePicker datePicker;
  @FXML
  private TableView<Transaction> recurringTransactions;
  @FXML
  private TableColumn<Transaction, String> name;
  @FXML
  private TableColumn<Transaction, Double> amount;
  @FXML
  private TableColumn<Transaction, String> date;
  @FXML
  private PieChart pieChart;
  private Register categoryRegister;

  public RecurringTransactionsController()
      throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(
        getClass().getClassLoader().getResource("database/register.json"));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fillTable();
    fillPie();
  }

  private void fillTable() {
    ArrayList<Transaction> transactions = categoryRegister.getTransactionsByCategoryType(true);
    ObservableList<Transaction> transactionObservableList =
        FXCollections.observableArrayList(transactions);
    name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    recurringTransactions.setItems(transactionObservableList);
  }

  private void fillPie() {
    ArrayList<Transaction> transactions = categoryRegister.getTransactionsByCategoryType(true);
    double expenseAmount = 0;
    double incomeAmount = 0;
    for (Transaction transaction : transactions) {
      if (transaction instanceof Expense) {
        expenseAmount += transaction.getAmount();
      } else {
        incomeAmount += transaction.getAmount();
      }
    }
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Expenses", expenseAmount),
        new PieChart.Data("Income", incomeAmount)
    );
    pieChart.setData(pieChartData);
  }
/*
@FXML
  public void listData() {
    List<String> transactionStringList = new ArrayList<>();
    transactionStringList.add("Cats");
    transactionStringList.add("Bottle");
    ObservableList<String> observableList = FXCollections.observableList(transactionStringList);
    recurringTransactions.setItems(observableList);
  }
*/

  /**
   * Button to go to home page of application.
   *
   * @param event when button is pressed with mouse.
   * @throws IOException if input is invalid.
   */
  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
        "/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to income.
   *
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
   * Button that takes user to future budgeting.
   *
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
   * Button that takes user to edit income.
   *
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
   * A button that opens the help option menu.
   *
   * @param event - mouse click
   * @throws IOException - if wrong input detected
   */
  public void openHelpOption(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption =
        new FXMLLoader(getClass().getResource("/HelpScenes/HelpRecTrans.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

}

