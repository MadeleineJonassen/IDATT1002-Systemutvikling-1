package no.ntnu.idatt1002.demo.data;


import java.util.Calendar;
//Date format to format dates

/**
 *  Entity class representing a transaction in the app. All instances of this class
 *  is held by the Register class. It is assumed all amounts are the same currency
 *
 */
public class Transaction {
    private final double amount;
    private String category;
    private final String name;
    private final Calendar date;

    /**
     * Constructor for a transaction, without the date. If this is called, it is assumed
     * that the current date is the date to be set
     *
     * @param amount The amount of the transaction as a double. If it is negative, that means money
     * is deducted from the user's account
     * @param category The category of the transaction as a string
     * @param name The name given to the transaction as a string
     */
    Transaction(double amount, String category, String name){
        this.amount = amount;
        this.category = category;
        this.name = name;
        this.date = Calendar.getInstance();
    }

    /**
     * Constructor for a transaction, with a date given.
     *
     * @param amount The amount of the transaction as a double. If it is negative, that means money
     * is deducted from the user's account
     * @param category The category of the transaction as a string
     * @param name The name given to the transaction as a string
     * @param date The date given to the transaction as a Calendar object
     */
    Transaction(double amount, String category, String name, Calendar date){
        this.amount = amount;
        this.category = category;
        this.name = name;
        //TODO does this mean that if the date object passed as an argument is deleted, that this will not persist?
        this.date = date;
    }

    /**
     * Returns the transaction amount. If it is negative, that means money
     * is deducted from the user's account
     *
     * @return The amount as a double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the category associated with the transaction.
     *
     * @return The category as a string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name given to the transaction.
     *
     * @return The name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date for the transaction
     *
     * @return The date as a Calendar object
     */
    public Calendar getDate(){ return date; }

    /**
     * Changes the category associated with the transaction
     *
     * @param category New category as a string
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
