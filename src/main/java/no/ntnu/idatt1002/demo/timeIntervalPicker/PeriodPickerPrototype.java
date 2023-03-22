package no.ntnu.idatt1002.demo.timeIntervalPicker;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * Selecting a single date or a period - only a prototype.
 * As long as you have made an active choice on the {@code toDate}, the {@code fromDate} and {@code toDate} will have the same date.
 */
public class PeriodPickerPrototype extends GridPane {

  private static final String CSS_CALENDAR_BEFORE = "calendar-before";
  private static final String CSS_CALENDAR_BETWEEN = "calendar-between";
  private static final String CSS_CALENDAR_TODAY = "calendar-today";
  private static final boolean DISPLAY_WEEK_NUMBER = true;

  private Label fromLabel;
  private Label toLabel;

  private DatePicker fromDate;
  private DatePicker toDate;
  private StringConverter<LocalDate> converter;
  private PeriodController controller;
  private ChangeListener<LocalDate> fromDateListener;
  private ChangeListener<LocalDate> toDateListener;
  private Callback<DatePicker, DateCell> toDateCellFactory;
  private Callback<DatePicker, DateCell> fromDateCellFactory;
  private Tooltip todayTooltip;
  private boolean toDateIsActivlyChosenbyUser;

  public PeriodPickerPrototype(final PeriodController periodController)

  {
    this.controller = periodController;
    createComponents();
    makeLayout();
    createHandlers();
    bindAndRegisterHandlers();
    i18n();
    initComponent();
  }

  public void createComponents() {
    fromLabel = new Label();
    toLabel = new Label();
    fromDate = new DatePicker();
    toDate = new DatePicker();
    todayTooltip = new Tooltip();
  }

  public void createHandlers() {
    fromDate.setOnAction(event -> {
      if ((!toDateIsActivlyChosenbyUser) || fromDate.getValue().isAfter(toDate.getValue())) {
        setDateWithoutFiringEvent(fromDate.getValue(), toDate);
        toDateIsActivlyChosenbyUser = false;
      }
    });

    toDate.setOnAction(event -> toDateIsActivlyChosenbyUser = true);

    fromDateCellFactory = new Callback<DatePicker, DateCell>() {
      @Override public DateCell call(final DatePicker datePicker) {
        return new DateCell() {
          @Override
          public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            getStyleClass().removeAll(CSS_CALENDAR_TODAY, CSS_CALENDAR_BEFORE, CSS_CALENDAR_BETWEEN);

            if ((item.isBefore(toDate.getValue()) || item.isEqual(toDate.getValue())) && item.isAfter(fromDate.getValue())) {
              getStyleClass().add(CSS_CALENDAR_BETWEEN);
            }

            if (item.isEqual(controller.currentDate())) {
              getStyleClass().add(CSS_CALENDAR_TODAY);
              setTooltip(todayTooltip);
            } else {
              setTooltip(null);
            }
          }
        };
      }
    };

    toDateCellFactory =
        new Callback<DatePicker, DateCell>() {
          @Override
          public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
              @Override
              public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(fromDate.getValue()));
                getStyleClass().removeAll(CSS_CALENDAR_TODAY, CSS_CALENDAR_BEFORE, CSS_CALENDAR_BETWEEN);


                if (item.isBefore(fromDate.getValue())) {
                  getStyleClass().add(CSS_CALENDAR_BEFORE);
                } else if (item.isBefore(toDate.getValue()) || item.isEqual(toDate.getValue())) {
                  getStyleClass().add(CSS_CALENDAR_BETWEEN);
                }
                if (item.isEqual(controller.currentDate())) {
                  getStyleClass().add(CSS_CALENDAR_TODAY);
                  setTooltip(todayTooltip);
                } else {
                  setTooltip(null);
                }
              }
            };
          }
        };
    converter = new DateConverter();
    fromDateListener = (observableValue, oldValue, newValue) -> {
      if (newValue == null) {
        // Restting old value and cancel..
        setDateWithoutFiringEvent(oldValue, fromDate);
        return;
      }
      controller.fromDateProperty().set(newValue);
    };
    toDateListener = (observableValue, oldValue, newValue) -> {
      if (newValue == null) {
        // Restting old value and cancel..
        setDateWithoutFiringEvent(oldValue, toDate);
        return;
      }
      controller.toDateProperty().set(newValue);
    };

  }

  /**
   * Changes the date on {@code datePicker} without fire {@code onAction} event.
   */
  private void setDateWithoutFiringEvent(LocalDate newDate, DatePicker datePicker) {
    final EventHandler<ActionEvent> onAction = datePicker.getOnAction();
    datePicker.setOnAction(null);
    datePicker.setValue(newDate);
    datePicker.setOnAction(onAction);
  }

  public void bindAndRegisterHandlers() {
    toDate.setDayCellFactory(toDateCellFactory);
    fromDate.setDayCellFactory(fromDateCellFactory);
    fromDate.valueProperty().addListener(fromDateListener);
    fromDate.setConverter(converter);
    toDate.valueProperty().addListener(toDateListener);
    toDate.setConverter(converter);

  }

  public void makeLayout() {
    setHgap(6);
    add(fromLabel, 0, 0);
    add(fromDate, 1, 0);
    add(toLabel, 2, 0);
    add(toDate, 3, 0);

    fromDate.setPrefWidth(120);
    toDate.setPrefWidth(120);
    fromLabel.setId("calendar-label");
    toLabel.setId("calendar-label");
  }

  public void i18n() {
    // i18n code replaced with
    fromDate.setPromptText("dd.mm.yyyy");
    toDate.setPromptText("dd.mm.yyyy");
    fromLabel.setText("From");
    toLabel.setText("To");
    todayTooltip.setText("Today");
  }

  public void initComponent() {
    fromDate.setTooltip(null);   // Ønsker ikke tooltip
    setDateWithoutFiringEvent(controller.currentDate(), fromDate);
    fromDate.setShowWeekNumbers(DISPLAY_WEEK_NUMBER);

    toDate.setTooltip(null);   // Ønsker ikke tooltip
    setDateWithoutFiringEvent(controller.currentDate(), toDate);
    toDate.setShowWeekNumbers(DISPLAY_WEEK_NUMBER);
  }


}