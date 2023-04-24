package no.ntnu.idatt1002.SpendWise.budgeting;

import javafx.beans.property.SimpleDoubleProperty;
import no.ntnu.idatt1002.SpendWise.data.Category;

public class BudgetingCell {
  private Category category;
  private SimpleDoubleProperty expected;
  private SimpleDoubleProperty actual;
  private SimpleDoubleProperty difference;

  public BudgetingCell(double expected, double actual, Category category) {
    this.expected = new SimpleDoubleProperty(expected);
    this.actual = new SimpleDoubleProperty(actual);
    this.difference = new SimpleDoubleProperty(expected-actual);
    this.category = category;
  }

  public BudgetingCell(double actual, Category category) {
    this.expected = new SimpleDoubleProperty(0);
    this.actual = new SimpleDoubleProperty(actual);
    this.difference = new SimpleDoubleProperty(expected.get()-actual);
    this.category = category;
  }

  public double getExpected() {
    return expected.get();
  }

  public void setExpected(double newExpected) {
    this.difference.set(newExpected-actual.get());
    this.expected.set(newExpected);
  }

  public double getActual() {
    return actual.get();
  }

  public void setActual(double newActual) {
    this.difference.set(this.expected.get() - newActual);
    this.actual.set(newActual);
  }

  public String getCategoryName() {
    return category.getName();
  }

  public double getDifference() {
    return difference.get();
  }
}
