package no.ntnu.idatt1002.demo.data;

import no.ntnu.idatt1002.demo.exceptions.ConformityException;
import no.ntnu.idatt1002.demo.exceptions.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterTest {
    private Register register;

    @BeforeEach
    public void setup() throws DuplicateNameException, ConformityException {
        register = new Register();

        // TODO Empty, replaced with test in RegisterControllerTest for now

        /*
        Category food = new Category("Food");
        Category loan = new Category("Loan");
        //loan.addTransaction(new Income("Student loan", ));
        Category job = new Category("Job");

        register.addAll(food, loan, job);*/
    }
}
