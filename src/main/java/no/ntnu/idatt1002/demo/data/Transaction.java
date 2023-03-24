package no.ntnu.idatt1002.demo.data;

import java.util.Calendar;
import java.util.Date;

//Date format to format dates

//TODO make user tests for all in data

//NOTE Since this class is immutable, deep copies should not be necessary. If this is wrong
//all files copying transactions need to be changed

/**
 * Abstract class that expense/income inherits from.
 */
public abstract class Transaction {
  private String name;
  private String notes;
  private Date date;
  private double amount;

  //Constructor in here?

  /**
   * Creates a transaction with following parameters.

   * @param name of the transaction
   * @param notes small notes for the transaction
   * @param date of the transaction
   * @param amount of money transferred
   */
  public Transaction(String name, String notes, Date date, double amount) {
    this.name = name;
    this.notes = notes;
    //TODO deep copy, or solved by being private?
    this.date = date;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public String getNotes() {
    return notes;
  }

  public Date getDate() {
    return date;
  }

  public double getAmount() {
    return amount;
  }
}
