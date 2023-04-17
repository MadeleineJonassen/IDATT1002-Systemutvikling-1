package no.ntnu.idatt1002.demo.expenses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * GUI controller for the expenses.
 */
public class OutcomeController {
  @FXML
  private VBox categoryCheckBoxes;
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private Button changeToIncome;
  @FXML
  private PieChart pieChart;
  @FXML
  private GridPane gridPane;
  private Register register;
  private Stage stage;

  private Scene scene;

  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("/SpendWiseHomePage.fxml"))));
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
    Scene rootSwitchToIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Income.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(rootSwitchToIncome);
    stage.show();
  }

  /**
   * Button that takes user to recurring transactions.
   *
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
   * Button that takes user to edit income.
   *
   * @param event mouse click.
   * @throws IOException if wrong input detected.
   */
  public void switchToEditIncome(ActionEvent event) throws IOException {
    GridPane rootSwitchToEditIncome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/EditIncome.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditIncome);
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
   * A button that opens the help option menu.
   *
   * @param event - mouse click
   * @throws IOException - if wrong input detected
   */
  public void openHelpOption(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption = new FXMLLoader(getClass().getResource("/HelpScenes/HelpOption.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

  ///////


  /**
   * Initializes the controller class.
   *
   * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
   * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
   */
  public void initialize(URL url, ResourceBundle resourceBundle) {

    /*
    ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
            new PieChart.Data("Food", 13),
            new PieChart.Data("Housing", 25),
            new PieChart.Data("Fixed Expenses", 10),
            new PieChart.Data("Travel", 22),
            new PieChart.Data("Other", 30)
    );
    pieChart.setData(piechartData);*/

    try {
      register = RegisterController.readData(Objects.requireNonNull(
              getClass().getClassLoader().getResource("database/register.json")));
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    ArrayList<Category> categories = register.getCategories();
    for (Category category : categories) {
      // Only add expense categories to the list
      if (category.isExpenseCategory()) {
        CheckBox checkBox = new CheckBox(category.getName());
        checkBox.setSelected(false);
        categoryCheckBoxes.getChildren().add(checkBox);
      }
    }
  }


  @FXML
  private String timeInterval() { //TODO: optimize...
    String result = null;
    if (fromDate.isManaged() || toDate.isManaged()) {
      LocalDate startDate = fromDate.getValue();
      LocalDate stopDate = toDate.getValue();
      if (toDate == null) {
        toDate = fromDate;
        result = ("(" + startDate + ")");
      } else if (stopDate.isAfter(startDate)) {
        result = ("(" + startDate + "->" + stopDate + ")");
      } else if (startDate.isAfter(stopDate)) {
        startDate = toDate.getValue();
        stopDate = fromDate.getValue();
        result = ("(" + startDate + "->" + stopDate + ")");
      }
    }
    return result;
  }

  /**
   * Trigger for the go button
   *
   * @param actionEvent The ActionEvent that triggered the method
   */
  public void goButtonPushed(ActionEvent actionEvent) {
    // If the user has not selected both dates
    if (fromDate.getValue() == null || toDate.getValue() == null) {
      return;
    }
    // If the user has made a wrong selection
    // TODO: maybe make more user friendly (error message)
    if (fromDate.getValue().isAfter(toDate.getValue())) {
      return;
    }

  }
}
