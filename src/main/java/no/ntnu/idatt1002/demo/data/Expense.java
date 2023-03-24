package no.ntnu.idatt1002.demo.data;

import java.util.Date;

/**
 * Describes an expence using name, notes, date and amount.
 * String name - transaction name
 * String notes - short notes for the expense
 * Date date - ISO-8601 format of date for transaction
 * double amount - amount of the expense
 */
public class Expense extends Transaction {
  public Expense(String name, String notes, Date date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO override the date?
}
