package no.ntnu.idatt1002.demo.data;

import java.util.Date;

public class RecurringExpense extends Expense{
  public RecurringExpense(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }

  public RecurringExpense(Expense e){
    super(e.getName(), e.getNotes(), e.getDate(), e.getAmount());
  }
}
