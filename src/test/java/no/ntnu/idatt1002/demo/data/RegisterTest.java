package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    private Register register;

    @BeforeEach
    public void setup() throws DuplicateNameException {
        register = new Register();

        Category food = new Category("Food");
        RecurringCategory loan = new RecurringCategory("Loan");
        RecurringCategory job = new RecurringCategory("Job");

        register.addAll(food, loan, job);
    }
}
