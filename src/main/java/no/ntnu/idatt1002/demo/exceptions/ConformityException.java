package no.ntnu.idatt1002.demo.exceptions;

/**
 * Exception that is thrown when a transaction is attempted to add to a category,
 * and the transaction does not conform to the type of transactions already present in
 * the category.
 */
public class ConformityException extends Exception {
  public ConformityException(String message) {
    super(message);
  }
}
