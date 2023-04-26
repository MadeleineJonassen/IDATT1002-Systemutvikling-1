package no.ntnu.idatt1002.spendwise.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import no.ntnu.idatt1002.spendwise.exceptions.ConformityException;

/**
 * Abstract class that Expense/Income inherits from.
 * All instances derived from this class are immutable.
 * The class uses SimpleStringProperty to format properly in TableViews
 */
public abstract class Transaction {
  private final SimpleStringProperty name;
  private final SimpleStringProperty notes;
  private final SimpleStringProperty date;
  private final SimpleDoubleProperty amount;
  private final SimpleStringProperty amountString; // Only used by tableview
  private SimpleStringProperty category; // Only used by tableview

  /**
   * Constructor for a transaction.
   *
   * @param name   The name given to the transaction.
   * @param notes  The notes given to the transaction, may be empty.
   * @param date   The date given to the transactions as a String. ISO 8601 format.
   * @param amount The amount of the transactions as a double. Does not specify a currency
   */
  public Transaction(String name, String notes, String date, double amount) {
    this.name = new SimpleStringProperty(name);
    this.notes = new SimpleStringProperty(notes);
    this.date = new SimpleStringProperty(date);
    this.amount = new SimpleDoubleProperty(amount);
    this.amountString = new SimpleStringProperty(this.amountToString());
  }

  /**
   * Returns the name of the transaction.
   *
   * @return The name of the transaction as a String.
   */
  public String getName() {
    return name.get();
  }

  /**
   * Returns the notes of the transaction.
   *
   * @return The notes of the transaction as a String.
   */
  public String getNotes() {
    return notes.get();
  }

  /**
   * Returns the date of the transaction.
   *
   * @return The date of the transaction as a String in ISO 8601 format.
   */
  public String getDate() {
    return date.get();
  }

  /**
   * Returns the amount of the transaction.
   *
   * @return The amount of the transaction as a double.
   */
  public double getAmount() {
    return amount.get();
  }

  /**
   * Returns the amount of the transaction as a String. Used for formatting in TableView.
   *
   * @return The amount of the transaction as a String.
   */
  public String getAmountString() {
    return amountString.get();
  }

  /**
   * Returns the category of the transaction.
   *
   * @return The category of the transaction as a String.
   * @throws ConformityException If the category has not been set yet.
   */
  public String getCategory() throws ConformityException {
    if (category == null) {
      throw new ConformityException("Category has not been set yet (it should have been)");
    }

    return category.get();
  }

  /**
   * Sets the category of the transaction. Guarantees immutability safety.
   *
   * @param category The category to be set.
   */
  public void setCategory(String category) {
    // Only fire this method if the category hasn't been set yet
    if (this.category == null) {
      this.category = new SimpleStringProperty(category);
    }
  }

  /**
   * Returns the amount of the transaction as a String. Used for formatting.
   *
   * @return The amount of the transaction as a String.
   */
  protected abstract String amountToString();

  /**
   * Checks if the transaction is within the given time frame.
   *
   * @param fromDate The start date of the time frame.
   * @param toDate   The end date of the time frame.
   * @return True if the transaction is within the time frame, false otherwise.
   */
  public boolean isWithinTimeFrame(LocalDate fromDate, LocalDate toDate) {
    LocalDate transactionDate = LocalDate.parse(this.getDate(),
        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    return (transactionDate.isAfter(fromDate) || transactionDate.equals(fromDate))
        && (transactionDate.equals(toDate) || transactionDate.isBefore(toDate));
  }
}
