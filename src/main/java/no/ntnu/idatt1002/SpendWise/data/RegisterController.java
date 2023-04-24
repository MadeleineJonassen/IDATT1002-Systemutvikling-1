package no.ntnu.idatt1002.SpendWise.data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Loader class containing methods for writing and reading data from a JSON file,
 * which holds all the data of the register.
 */
public class RegisterController {
  /**
   * Reads data from a JSON file and returns a Register object.
   *
   * @param url The URL of the JSON file.
   * @return A Register object, with all the data from the JSON file loaded.
   * @throws IOException If the file read fails.
   * @throws URISyntaxException If the URL is invalid.
   * @throws ConformityException If the data in the JSON file is invalid.
   * @throws DuplicateNameException If the data in the JSON file is invalid.
   */
  @SuppressWarnings("VulnerableCodeUsages")
  public static Register readData(URL url) throws
      IOException, URISyntaxException, ConformityException, DuplicateNameException {
    // Default database should be in resources/database
    File file = new File(url.toURI());
    String content = FileUtils.readFileToString(file, "utf-8");
    JSONObject json = new JSONObject(content);

    Register register = new Register();

    JSONArray jsonCategories = json.getJSONArray("categories");
    for (int i = 0; i < jsonCategories.length(); i++) {
      JSONObject currentJsonCategory = jsonCategories.getJSONObject(i);

      Category category;
      // TODO add failsafes so regular transactions can not be recurring and vice versa?
      category = new Category(
          currentJsonCategory.getString("name"),
          currentJsonCategory.getBoolean("isExpenseCategory"),
          currentJsonCategory.getBoolean("recurring"));

      JSONArray jsonTransactions = currentJsonCategory.getJSONArray("transactions");
      for (int j = 0; j < jsonTransactions.length(); j++) {
        JSONObject currentJsonTransaction = jsonTransactions.getJSONObject(j);

        // If the amounts are written with minus signs, they are expenses.
        // This is for readability
        Transaction t;
        if (category.isExpenseCategory()) {
          if (currentJsonCategory.getBoolean("recurring")) {
            t = new RecurringExpense(
                currentJsonTransaction.getString("name"),
                currentJsonTransaction.getString("notes"),
                currentJsonTransaction.getString("date"),
                -(currentJsonTransaction.getDouble("amount")));
          } else {
            t = new Expense(
                currentJsonTransaction.getString("name"),
                currentJsonTransaction.getString("notes"),
                currentJsonTransaction.getString("date"),
                -(currentJsonTransaction.getDouble("amount")));
          }
        } else {
          if (currentJsonCategory.getBoolean("recurring")) {
            t = new RecurringIncome(
                currentJsonTransaction.getString("name"),
                currentJsonTransaction.getString("notes"),
                currentJsonTransaction.getString("date"),
                currentJsonTransaction.getDouble("amount"));
          } else {
            t = new Income(
                currentJsonTransaction.getString("name"),
                currentJsonTransaction.getString("notes"),
                currentJsonTransaction.getString("date"),
                currentJsonTransaction.getDouble("amount"));
          }
        }
        category.addTransaction(t);
      }
      register.addCategory(category);
    }
    return register;
  }

  /**
   * Writes data from a Register object to a JSON object. It does not actually write
   * to a file, but serializes and returns a JSON object that can be written to a file.
   *
   * @param register The register to write data from.
   * @return A JSON object containing all the data from the register.
   * @throws IOException If the file write fails.
   */
  @SuppressWarnings("VulnerableCodeUsages")
  public static JSONObject writeData(Register register) throws IOException {
    JSONObject json = new JSONObject();

    JSONArray categoriesJson = new JSONArray();
    ArrayList<Category> categories = (ArrayList<Category>) register.getCategories();

    for (Category c : categories) {
      JSONObject categoryJson = new JSONObject();

      categoryJson.put("name", c.getName());
      categoryJson.put("recurring", c.isRecurring());
      categoryJson.put("isExpenseCategory", c.isExpenseCategory());

      JSONArray transactionListJson = new JSONArray();
      for (Transaction t : c.getTransactions()) {
        JSONObject transactionJson = new JSONObject();
        transactionJson.put("name", t.getName());
        transactionJson.put("notes", t.getNotes());
        transactionJson.put("date", t.getDate());

        if (t instanceof Expense) {
          transactionJson.put("amount", -t.getAmount());
        } else {
          transactionJson.put("amount", t.getAmount());
        }

        transactionListJson.put(transactionJson);
      }

      categoryJson.put("transactions", transactionListJson);
      categoriesJson.put(categoryJson);
    }
    json.put("categories", categoriesJson);

    return json;
  }
}
