package no.ntnu.idatt1002.demo.budgeting;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for future budgeting GUI.
 */
public class FutureBudgetingController implements Initializable {
  private Stage stage;
  private Scene scene;
  @FXML
  public TextField firstExpected;

  @FXML
  public TextField secondExpected;

  @FXML
  public TextField thirdExpected;

  @FXML
  public TextField fourthExpected;

  @FXML
  public TextField fifthExpected;

  @FXML
  public TextField sixthExpected;

  @FXML
  public TextField firstActual;

  @FXML
  public TextField secondActual;

  @FXML
  public TextField thirdActual;

  @FXML
  public TextField fourthActual;

  @FXML
  public TextField fifthActual;

  @FXML
  public TextField sixthActual;

  @FXML
  private Text firstDiff;

  @FXML
  private Text secondDiff;

  @FXML
  private Text thirdDiff;

  @FXML
  private Text fourthDiff;

  @FXML
  private Text fifthDiff;

  @FXML
  private Text sixthDiff;

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setFirstDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(firstExpected.getText())
            - Integer.parseInt(firstActual.getText())));
    firstDiff.setText(difference);
  }

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setSecondDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(secondExpected.getText())
            - Integer.parseInt(secondActual.getText())));
    secondDiff.setText(difference);
  }

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setThirdDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(thirdExpected.getText())
            - Integer.parseInt(thirdActual.getText())));
    thirdDiff.setText(difference);
  }

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setFourthDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(fourthExpected.getText())
            - Integer.parseInt(fourthActual.getText())));
    fourthDiff.setText(difference);
  }

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setFifthDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(fifthExpected.getText())
            - Integer.parseInt(fifthActual.getText())));
    fifthDiff.setText(difference);
  }

  /**
   * Calculates difference between given expected output and actual output.
   */
  public void setSixthDiff() {
    String difference;
    difference = String.valueOf((Integer.parseInt(sixthExpected.getText())
            - Integer.parseInt(sixthActual.getText())));
    sixthDiff.setText(difference);
  }

  @FXML
  private ChoiceBox<Object> firstCategory;

  @FXML
  private ChoiceBox<Object> secondCategory;

  @FXML
  private ChoiceBox<Object> thirdCategory;

  @FXML
  private ChoiceBox<Object> fourthCategory;

  @FXML
  private ChoiceBox<Object> fifthCategory;

  @FXML
  private ChoiceBox<Object> sixthCategory;

  /**
   * Initializes the categories used in future budgeting dropdowns.
   */
  public void categoryInitiator() {
    if (firstCategory.isVisible()) {
      List<ChoiceBox<Object>> dropdowns = new ArrayList<>();
      dropdowns.add(firstCategory);
      dropdowns.add(secondCategory);
      dropdowns.add(thirdCategory);
      dropdowns.add(fourthCategory);
      dropdowns.add(fifthCategory);
      dropdowns.add(sixthCategory);

      List<String> categories = new ArrayList<>();
      categories.add("Housing");
      categories.add("Groceries");
      categories.add("Travel");
      categories.add("Entertainment");
      categories.add("Fixed expenses");
      categories.add("Other...");

      for (ChoiceBox<Object> cb : dropdowns) { //TODO add this to button that leads to page.
        cb.setItems(FXCollections.observableArrayList(categories));
      }
    }
  }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
      categoryInitiator();
    }

  /**
   * Button to go to home page of application.

   * @param event when button is pressed with mouse.
   * @throws IOException if input is invalid.
   */
  public void goHome(ActionEvent event) throws IOException {
    BorderPane rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
            "/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
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
            getClass().getResource("/NewIncome.fxml"))));

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
    FXMLLoader rootSwitchToHelpOption = new FXMLLoader(getClass().getResource("/HelpScenes/HelpBudgeting.fxml"));
    Parent rootHelp = (Parent) rootSwitchToHelpOption.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(rootHelp));
    stage.show();
  }

 }



