package no.ntnu.idatt1002.demo.data;

//TODO should there be an extra class for subscriptions that inherit from Transaction?

import java.util.ArrayList;

public class Register {
    private int numberOfTransactions;
    private ArrayList<Category> categories;

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
    public void getTransactionsByCategory(String category){
        //TODO
    }

    /**
     * Returns all transactions given a certain category type (class).
     *
     * @param c The category type you want to search for. This is passed as an empty object of the type you want
     * @return All transactions found as an ArrayList, returns an empty arraylist if none were found
     */
    public ArrayList<Transaction> getTransactionsByCategoryType(Category c){
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Category category : this.categories){
            if (category.getClass().equals(c.getClass())){
                transactions.addAll(category.getTransactions());
            }
        }

        return transactions;
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

        //TODO
        return netIncome;
    }

    //TODO doing this to avoid aggregation. Change if this impacts coupling too much

    //TODO getCategoriesByName
}
