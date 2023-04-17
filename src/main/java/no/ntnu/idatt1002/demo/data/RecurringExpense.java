package no.ntnu.idatt1002.demo.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RecurringExpense extends Expense{
  public RecurringExpense(String name, String notes, String date, double amount) {
    super(name, notes, date, amount);
  }

  public boolean isWithinTimeFrame(LocalDate fromDate, LocalDate toDate){
    // The format for "date" in a recurring transaction is just a day of the month
    // So we need to add the month and year to the date (current) to be able to compare it to the time frame
    LocalDate transactionDate = LocalDate.parse(LocalDate.now().getYear() +
            "-" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))+
            "-" + this.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    return (transactionDate.isAfter(fromDate) || transactionDate.equals(fromDate)) &&
        (transactionDate.equals(toDate) || transactionDate.isBefore(toDate));
  }
}
