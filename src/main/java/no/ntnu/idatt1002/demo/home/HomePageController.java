package no.ntnu.idatt1002.demo.home;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * GUI controller for the home page.
 */
public class HomePageController {

  private Stage stage;
  private Scene scene;
  private Scene rootSwitchToExpenses;
  private Scene rootSwitchToIncome;
  private AnchorPane rootSwitchToRecurringTrans;
  private GridPane rootSwitchToAddExpense;
  private GridPane rootSwitchToEditIncome;
  private AnchorPane rootSwitchToFutureBudgeting;

  /**
   * Button that takes user to expenses.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToExpenses(ActionEvent event) throws IOException {
    rootSwitchToExpenses = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/Expenses.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(rootSwitchToExpenses);
    stage.show();
  }

  /**
   * Button that takes user to income.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToIncome(ActionEvent event) throws IOException {
    rootSwitchToIncome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/Income.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(rootSwitchToIncome);
    stage.show();
  }

  /**
   * Button that takes user to recurring transactions.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToRecurringTransactions(ActionEvent event) throws IOException {
    rootSwitchToRecurringTrans = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/RecurringTransactions.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToRecurringTrans);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to edit expenses.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditExpenses(ActionEvent event) throws IOException {
    rootSwitchToAddExpense = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/EditExpense.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToAddExpense);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to edit income.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditIncome(ActionEvent event) throws IOException {
    rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(
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
    rootSwitchToFutureBudgeting = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/FutureBudgeting.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToFutureBudgeting);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToHelpOption(ActionEvent event) throws IOException {
    rootSwitchToHelpOption = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/HelpOption.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToHelpOption);
    stage.setScene(scene);
    stage.show();
  }

}
