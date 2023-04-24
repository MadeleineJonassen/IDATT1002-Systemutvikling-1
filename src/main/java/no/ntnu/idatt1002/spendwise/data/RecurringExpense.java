package no.ntnu.idatt1002.spendwise.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Describes a recurring expense, inheriting from the Expense class.
 */
public class RecurringExpense extends Expense {
  /**
   * Constructor for a recurring expense.
   *
   * @param name   Name of the expense.
   * @param notes  Notes for the expense.
   * @param date   Date of the expense. (Day of the month)
   * @param amount Amount of the expense.
   */
  public RecurringExpense(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }

  /**
   * Returns whether the expense is within the given time frame.
   *
   * @param fromDate The start date of the time frame.
   * @param toDate   The end date of the time frame.
   * @return True if the expense is within the time frame, false otherwise.
   */
  public boolean isWithinTimeFrame(LocalDate fromDate, LocalDate toDate) {
    /* The format for "date" in a recurring transaction is just a day of the month.
     * So we need to add the month and year to the date
     * to be able to compare it to the time frame.
     */

    LocalDate transactionDate = LocalDate.parse(LocalDate.now().getYear()
        + "-" + fromDate.format(DateTimeFormatter.ofPattern("MM"))
        + "-" + this.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    return (transactionDate.isAfter(fromDate) || transactionDate.equals(fromDate))
        && (transactionDate.equals(toDate) || transactionDate.isBefore(toDate));
  }
}
