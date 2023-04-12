package no.ntnu.idatt1002.demo.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Calendar;
//Date format to format dates

//TODO make user tests for all in data

//NOTE Since this class is immutable, deep copies should not be necessary. If this is wrong
//all files copying transactions need to be changed

/**
 * Abstract class that expense/income inherits from.
 */
public abstract class Transaction {
    private final SimpleStringProperty name;
    private final SimpleStringProperty notes;
    //TODO make into string (all other places)
    private final SimpleStringProperty date;
    private final SimpleDoubleProperty amount;
    private SimpleStringProperty category; // Only used by tableview
    private SimpleStringProperty amountString; // Only used by tableview

    //Constructor in here?
    public Transaction(String name, String notes, String date, double amount){
        this.name = new SimpleStringProperty(name);
        this.notes = new SimpleStringProperty(notes);
        this.date = new SimpleStringProperty(date);
        this.amount = new SimpleDoubleProperty(amount);
        this.amountString = new SimpleStringProperty(this.amountToString());
    }

    public String getName() {
        return name.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public String getDate() {
        return date.get();
    }

    public double getAmount(){
        return amount.get();
    }

    public String getAmountString(){
        return amountString.get();
    }

    public String getCategory() throws ConformityException{
        if (category == null){
            throw new ConformityException("Category has not been set yet (it should have been)");
        }

        return category.get();
    }

    public void setCategory(String category){
        //Only fire this method if the category hasn't been set yet
        if (this.category == null){
            this.category = new SimpleStringProperty(category);
        }
    }

    protected abstract String amountToString();

    public boolean isWithinTimeFrame(LocalDate fromDate, LocalDate toDate){
        LocalDate transactionDate = LocalDate.parse(this.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return transactionDate.isAfter(fromDate) && transactionDate.isBefore(toDate);
    }
}
