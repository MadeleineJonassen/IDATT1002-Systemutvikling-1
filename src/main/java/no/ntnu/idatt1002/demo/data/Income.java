package no.ntnu.idatt1002.demo.data;

import java.util.Date;

/**
 * Describes an income using name, notes, date and amount.
 * String name - transaction name
 * String notes - short notes for the income
 * Date date - ISO-8601 format of date for transaction
 * double amount - amount of the income
 */

public class Income extends Transaction {
  public Income(String name, String notes, Date date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO
}
