package no.ntnu.idatt1002.demo.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//TODO javadoc for each test?

/**
 * User tests for testing the entity classes Register and Entity
 */
public class EntityClassTest {
    Register register = new Register();

    @BeforeEach
    public void registerSetup(){
        //Amount are in NKr
        register.addTransaction(-79.99, "Entertainment", "Tv-streaming");
        register.addTransaction(-249.99, "Food", "Food delivery");
        register.addTransaction(-723.50, "Food", "Grocery store");
    }

    @Test
    public void categorySearchTestEquals(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(-249.99, "Food", "Food delivery"));
        transactions.add(new Transaction(-723.50, "Food", "Grocery store"));

        //TODO better name
        ArrayList<Transaction> categoryTransactions = register.getTransactionsByCategory("Food");
        for (int i = 0; i < transactions.size(); i++){
            assertEquals(transactions.get(i).getAmount(), categoryTransactions.get(i).getAmount());
            assertEquals(transactions.get(i).getCategory(), categoryTransactions.get(i).getCategory());
            assertEquals(transactions.get(i).getName(), categoryTransactions.get(i).getName());
        }
    }

    @Test
    public void numberOfTransactionsEquals(){
        assertEquals(register.getNumberOfTransactions(), 3);
    }

    @Test
    public void numberOfTransactionsNotEquals(){
        //Addition to register is only temporary, and will not impact future unit tests
        register.addTransaction(-7500, "Rent", "Rent for apartment");
        assertNotEquals(register.getNumberOfTransactions(), 3);
    }

    @Test
    public void netIncomeEquals(){
        //-(79.99 + 249.99 + 723.50) = -1053.48
        assertEquals(register.getNetIncome(), -1053.48);
    }
}
