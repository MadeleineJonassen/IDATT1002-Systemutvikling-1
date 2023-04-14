package no.ntnu.idatt1002.demo.recurring;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Expense;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;
import no.ntnu.idatt1002.demo.data.Transaction;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

//import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for recurring transactions FXML.
 */
public class RecurringTransactionsController implements Initializable {
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

  public RecurringTransactionsController() throws DuplicateNameException, IOException, URISyntaxException, ConformityException {
    categoryRegister = RegisterController.readData(getClass().getClassLoader().getResource("database/register.json"));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fillTable();
    fillPie();
  }

  private void fillTable() {
    ArrayList<Transaction> transactions = categoryRegister.getTransactionsByCategoryType(true);
    ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList(transactions);
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

  /**
   * Button to go to home page of application.

   * @param event when button is pressed with mouse.
   * @throws IOException if input is invalid.
   */
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
        "/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }
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

