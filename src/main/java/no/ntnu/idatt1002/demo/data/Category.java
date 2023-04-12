package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Category {
    private String name;
    //TODO not let recurring be part of this class but instead RecurringCategory
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private int numberOfTransactions;

    public Category(String name){
        this.name = name;
    }
    public Category(){};

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

    public boolean isExpenseCategory(){
        return transactions.get(0) instanceof Expense;
    }
    public boolean isIncomeCategory(){ return transactions.get(0) instanceof Income; }
    public boolean isRecurring() {
        return ((transactions.get(0) instanceof RecurringExpense)
                || (transactions.get(0) instanceof RecurringIncome));
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

    //TODO remove transaction, delete category (with moving transactions?), search transaction by name/id
}
