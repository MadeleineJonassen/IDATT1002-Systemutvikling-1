package no.ntnu.idatt1002.demo.data;

import java.util.Date;

/**
 *  * Describes a recurring income using name, notes, date and amount.
 *  * String name - recurring income name
 *  * String notes - short notes for the recurring income
 *  * Date date - ISO-8601 format of date for recurring income
 *  * double amount - amount of the recurring income
 */
public class RecurringIncome extends Income {
  public RecurringIncome(String name, String notes, Date date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO
}
