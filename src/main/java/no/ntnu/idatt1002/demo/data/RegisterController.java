package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class RegisterController {
    @SuppressWarnings("VulnerableCodeUsages")
    public static Register readData(URL url) throws IOException, URISyntaxException, ConformityException, DuplicateNameException {
        // Default database should be in resources/database
        File file = new File(url.toURI());
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject json = new JSONObject(content);

        Register register = new Register();

        JSONArray jsonCategories = json.getJSONArray("categories");
        for (int i = 0; i < jsonCategories.length(); i++){
            JSONObject currentJsonCategory = jsonCategories.getJSONObject(i);

            Category category;
            // TODO add failsafes so regular transactions can not be recurring and vice versa?
            category = new Category(currentJsonCategory.getString("name"));

            JSONArray jsonTransactions = currentJsonCategory.getJSONArray("transactions");
            for (int j = 0; j < jsonTransactions.length(); j++){
                JSONObject currentJsonTransaction = jsonTransactions.getJSONObject(j);

                // If the amounts are written with minus signs, they are expenses
                Transaction t;
                if (currentJsonTransaction.getDouble("amount") < 0){
                    if (currentJsonCategory.getBoolean("recurring")){
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
                    if (currentJsonCategory.getBoolean("recurring")){
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

    @SuppressWarnings("VulnerableCodeUsages") //TODO fix, recurring write
    public static JSONObject writeData(Register register) throws IOException {
        JSONObject json = new JSONObject();

        JSONArray categoriesJson = new JSONArray();
        ArrayList<Category> categories = register.getCategories();

        for (Category c : categories){
            JSONObject categoryJson = new JSONObject();

            categoryJson.put("name", c.getName());
            categoryJson.put("recurring", c.isRecurring());

            JSONArray transactionListJson = new JSONArray();
            for (Transaction t: c.getTransactions()){
                JSONObject transactionJson = new JSONObject();
                transactionJson.put("name", t.getName());
                transactionJson.put("notes", t.getNotes());
                transactionJson.put("date", t.getDate());

                if (t instanceof Expense){
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

        //TODO find differences where json can be written and read
        //FileWriter file = new FileWriter(Register.class.getResource(".").getFile() + "/" + jsonPath);
        //file.write(json.toString());
        //file.close();


    }

    /*
    public static void main(String[] args) throws IOException, URISyntaxException {
        try{
            loadData(getClass().getClassLoader().getResource("database/register.json"));
        } catch (Exception e){

        }

    }*/
}
