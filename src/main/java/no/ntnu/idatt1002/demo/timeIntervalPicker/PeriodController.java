package no.ntnu.idatt1002.demo.timeIntervalPicker;
import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;


public interface PeriodController {

  /**
   * @return Today.
   */
  LocalDate currentDate();

  /**
   * @return Selected from date.
   */
  SimpleObjectProperty<LocalDate> fromDateProperty();

  /**
   * @return Selected to date.
   */
  SimpleObjectProperty<LocalDate> toDateProperty();
}