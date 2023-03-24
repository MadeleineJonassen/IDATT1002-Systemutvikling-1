package no.ntnu.idatt1002.demo.recurringTransactions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.RecurringCategory;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.Transaction;

//import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecurringTransactionsController {
  @FXML
  private Label monthName;
  @FXML
  private DatePicker datePicker;
  @FXML
  private ListView<String> recurringTransactions;
  private Month month;
  private Register categoryRegister = new Register();

  /**
   * Saves the date chosen from the DatePicker and changes the month name under
   * the pie chart.
   */
  @FXML
  public void dateChosen() {
    LocalDate date = datePicker.getValue();
    month = date.getMonth();
    String monthString = month.toString().toLowerCase();
    String finalString = monthString.substring(0,1).toUpperCase() + monthString.substring(1);
    if (date != null) {
      monthName.setText(finalString + " overview");
    } else {
      monthName.setText("");
    }
  }

  @FXML
  public void listData() {
    List<String> transactionStringList = new ArrayList<>();
    List<Transaction> transactionList = categoryRegister.getTransactionsByCategoryType(new RecurringCategory());
    for (Transaction transaction : transactionList) {
      transactionStringList.add(transaction.toString());
    }
    ObservableList<String> observableList = FXCollections.observableList(transactionStringList);
    recurringTransactions.setItems(observableList);
  }

  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

