package no.ntnu.idatt1002.SpendWise.data;

import java.util.ArrayList;
import java.util.List;
import javax.naming.NameNotFoundException;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;

/**
 * The main class responsible for holding the data of all transactions and categories.
 * It consists of a hierarchy where Register holds multiple instances of Category.
 * Category consists of transactions (see Category).
 */
public class Register {
  private final ArrayList<Category> categories = new ArrayList<>();

  /**
   * Adds a transaction to the register with a specified category.
   * If the transaction type does not match the category type, a
   * ConformityException will be thrown.
   *
   * @param transaction The transaction to be added.
   * @param catString   The name of the category to add the transaction to.
   * @throws ConformityException If the transaction type does not match the category type.
   */
  public void addTransactionToCategory(Transaction transaction, String catString)
      throws ConformityException {
    Category category = this.getCategoryByName(catString);
    category.addTransaction(transaction);
  }

  /**
   * Returns categories given a transaction type (expense/income).
   *
   * @param isExpense If the category is an expense category or not.
   * @return A list of categories.
   */
  public List<String> getCategoriesByTransactionType(boolean isExpense) {
    List<String> categoriesByType = new ArrayList<>();
    for (Category category : categories) {
      if (isExpense) {
        if (category.isExpenseCategory()) {
          categoriesByType.add(category.getName());
        }
      } else {
        if (category.isIncomeCategory()) {
          System.out.println("Income triggered");
          categoriesByType.add(category.getName());
        }
      }
    }
    return categoriesByType;
  }

  /**
   * Returns all transactions given a transaction type (expense/income).
   *
   * @param isExpense If the transaction is an expense or not.
   * @return A list of transactions.
   */
  public List<Transaction> getTransactionByTransactionType(boolean isExpense) {
    List<Transaction> transactionsByType = new ArrayList<>();
    for (Category category : categories) {
      if (category.isExpenseCategory() == isExpense) {
        transactionsByType.addAll(category.getTransactions());
      }
    }
    return transactionsByType;
  }

  /**
   * Returns all transactions given a certain category type (boolean).
   *
   * @param recurring The category type you want to search for.
   * @return All transactions found as an ArrayList, returns an empty arraylist if none were found.
   */
  public ArrayList<Transaction> getTransactionsByCategoryType(boolean recurring) {
    ArrayList<Transaction> transactions = new ArrayList<>();

    // TODO may introduce bugs
    //  if empty recurring categories show up as not false due to current behavior

    for (Category c : this.categories) {
      if (c.isRecurring() == recurring) {
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

    //TODO this does not need to be a deep copy

    Category categoryCopy = new Category(
        category.getName(), category.isExpenseCategory(), category.isRecurring());
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
  public void addAll(ArrayList<Category> categories) throws
      DuplicateNameException, ConformityException {
    for (Category c : categories) {
      addCategory(c);
    }
  }

  /**
   * Adds all categories passed to the register.
   * If any of the categories already exists a DuplicateNameException will be thrown.
   * The categories are passed as varargs.
   *
   * @param categories Categories to add.
   */
  public void addAll(Category... categories) throws DuplicateNameException, ConformityException {
    for (Category c : categories) {
      addCategory(c);
    }
  }

  /**
   * Checks if a category with the given name exists in the register.
   *
   * @param name Name to check as a String.
   * @return True if name exists, false if it does not.
   */
  private boolean hasName(String name) {
    for (Category c : categories) {
      if (c.getName().equals(name)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns all categories present in the register, with all transactions.
   * This is a deep copy, and can therefore not be used to modify the register.
   *
   * @return A list of categories.
   */
  public List<Category> getCategories() {
    //TODO This has to be tested, since it may break with a deep copy like this
    ArrayList<Category> categoriesCopy = new ArrayList<>();
    for (Category c : categories) {
      Category categoryCopy = new Category(c.getName(), c.isExpenseCategory(), c.isRecurring());
      try {
        // Transaction is guaranteed immutable and can therefore be shallow copied
        categoryCopy.addAll(c.getTransactions());
      } catch (ConformityException e) {
        e.printStackTrace();
      }
      categoriesCopy.add(categoryCopy);
    }

    return categoriesCopy;
  }

  /**
   * Returns an ArrayList of all transactions in the register.
   *
   * @return All transactions as an ArrayList.
   */
  public ArrayList<Transaction> getAllTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();

    for (Category c : categories) {
      transactions.addAll(c.getTransactions());
    }

    return transactions;
  }

  /**
   * Returns the category with the given name. If no category is found, null is returned.
   * This is a shallow copy, and can be used to modify the register.
   *
   * @param text Name of the category to search for
   * @return The category with the given name, or null if no category was found
   */
  public Category getCategoryByName(String text) {
    // TODO review if needs to be changed
    for (Category c : categories) {
      if (c.getName().equals(text)) {
        return c;
      }
    }

    return null;
  }

  /**
   * Removes a category by name from the register.
   *
   * @param categoryName Name of the category to remove.
   * @throws NameNotFoundException If no category with the given name was found.
   */
  public void removeCategoryByString(String categoryName) throws NameNotFoundException {
    if (!categories.removeIf(c -> c.getName().equals(categoryName))) {
      throw new NameNotFoundException("No category with that name was found");
    }
  }

  /**
   * Removes a transaction from the register.
   *
   * @param transaction Transaction to remove.
   * @throws ConformityException If the transaction does not conform to the category.
   */
  public void removeTransaction(Transaction transaction) throws ConformityException {
    Category category = this.getCategoryByName(transaction.getCategory());
    category.removeTransaction(transaction);
  }
}
