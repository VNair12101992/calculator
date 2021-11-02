package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.DriverManager;
import org.junit.Assert;
import app.Calculator;

public class CalculatorStepDef {

    Calculator app = new Calculator();

    @When("I go to the calculator application")
    public void i_go_to_the_calculator_application() {
        Assert.assertEquals(DriverManager.getDriver()
                .findElementById("com.google.android.calculator:id/digit_2")
                .isDisplayed(), true);
    }

    @When("I type {string} in the input field")
    public void i_type_in_the_input_field(String string) {
        app.typeInputField(string);
    }

    @When("I click {string} button")
    public void i_click_button(String string) {
        app.clickBtn(string);
    }

    @Then("{string} result is displaying in the result field")
    public void result_is_displaying_in_the_result_field(String string) {
        Assert.assertEquals(app.getResultField(), string);
    }


    @When("I type {string}")
    public void i_type(String string) {
        app.typeInputField(string);
    }

    @Then("{string} red validation message appears in the result field")
    public void red_validation_message_appears_in_the_result_field(String string) {
        Assert.assertEquals(app.getErrorMessage(), string);
    }
}
