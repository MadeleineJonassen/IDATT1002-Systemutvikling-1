package no.ntnu.idatt1002.demo.timeIntervalPicker;

import static java.lang.System.out;

import java.time.LocalDate;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatt1002.demo.timeIntervalPicker.PeriodController;
import no.ntnu.idatt1002.demo.timeIntervalPicker.PeriodPickerPrototype;

public class PeriodMain extends Application {

  private Stage stage;

  public static void main(String[] args) {
    Locale.setDefault(new Locale("no", "NO"));
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    stage.setTitle("Period prototype ");
    initUI();
    stage.getScene().getStylesheets().add(getClass().getResource("/period-picker.css").toExternalForm());
    stage.show();
  }

  private void initUI() {
    VBox vbox = new VBox(20);
    vbox.setStyle("-fx-padding: 10;");
    Scene scene = new Scene(vbox, 400, 200);


    stage.setScene(scene);
    final PeriodPickerPrototype periodPickerPrototype = new PeriodPickerPrototype(new PeriodController() {

      SimpleObjectProperty<LocalDate> fromDate = new SimpleObjectProperty<>();
      SimpleObjectProperty<LocalDate> toDate = new SimpleObjectProperty<>();

      {
        final ChangeListener<LocalDate> dateListener = (observable, oldValue, newValue) -> {
          if (fromDate.getValue() != null && toDate.getValue() != null) {
            out.println("Selected period " + fromDate.getValue() + " - " + toDate.getValue());
          }
        };
        fromDate.addListener(dateListener);
        toDate.addListener(dateListener);

      }


      @Override public LocalDate currentDate() {
        return LocalDate.now();
      }

      @Override public SimpleObjectProperty<LocalDate> fromDateProperty() {
        return fromDate;
      }

      @Override public SimpleObjectProperty<LocalDate> toDateProperty() {
        return toDate;
      }


    });

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    Label checkInlabel = new Label("Check-In Date:");
    GridPane.setHalignment(checkInlabel, HPos.LEFT);
    gridPane.add(periodPickerPrototype, 0, 1);
    vbox.getChildren().add(gridPane);
  }
}


