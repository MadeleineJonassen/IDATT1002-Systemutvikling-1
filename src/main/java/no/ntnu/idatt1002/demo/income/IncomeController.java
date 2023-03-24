package no.ntnu.idatt1002.demo.income;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI controller for income class.
 */
public class IncomeController implements Initializable {
  @FXML
  private CheckBox salaryChecked;
  @FXML
  private CheckBox investmentChecked;
  @FXML
  private CheckBox loanChecked;
  @FXML
  private CheckBox otherIncomeChecked;
  @FXML
  private PieChart pieChart;

  public void editCategoryButtonPushed() {
    System.out.println("The category button has been pushed");
  }

  public void editIncomeButtonPushed() {
    System.out.println("The edit income button has been pushed");
  }

  public void viewExpensesButtonPushed() {
    System.out.println("The view expenses button has been pushed");
  }

  public void changeToIncomeBarGraphButtonPushed() {
    System.out.println("The change to bar graph button has been pushed");
  }

  @FXML
  private void salaryChecked() {
    if (!salaryChecked.isSelected()) {
      System.out.println("salary is de-checked");
    } else if (salaryChecked.isSelected()) {
      System.out.println("salary selected");
    }
  }

  @FXML
  private void investmentChecked() {
    if (!investmentChecked.isSelected()) {
      System.out.println("investment is de-checked");
    } else if (investmentChecked.isSelected()) {
      System.out.println("investment selected");
    }
  }

  @FXML
  private void loanChecked() {
    if (!loanChecked.isSelected()) {
      System.out.println("loan is de-checked");
    } else if (loanChecked.isSelected()) {
      System.out.println("loan expenses selected");
    }
  }

  @FXML
  private void otherIncomeChecked() {
    if (!otherIncomeChecked.isSelected()) {
      System.out.println("other is de-checked");
    } else if (otherIncomeChecked.isSelected()) {
      System.out.println("other income selected");
    }
  }

  /**
   * Button to go to home page.

   * @param event mouse click
   * @throws IOException if invalid input is detected.
   */
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Making comment to make checkstyle go away. TODO: comment for real.

   * @param url unknown
   * @param resourceBundle unknown
   */
  public void initialize(URL url, ResourceBundle resourceBundle) {

    ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
        new PieChart.Data("Food", 13),
        new PieChart.Data("Housing", 25),
        new PieChart.Data("Fixed Expenses", 10),
        new PieChart.Data("Travel", 22),
        new PieChart.Data("Other", 30)
    );
    pieChart.setData(piechartData);
  }

  /**
   * Button to go to expenses.

   * @param event mouse click on button.
   * @throws IOException if wrong input is detected.
   */
  public void goToExpenses(ActionEvent event) throws IOException {
    Scene goExpenses = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/Expenses.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(goExpenses);
    stage.show();
  }

}

