package no.ntnu.idatt1002.demo.data;

import java.util.ArrayList;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;

/**
 * A class containing category to be made after MVP.
 */
public class Category {
  private String name;
  //TODO not let recurring be part of this class but instead RecurringCategory
  private ArrayList<Transaction> transactions;
  private int numberOfTransactions;

  public Category(String name) {
    this.name = name;
  }

  public Category() {

  }

  public String getName() {
    return name;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
    //TODO
  }

  /**
   * Used to add a transaction to registry.

   * @param t - transaction inputted
   * @throws ConformityException - thrown if
   */
  public void addTransaction(Transaction t) throws ConformityException {
    //If transaction is not the same as any transaction in the category
    if (!transactions.isEmpty()) {
      if (!transactions.get(0).getClass().equals(t.getClass())) {
        throw new ConformityException("Transaction has to be of same type as others in category");
      }
    }

    transactions.add(t);
  }

  /**
   * Adds all transactions to registry.

   * @param transactionsToAdd an ArrayList of all transactions.
   * @throws ConformityException thrown if
   */
  public void addAll(ArrayList<Transaction> transactionsToAdd) throws ConformityException {
    for (Transaction t : transactionsToAdd) {
      addTransaction(t);
    }
  }

  //TODO remove transaction, delete category (with moving transactions?),
  // search transaction by name/id
}
