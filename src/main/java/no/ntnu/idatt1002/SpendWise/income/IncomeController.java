package no.ntnu.idatt1002.SpendWise.income;

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
import javafx.stage.Stage;
import no.ntnu.idatt1002.SpendWise.data.Category;
import no.ntnu.idatt1002.SpendWise.data.Register;
import no.ntnu.idatt1002.SpendWise.data.RegisterController;

/**
 * GUI controller for income class.
 */
public class IncomeController implements Initializable {
  private Stage stage;
  private Scene scene;
  @FXML
  private VBox categoryCheckBoxes;
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private PieChart pieChart;
  private Register register;
  private Scene rootSwitchToEditCategory;

  public void editCategoryButtonPushed(ActionEvent event) throws IOException {
    FXMLLoader rootSwitchToEditCategory = new FXMLLoader(getClass().getResource("/EditCategory.fxml"));
    Parent rootEditCategory = (Parent) rootSwitchToEditCategory.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootEditCategory));
    stage.show();
  }

  public void editIncomeButtonPushed() {
    System.out.println("The edit income button has been pushed");
  }

  public void changeToIncomeBarGraphButtonPushed() {
    System.out.println("The change to bar graph button has been pushed");
  }


  /**
   * Making comment to make checkstyle go away. TODO: comment for real.

   * @param url unknown
   * @param resourceBundle unknown
   */
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
      // Only add Income categories to the list
      if (!category.isExpenseCategory()) {
        CheckBox checkBox = new CheckBox(category.getName());
        checkBox.setSelected(false);
        categoryCheckBoxes.getChildren().add(checkBox);
      }
    }
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

    }

    pieChart.setData(piechartData);
    pieChart.setLegendVisible(true);
  }


  /**
   * Button to go to home page.

   * @param event mouse click
   * @throws IOException if invalid input is detected.
   */
  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToExpenses(ActionEvent event) throws IOException {
    BorderPane rootSwitchToOutcome = (FXMLLoader.load(Objects.requireNonNull(
            getClass().getResource("/Expenses.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(rootSwitchToOutcome);
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
    FXMLLoader rootSwitchToHelpOption = new FXMLLoader(getClass().getResource("/HelpScenes/HelpIncome.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

}

