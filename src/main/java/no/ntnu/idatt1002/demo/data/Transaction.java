package no.ntnu.idatt1002.demo.data;

import java.util.Date;

import java.util.Calendar;
//Date format to format dates

/**
 * Abstract class that expense/income inherits from.
 */
public abstract class Transaction {
    private String name;
    private String notes;
    private Date date;
    private double amount;

    //Constructor in here?

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount(){
        return amount;
    }
}
