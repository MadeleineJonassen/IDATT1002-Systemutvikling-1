package no.ntnu.idatt1002.SpendWise.home;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatt1002.SpendWise.data.Category;
import no.ntnu.idatt1002.SpendWise.data.Register;
import no.ntnu.idatt1002.SpendWise.data.RegisterController;

/**
 * GUI controller for the home page.
 */
public class HomePageController implements Initializable {

  private Stage stage;
  private Scene scene;
  @FXML
  private PieChart pieChart;
  private Register register;


  /**
   * Button that takes user to expenses.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToExpenses(ActionEvent event) throws IOException {
    BorderPane rootSwitchToOutcome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Expenses.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToOutcome);
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
   * Button that takes user to future budgeting.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToBudgeting(ActionEvent event) throws IOException {
    BorderPane rootSwitchToBudgeting = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Budgeting.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToBudgeting);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to edit expenses.

   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditExpenses(ActionEvent event) throws IOException {
    BorderPane rootSwitchToAddExpense = (FXMLLoader.load(Objects.requireNonNull(
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
    BorderPane rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/EditIncome.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditIncome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * A button that opens the help option menu.
   * @param event - mouse click
   * @throws IOException - if wrong input detected
   */
  public void openHelpOption(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption = new FXMLLoader(getClass().getResource("/HelpScenes/HelpHome.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

  /**
   * Initializes the home page. Mainly used to set up the pie chart for expenses vs. income.
   * @param url The URL to the FXML file.
   * @param resourceBundle The resources used in the FXML file.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      register = RegisterController.readData(Objects.requireNonNull(
          getClass().getClassLoader().getResource("database/register.json")));
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    double totalIncome = 0;
    double totalExpenses = 0;
    LocalDate today = LocalDate.now();
    pieChart.setTitle("Overview for " + today.getMonth());
    // Checks the total income and expenses for the current fiscal month.
    for (Category c : register.getCategories()){
      if (c.isIncomeCategory()) {
        totalIncome += c.getTotalAmountWithinTimeFrame(today.withDayOfMonth(1),
            today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
      } else {
        totalExpenses += totalIncome += c.getTotalAmountWithinTimeFrame(today.withDayOfMonth(1),
            today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
      }
    }

    pieChart.getData().add(new PieChart.Data("Income", totalIncome));
    pieChart.getData().add(new PieChart.Data("Expenses", totalExpenses));
  }
}
