package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * An instance of Category holds Transaction objects. A category has a name, and can only consist of
 * one type of Transaction (Expense/Income), and can only consist of either recurring transactions or not.
 */
public class Category {
    private final String name;
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private int numberOfTransactions;
    private final boolean isExpenseCategory; // false means income, true means expense
    private final boolean isRecurringCategory;

    /**
     * Constructor a category.
     *
     * @param name The name of the category.
     * @param isExpenseCategory If the category holds Expense objects or not.
     * @param isRecurringCategory If the category is recurring or not
     */
    public Category(String name, boolean isExpenseCategory, boolean isRecurringCategory){
        this.name = name;
        this.isExpenseCategory = isExpenseCategory;
        this.isRecurringCategory = isRecurringCategory;
    }


    /**
     * Returns the name of the category.
     *
     * @return Name of the category as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all transactions in a category.
     * Does not make deep-copy since Transaction objects are immutable.
     *
     * @return A List of all transactions in the category.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Adds a Transaction to the category.
     *
     * @param t The Transaction to be added.
     * @throws ConformityException If the type of transaction passed does not conform to the
     * transaction type already present in the category.
     */
    public void addTransaction(Transaction t) throws ConformityException {

        // If transaction is not the same as any other transaction in the category
        if (!transactions.isEmpty()){
            if (!transactions.get(0).getClass().equals(t.getClass())){
                throw new ConformityException("Transaction has to be of same type as others in category");
            }
        }

        t.setCategory(this.name);
        transactions.add(t);
        numberOfTransactions++;
    }

    /**
     * Removes a transaction from the category.
     *
     * @param transaction The transaction to be removed
     */
    public void removeTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transactions.remove(transaction);
        } else {
            throw new IllegalArgumentException("The transaction does not exist");
        }
    }

    /**
     * Adds all transactions present in a List to the category.
     * @param transactionsToAdd The List of transactions to add
     * @throws ConformityException If any addTransaction call fails
     */
    public void addAll(List<Transaction> transactionsToAdd) throws ConformityException{
        for (Transaction t : transactionsToAdd){
            addTransaction(t);
        }
    }

    /**
     * Returns the number of transactions in the category.
     *
     * @return Number of transactions present.
     */
    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    /**
     * Returns whether the category is of a recurring type
     *
     * @return If the category is recurring.
     */
    public boolean isRecurring() {
        return isRecurringCategory;
    }


    /**
     * Returns the total amount summed by all transactions in the category.
     * This will always be positive, even if the category has expenses.
     *
     * @return The sum of amounts as a double.
     */
    public double getTotalAmount() {
        double total = 0;

        for (Transaction t : transactions){
            total += t.getAmount();
        }

        return total;
    }

    /**
     * Returns the sum of transactions in the category that fit the given timeframe.
     *
     * @param from The "from" date as a LocalDate object.
     * @param to The "to" date as a LocalDate object.
     * @return The sum of transactions in the given timeframe as a double.
     */
    public double getTotalAmountWithinTimeFrame(LocalDate from, LocalDate to) {
        double total = 0;

        for (Transaction t : transactions){
            if (t.isWithinTimeFrame(from, to)){
                total += t.getAmount();
            }
        }

        return total;
    }

    /**
     * If the category is an Expense category or not.
     *
     * @return True if the category is an Expense category.
     */
    public boolean isExpenseCategory() {
        return isExpenseCategory;
    }

    /**
     * If the category is an Income category or not.
     *
     * @return True if the category is an Income category.
     */
    public boolean isIncomeCategory() {
        return !isExpenseCategory;
    }
}
