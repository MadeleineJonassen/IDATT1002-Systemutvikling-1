package no.ntnu.idatt1002.SpendWise.data;


/**
 * Describes an Income using the Transaction class.
 */
public class Income extends Transaction {
  public Income(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }

  /**
   * Returns the amount of the expense as a string with currency notation.
   *
   * @return The amount of the expense as a string representation.
   */
  protected String amountToString(){
    return Double.toString(this.getAmount()) + " NKr";
  }
}
