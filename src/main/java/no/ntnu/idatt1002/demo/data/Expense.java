package no.ntnu.idatt1002.demo.data;

import java.util.Date;

public class Expense extends Transaction{
  public Expense(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO override the date?

    protected String amountToString(){
        return "-" + Double.toString(this.getAmount()) + " NKr";
    }
}
