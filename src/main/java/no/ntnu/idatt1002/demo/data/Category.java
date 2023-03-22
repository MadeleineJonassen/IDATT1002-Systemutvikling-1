package no.ntnu.idatt1002.demo.data;

import java.util.ArrayList;

public class Category {
    private String name;
    //TODO not let recurring be part of this class but instead RecurringCategory
    private ArrayList<Transaction> transactions;
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

    public void addTransaction(Transaction t){
        transactions.add(t);
    }

    public void addAll(ArrayList<Transaction> transactionsToAdd){
        for (Transaction t : transactionsToAdd){
            addTransaction(t);
        }
    }

    //TODO remove transaction, delete category (with moving transactions?), search transaction by name/id
}
