package no.ntnu.idatt1002.demo.data;

//TODO should there be an extra class for subscriptions that inherit from Transaction?

import java.util.ArrayList;

public class Register {
    private int numberOfTransactions;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Returns the number of transactions in the register
     *
     * @return The number of transactions as an int
     */
    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    /**
     * Returns a deep-copy of the transactions given a certain category
     *
     * @param category The category to search for as a String
     * @return All transactions found as an ArrayList, returns an empty arraylist if none were found
     */
    public ArrayList<Transaction> getTransactionsByCategory(String category){
        ArrayList<Transaction> foundTransactions = new ArrayList<>();
        for (Transaction t : this.transactions){
            if (t.getCategory().equals(category)){
                Transaction transactionCopy = new Transaction(t.getAmount(), t.getCategory(), t.getName());
                foundTransactions.add(transactionCopy);
            }
        }

        return foundTransactions;
    }

    //TODO should this be here? Or should it only work for subscriptions
    /**
     * Based on all transactions in the register, this method calculates the
     * net income
     *
     * @return The net income as a double. Negative means the user is losing money.
     */
    public double getNetIncome(){
        double netIncome = 0;

        for (Transaction t: this.transactions){
            netIncome += t.getAmount();
        }

        return netIncome;
    }

    //TODO doing this to avoid aggregation. Change if this impacts coupling too much

    /**
     * Add a transaction to the register
     *
     * @param amount The amount of currency the transaction holds as a double
     * @param category The category associated with the transaction as a string
     * @param name The name given to the transaction as a String
     */
    public void addTransaction(double amount, String category, String name){
        this.transactions.add(new Transaction(amount, category, name));
    }
}
