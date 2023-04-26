package no.ntnu.idatt1002.spendwise.expenses;

import java.io.IOException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import no.ntnu.idatt1002.spendwise.data.Category;
import no.ntnu.idatt1002.spendwise.data.Register;
import no.ntnu.idatt1002.spendwise.data.RegisterController;

/**
 * GUI controller for the expenses.
 */
public class ExpensesController implements Initializable {
  @FXML
  private VBox categoryCheckBoxes;
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private PieChart pieChart;
  private Register register;
  private Stage stage;

  private Scene scene;

  /**
   * Trigger for when the the user clicks the home button.
   *
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
   */
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
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
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
   *
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
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
   * Button that takes user to edit expenses.
   *
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
   */
  public void switchToEditExpense(ActionEvent event) throws IOException {
    BorderPane rootSwitchToEditExpense = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/EditExpense.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToEditExpense);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Button that takes user to future budgeting.
   *
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
   */
  public void switchToFutureBudgeting(ActionEvent event) throws IOException {
    BorderPane rootSwitchToFutureBudgeting = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/Budgeting.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToFutureBudgeting);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * A button that opens the help option menu.
   *
   * @param event Mouse click.
   * @throws IOException If the FXML file can not be found.
   */
  public void openHelpOption(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToHelpOption =
        new FXMLLoader(getClass().getResource("/HelpScenes/HelpExpenses.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

  /**
   * Initializes the controller class.
   *
   * @param url The url to the FXML file.
   * @param resourceBundle The resource bundle.
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

    ArrayList<Category> categories = (ArrayList<Category>) register.getCategories();
    for (Category category : categories) {
      // Only add expense categories to the list
      if (category.isExpenseCategory()) {
        CheckBox checkBox = new CheckBox(category.getName());
        checkBox.setSelected(false);
        checkBox.setFont(Font.font("Arial", 20));
        categoryCheckBoxes.getChildren().add(checkBox);
      }
    }
  }

  /**
   * Trigger for the go button.
   *
   * @param actionEvent The ActionEvent that triggered the method
   */
  public void goButtonPushed(ActionEvent actionEvent) {
    // If the user has not selected both dates
    if (fromDate.getValue() == null || toDate.getValue() == null) {
      return;
    }
    // If the user has made a wrong selection
    if (fromDate.getValue().isAfter(toDate.getValue())) {
      return;
    }

    // Get the selected categories
    ArrayList<Category> selectedCategories = new ArrayList<>();
    for (int i = 0; i < categoryCheckBoxes.getChildren().size(); i++) {
      CheckBox checkBox = (CheckBox) categoryCheckBoxes.getChildren().get(i);
      if (checkBox.isSelected()) {
        Category c = register.getCategoryByName(checkBox.getText());
        selectedCategories.add(c);
      }
    }

    ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
    for (Category category : selectedCategories) {
      double sum = category.getTotalAmountWithinTimeFrame(fromDate.getValue(), toDate.getValue());

      if (sum != 0) {
        piechartData.add(new PieChart.Data(category.getName(), sum));
      }

      pieChart.setData(piechartData);
      pieChart.setLegendVisible(true);
    }

  }
}

