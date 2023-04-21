package no.ntnu.idatt1002.SpendWise.data;

import no.ntnu.idatt1002.SpendWise.exceptions.ConformityException;
import no.ntnu.idatt1002.SpendWise.exceptions.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;

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
