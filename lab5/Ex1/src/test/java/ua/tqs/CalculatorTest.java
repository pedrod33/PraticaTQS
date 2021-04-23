package ua.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator c;

    @Given("^a turned on calculator$")
    public void setUp(){
        c = new Calculator();
    }

    @When("I add 2 to 2")
    public void iAdd2() {
        c.push(2);
        c.push(2);
        c.push("+");
    }

    @When("I subtract 3 to 5")
    public void iSubtractTo() {
        c.push(5);
        c.push(3);
        c.push("-");
    }

    @When("I add {int} and {int}")
    public void iAddTo(int arg0, int arg1) {
        c.push(arg0);
        c.push(arg1);
        c.push("+");
    }

    @Then("{double}")
    public void the_result_is(double expected) {
        assertEquals(expected, c.value());
    }
}
