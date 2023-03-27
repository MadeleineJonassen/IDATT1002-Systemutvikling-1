package no.ntnu.idatt1002.demo.data;

import java.util.Date;

public class Income extends Transaction {
  public Income(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }
  //TODO

  protected String amountToString(){
    return Double.toString(this.getAmount()) + " NKr";
  }
}
