package no.ntnu.idatt1002.SpendWise.home;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class that creates a window for the user to confirm an action.
 */
public class ConfirmBox {

  static boolean answer;

  /**
   * Creates a window with a title and a message.
   *
   * @param title The title of the window.
   * @param message The message to be displayed in the window.
   * @return True if the user clicks yes, false if the user clicks no.
   */
  public static boolean display(String title, String message) {
    Stage window = new Stage();
    window.initModality(
        Modality.APPLICATION_MODAL);  //makes the user take care of the window in front of them
    window.setTitle(title);
    window.setMinWidth(250);
    Label label = new Label();
    label.setText(message);

    //Create two buttons
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    yesButton.setOnAction(e -> {
      answer = true;
      window.close();
    });
    noButton.setOnAction(e -> {
      answer = false;
      window.close();
    });

    VBox layout = new VBox(15);
    layout.getChildren().addAll(label, yesButton, noButton);
    layout.setAlignment((Pos.CENTER));
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

    return answer;
  }

}