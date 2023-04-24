package no.ntnu.idatt1002.SpendWise.data;

import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.NameNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for data operations with the Register class and its children. This uses the backup.json
 * to not mess with the actual database on launch.
 */
public class RegisterTest {
    private Register register;

    @BeforeEach
    public void setup() throws DuplicateNameException, ConformityException, URISyntaxException, IOException {
        URL url = Objects.requireNonNull(getClass().getClassLoader().getResource("database/backup.json"));

        File file = new File(url.toURI());
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject readJson = new JSONObject(content);
        this.register = RegisterController.readData(url);
    }

    @Test
    public void sumTest() {
        Category housing = register.getCategoryByName("Housing");
        // With the default database this should equate to 4500+200 = 4700
        assertEquals(housing.getTotalAmount(), 4700);
    }

    @Test
    public void removeCategoryTest () throws NameNotFoundException {
        register.removeCategoryByString("Housing");
        assertEquals(register.getCategories().size(), 5);
    }

    @Test
    public void removeTransactionTest () throws NameNotFoundException {
        Category housing = register.getCategoryByName("Housing");
        housing.removeTransaction(housing.getTransactions().get(0));
        assertEquals(register.getCategoryByName("Housing").getTransactions().size(), 1);
    }

    @Test
    public void addCategoryTest () throws DuplicateNameException, ConformityException {
        register.addCategory(new Category("Test", true, false));
        assertEquals(register.getCategories().size(), 7);
    }

    @Test
    public void addTransactionTest () throws DuplicateNameException, ConformityException, NameNotFoundException {
        Category housing = register.getCategoryByName("Housing");
        housing.addTransaction(new RecurringExpense("Test", "", "2021-04-20", 400));
        assertEquals(register.getCategoryByName("Housing").getTransactions().size(), 3);
        // 4500+200+400 = 5100
        assertEquals(register.getCategoryByName("Housing").getTotalAmount(), 5100);
    }
}
