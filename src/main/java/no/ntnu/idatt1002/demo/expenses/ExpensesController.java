package no.ntnu.idatt1002.demo.expenses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class ExpensesController implements Initializable{
  @FXML
  private CheckBox groceriesChecked;
  @FXML
  private CheckBox housingChecked;
  @FXML
  private CheckBox fixedExpensesChecked;
  @FXML
  private CheckBox travelChecked;
  @FXML
  private CheckBox entertainmentChecked;
  @FXML
  private CheckBox otherChecked;
  @FXML
  private PieChart pieChart;
  @FXML
  private GridPane gridPane;
  public void editCategoryButtonPushed(){
    System.out.println("The category button has been pushed");
  }

  public void editExpensesButtonPushed(){
    System.out.println("The edit expenses button has been pushed");
  }

  public void viewIncomeButtonPushed(){
    System.out.println("The view income button has been pushed");
  }

  public void changeToBarGraphButtonPushed(){
    System.out.println("The change to bar graph button has been pushed");
  }
  @FXML
  private void groceriesChecked(){
    if(!groceriesChecked.isSelected()){
      System.out.println("food is de-checked");
    }else if (groceriesChecked.isSelected()){
      System.out.println("food selected");
    }
  }

  @FXML
  private void housingChecked(){
    if(!housingChecked.isSelected()){
      System.out.println("housing is de-checked");
    }else if (housingChecked.isSelected()){
      System.out.println("housing selected");
    }
  }
  @FXML
  private void fixedExpensesChecked(){
    if(!fixedExpensesChecked.isSelected()){
      System.out.println("fixed expenses is de-checked");
    }else if (fixedExpensesChecked.isSelected()){
      System.out.println("fixed expenses selected");
    }
  }

  @FXML
  private void travelChecked(){
    if(!travelChecked.isSelected()){
      System.out.println("fixed expenses is de-checked");
    }else if (travelChecked.isSelected()){
      System.out.println("fixed expenses selected");
    }
  }
  @FXML
  private void entertainmentChecked(){
    if(!entertainmentChecked.isSelected()){
      System.out.println("entertainment is de-checked");
    }else if (entertainmentChecked.isSelected()){
      System.out.println("entertainment selected");
    }
  }

  @FXML
  private void otherChecked(){
    if(!otherChecked.isSelected()){
      System.out.println("fixed expenses is de-checked");
    }else if (otherChecked.isSelected()){
      System.out.println("fixed expenses selected");
    }
  }
  public void goHome(ActionEvent event) throws IOException {
    VBox rootGoHome = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SpendWiseHomePage.fxml"))));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootGoHome);
    stage.setScene(scene);
    stage.show();
  }


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


}

