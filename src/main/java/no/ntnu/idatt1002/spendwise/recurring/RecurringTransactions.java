package no.ntnu.idatt1002.spendwise.recurring;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates the GUI stage for recurring transactions.
 */
public class RecurringTransactions extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmUrl = getClass().getResource("/RecurringTransactions.fxml");
    primaryStage.setTitle("Recurring Transactions pane");
    Parent root = FXMLLoader.load(fxmUrl);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
