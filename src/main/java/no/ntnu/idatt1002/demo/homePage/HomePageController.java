package no.ntnu.idatt1002.demo.homePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HomePageController {

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

}
