package no.ntnu.idatt1002.demo.homePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;
import no.ntnu.idatt1002.demo.data.Transaction;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

  @FXML
  private TableView<Transaction> transactionTable = new TableView<>();
  private Stage stage;
  private Scene scene;
  private Scene rootSwitchToExpenses;
  private AnchorPane rootSwitchToRecurringTrans;
  private GridPane rootSwitchToAddExpense;
  private GridPane rootSwitchToEditIncome;
  public void switchToExpenses(ActionEvent event) throws IOException {
    rootSwitchToExpenses = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Expenses.fxml"))));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //scene = new Scene(root);
    stage.setScene(rootSwitchToExpenses);
    stage.show();
  }

  public void switchToRecurringTransactions(ActionEvent event) throws IOException {
    rootSwitchToRecurringTrans = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/RecurringTransactions.fxml"))));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToRecurringTrans);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToEditExpenses(ActionEvent event) throws IOException {
    rootSwitchToAddExpense = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddExpense.fxml"))));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToAddExpense);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToEditIncome(ActionEvent event) throws IOException {
    rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/EditIncome.fxml"))));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditIncome);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) throws RuntimeException {
    Register register;

    try {
      register = RegisterController.readData(Objects.requireNonNull(
              getClass().getClassLoader().getResource("database/register.json")));
    } catch (IOException | URISyntaxException | ConformityException | DuplicateNameException e) {
      throw new RuntimeException(e);
    }

    // Make all the columns
    TableColumn<Transaction, String> nameColumn = new TableColumn<Transaction, String>("Name");
    TableColumn<Transaction, String> amountColumn = new TableColumn<Transaction, String>("Amount");
    TableColumn<Transaction, String> categoryColumn = new TableColumn<Transaction, String>("Category");

    // Set the cell value factories based on transactions
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    // For the amount column use the amountToString method
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountString"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

    // Add the columns to the table
    ArrayList<Transaction> transactions = register.getAllTransactions();
    ObservableList<Transaction> data = FXCollections.observableArrayList(transactions);
    transactionTable.setItems(data);
    transactionTable.getColumns().addAll(nameColumn, amountColumn, categoryColumn);
  }
}
