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
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for future budgeting GUI.
 */
public class FutureBudgetingController implements Initializable {

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

  /**
   * Home button.

   * @param event mouse click on button.
   * @throws IOException if wrong input is detected.
   */
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(
        getClass().getResource("/SpendWiseHomePage.fxml"))));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    categoryInitiator();
  }
}
