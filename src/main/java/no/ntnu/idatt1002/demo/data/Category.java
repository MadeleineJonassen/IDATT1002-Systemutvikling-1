package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Category {
    private String name;
    //TODO not let recurring be part of this class but instead RecurringCategory
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private int numberOfTransactions;
    // false means income, true means expense
    private boolean isExpenseCategory;
    private boolean isRecurringCategory;

    public Category(String name, boolean isExpenseCategory, boolean isRecurringCategory){
        this.name = name;
        this.isExpenseCategory = isExpenseCategory;
        this.isRecurringCategory = isRecurringCategory;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
        //TODO
    }

    public void addTransaction(Transaction t) throws ConformityException {
        //If transaction is not the same as any transaction in the category
        if (!transactions.isEmpty()){
            if (!transactions.get(0).getClass().equals(t.getClass())){
                throw new ConformityException("Transaction has to be of same type as others in category");
            }
        }

        t.setCategory(this.name);
        transactions.add(t);
        numberOfTransactions++;
    }

    public void addAll(ArrayList<Transaction> transactionsToAdd) throws ConformityException{
        for (Transaction t : transactionsToAdd){
            addTransaction(t);
        }
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public boolean isRecurring() {
        return isRecurringCategory;
    }


    public double getTotalAmount() {
        double total = 0;

        for (Transaction t : transactions){
            total += t.getAmount();
        }

        return total;
    }

    public double getTotalAmountWithinTimeFrame(LocalDate from, LocalDate to) {
        double total = 0;

        for (Transaction t : transactions){
            if (t.isWithinTimeFrame(from, to)){
                total += t.getAmount();
            }
        }

        return total;
    }

    public boolean isExpenseCategory() {
        return isExpenseCategory;
    }

    public boolean isIncomeCategory() {
        return !isExpenseCategory;
    }

    //TODO remove transaction, delete category (with moving transactions?), search transaction by name/id
}
