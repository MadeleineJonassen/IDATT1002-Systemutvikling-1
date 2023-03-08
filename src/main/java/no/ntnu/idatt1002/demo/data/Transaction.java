package no.ntnu.idatt1002.demo.data;


/**
 *  Entity class representing a transaction in the app. All instances of this class
 *  is held by the Register class. It is assumed all amounts are the same currency
 *
 */
public class Transaction {
    private final double amount;
    private String category;
    private final String name;

    Transaction(double amount, String category, String name){
        this.amount = amount;
        this.category = category;
        this.name = name;
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
     * Changes the category associated with the transaction
     *
     * @param category New category as a string
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
