package no.ntnu.idatt1002.SpendWise.budgeting;

import javafx.beans.property.SimpleDoubleProperty;
import no.ntnu.idatt1002.SpendWise.data.Category;

/**
 * Class that defines the cells in the budgeting table.
 */
public class BudgetingCell {
  private Category category;
  private SimpleDoubleProperty expected;
  private SimpleDoubleProperty actual;
  private SimpleDoubleProperty difference;

  /**
   * Constructor for the BudgetingCell class.
   *
   * @param expected Expected amount of money to spend.
   * @param actual   Actual amount of money spent.
   * @param category Category of the cell.
   */
  public BudgetingCell(double expected, double actual, Category category) {
    this.expected = new SimpleDoubleProperty(expected);
    this.actual = new SimpleDoubleProperty(actual);
    this.difference = new SimpleDoubleProperty(expected - actual);
    this.category = category;
  }

  /**
   * Constructor for the BudgetingCell class.
   *
   * @param actual   Actual amount of money spent.
   * @param category Category of the cell.
   */
  public BudgetingCell(double actual, Category category) {
    this.expected = new SimpleDoubleProperty(0);
    this.actual = new SimpleDoubleProperty(actual);
    this.difference = new SimpleDoubleProperty(expected.get() - actual);
    this.category = category;
  }

  /**
   * Returns the expected amount of money to spend.
   *
   * @return Expected amount of money to spend as a double.
   */
  public double getExpected() {
    return expected.get();
  }

  /**
   * Sets the expected amount of money to spend.
   *
   * @param newExpected Expected amount of money to spend as a double.
   */
  public void setExpected(double newExpected) {
    this.difference.set(newExpected - actual.get());
    this.expected.set(newExpected);
  }

  /**
   * Returns the actual amount of money spent.
   *
   * @return Actual amount of money spent as a double.
   */
  public double getActual() {
    return actual.get();
  }

  /**
   * Sets the actual amount of money spent.
   *
   * @param newActual Actual amount of money spent as a double.
   */
  public void setActual(double newActual) {
    this.difference.set(this.expected.get() - newActual);
    this.actual.set(newActual);
  }

  /**
   * Returns the category of the cell.
   *
   * @return Category of the cell as a string.
   */
  public String getCategoryName() {
    return category.getName();
  }

  /**
   * Returns the difference between the expected and actual amount of money spent.
   *
   * @return Difference between the expected and actual amount of money spent as a double.
   */
  public double getDifference() {
    return difference.get();
  }
}
