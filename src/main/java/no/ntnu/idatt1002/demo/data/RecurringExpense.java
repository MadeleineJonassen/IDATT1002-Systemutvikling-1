package no.ntnu.idatt1002.demo.data;

import java.util.Date;

/**
 * Using expense describes a recurring expense
 *  * Describes a recurring expense using name, notes, date and amount.
 *  * String name - recurring transaction name
 *  * String notes - short notes for the recurring transaction
 *  * Date date - ISO-8601 format of date for recurring transaction
 *  * double amount - amount of the recurring transaction
 */
public class RecurringExpense extends Expense {
  public RecurringExpense(String name, String notes, Date date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO
}
