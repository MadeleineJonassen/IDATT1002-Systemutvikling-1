package no.ntnu.idatt1002.spendwise.data;


/**
 * Describes an expense using the Transaction class.
 */
public class Expense extends Transaction {
  public Expense(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }

  /**
   * Returns the amount of the expense as a string with a minus sign in front of it,
   * and currency notation.
   *
   * @return The amount of the expense as a string representation.
   */
  protected String amountToString() {
    return "-" + Double.toString(this.getAmount()) + " NKr";
  }
}
