package no.ntnu.idatt1002.demo.recurringTransactions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class RecurringTransactions extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmUrl = getClass().getResource("/RecurringTransactions.fxml");
    primaryStage.setTitle("Recurring Transactions pane");
    Parent root = FXMLLoader.load(fxmUrl);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
