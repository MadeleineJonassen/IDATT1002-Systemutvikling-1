package no.ntnu.idatt1002.demo.data;

//TODO should there be an extra class for subscriptions that inherit from Transaction?

import java.util.ArrayList;
import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

/**
 * Register for transactions.
 */
public class Register {
  private int numberOfTransactions;
  private ArrayList<Category> categories;

  /**
   * Returns the number of transactions in the register.
   *
   * @return The number of transactions as an int
   */
  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

  /**
   * Returns a deep-copy of the transactions given a certain category.
   *
   * @param category The category to search for as a String
   * @return All transactions found as an ArrayList, returns an empty arraylist if none were found.
   */
  public ArrayList<Transaction> getTransactionsByCategory(String category) {
    //TODO
    return null;
  }

  /**
   * Returns all transactions given a certain category type (class).
   *
   * @param category The category type you want to search for.
   *                 This can and should be passed as an empty object of the type you want
   * @return All transactions found as an ArrayList, returns an empty arraylist if none were found
   */
  public ArrayList<Transaction> getTransactionsByCategoryType(Category category) {
    ArrayList<Transaction> transactions = new ArrayList<>();

    for (Category c : this.categories) {
      if (c.getClass().equals(category.getClass())) {
        transactions.addAll(c.getTransactions());
      }
    }

    return transactions;
  }

  /**
   * Adds a category to the register. If the category already exists a DuplicateNameException
   * will be thrown, as categories must be unequivocally named
   *
   * @param category Category object to add to the register
   */
  public void addCategory(Category category) throws DuplicateNameException, ConformityException {
    if (hasName(category.getName())) {
      throw new DuplicateNameException("Category with that name already exists in the register");
    }

    Category categoryCopy = new Category(category.getName());
    categoryCopy.addAll(category.getTransactions());
    categories.add(categoryCopy);
  }

  /**
   * Adds all categories passed to the register.
   * If any of the categories already exists a DuplicateNameException will be thrown.
   * The categories are passed as an ArrayList
   *
   * @param categories ArrayList of categories
   */
  public void addAll(ArrayList<Category> categories)
      throws DuplicateNameException, ConformityException {
    for (Category c : categories) {
      addCategory(c);
    }
  }

  /**
   * Adds all categories passed to the register.
   * If any of the categories already exists a DuplicateNameException will be thrown.
   * The categories are passed as varargs
   *
   * @param categories Categories to add
   */
  public void addAll(Category... categories) throws DuplicateNameException, ConformityException {
    for (Category c : categories) {
      addCategory(c);
    }
  }

  /**
   * Checks if a category with the given name exists in the register.
   *
   * @param name Name to check as a String
   * @return True if name exists, false if it does not
   */
  private boolean hasName(String name) {
    for (Category c : categories) {
      if (c.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  //TODO should this be here? Or should it only work for subscriptions
  /**
   * Based on all transactions in the register, this method calculates the
   * net income.
   *
   * @return The net income as a double. Negative means the user is losing money.
   */
  public double getNetIncome() {
    double netIncome = 0;

    //TODO
    return netIncome;
  }

  //TODO doing this to avoid aggregation. Change if this impacts coupling too much

  //TODO getCategoriesByName
}
