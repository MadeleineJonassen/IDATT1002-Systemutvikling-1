package no.ntnu.idatt1002.demo.income;

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
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.data.Category;
import no.ntnu.idatt1002.demo.data.Register;
import no.ntnu.idatt1002.demo.data.RegisterController;

/**
 * GUI controller for income class.
 */
public class IncomeController implements Initializable {

  @FXML
  private VBox categoryCheckBoxes;
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private PieChart pieChart;
  @FXML
  private Button editCategory;
  private Register register;
  private Scene rootSwitchToEditCategory;
  private Stage stage;

  public void editCategoryButtonPushed(ActionEvent event) throws IOException {
    rootSwitchToEditCategory = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/EditCategory.fxml"))));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(rootSwitchToEditCategory);
    stage.show();
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

    ArrayList<Category> categories = register.getCategories();
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

