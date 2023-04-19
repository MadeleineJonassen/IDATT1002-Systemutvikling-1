package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class responsible for holding the data of all transactions and categories.
 * It consists of a hierarchy where Register holds multiple instances of Category.
 * Category consists of transactions (see Category).
 */
public class Register {
    private final ArrayList<Category> categories = new ArrayList<>();

    /**
     *
     *
     * @param transaction
     * @param catString
     * @throws ConformityException
     */
    public void addTransactionToCategory(Transaction transaction, String catString) throws ConformityException {
        Category category = this.getCategoryByName(catString);
        category.addTransaction(transaction);
    }

    /**
     *
     * @param isExpense
     * @return
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
     *
     * @param isExpense
     * @return
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
    public ArrayList<Transaction> getTransactionsByCategoryType(boolean recurring){
        ArrayList<Transaction> transactions = new ArrayList<>();

        // TODO may introduce bugs
        //  if empty recurring categories show up as not false due to current behavior

        for (Category c : this.categories){
            if (c.isRecurring() == recurring){
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
        if (hasName(category.getName())){
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
    public void addAll(ArrayList<Category> categories) throws DuplicateNameException, ConformityException {
        for (Category c : categories){
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
        for (Category c : categories){
            addCategory(c);
        }
    }

    /**
     * Checks if a category with the given name exists in the register
     *
     * @param name Name to check as a String
     * @return True if name exists, false if it does not
     */
    private boolean hasName(String name) {
        for (Category c : categories){
            if (c.getName().equals(name)){
                return true;
            }
        }

        return false;
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

    public ArrayList<Category> getCategories() {
        //TODO write properly deep copy
        return categories;
    }

    /**
     * Returns an ArrayList of all transactions in the register
     *
     * @return All transactions as an ArrayList
     */
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Category c : categories){
            transactions.addAll(c.getTransactions());
        }

        return transactions;
    }

    /**
     * Return the number of transactions across all categories in the register
     *
     * @return The number of transactions as an int
     */
    public int getNumberOfTransactions() {
        int numberOfTransactions = 0;

        for (Category c : categories) {
            numberOfTransactions += c.getNumberOfTransactions();
        }

        return numberOfTransactions;
    }

    /**
     * Returns the category with the given name
     *
     * @param text Name of the category to search for
     * @return The category with the given name, or null if no category was found
     */
    public Category getCategoryByName(String text) {
        //TODO write properly deep copy
        for (Category c : categories){
            if (c.getName().equals(text)){
                return c;
            }
        }

        return null;
    }

    public void removeCategoryByString(String categoryName){
        // TODO should this throw name not found exception?
        categories.removeIf(c -> c.getName().equals(categoryName));
    }

    //TODO doing this to avoid aggregation. Change if this impacts coupling too much

    //TODO getCategoriesByName

    public void removeTransaction(Transaction transaction) throws ConformityException {
        Category category = this.getCategoryByName(transaction.getCategory());
        category.removeTransaction(transaction);
    }
}
