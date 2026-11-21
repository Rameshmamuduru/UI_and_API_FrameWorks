
package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import base.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomeCataloguePage;
import pageObjects.LoginPage;
import pageObjects.UserHomePage;

public class LoginSteps {

    public static UserHomePage uhp;
    public static HomeCataloguePage hcp;
    public static LoginPage lp;

    // Initialize all Page Objects using the existing WebDriver
    public void initializePageObjects() {

        hcp = new HomeCataloguePage(BaseClass.driver);
        lp = new LoginPage(BaseClass.driver);
        uhp = new UserHomePage(BaseClass.driver);
    }

    // TC017 - Login with valid credentials
    @Given("the user is on the home page and clicks on Signup Login")
    public void the_user_is_on_the_home_page_and_clicks_on_Signup_Login() {

        initializePageObjects();
        hcp.signupLoginPageLink();
    }

    @When("the user enters a valid email address as {string}")
    public void the_user_enters_a_valid_email_address(String email) {

        lp.enterEmail(email);
    }

    @When("the user enters a valid password as {string}")
    public void the_user_enters_a_valid_password(String password) {

        lp.enterPassword(password);
    }

    // TC018 - Login with invalid credentials
    @When("the user enters invalid email address and password as below")
    public void user_enter_invalid_user_and_password(DataTable dataTable) {

        Map<String, String> list =
                dataTable.asMap(String.class, String.class);

        String email = list.get("email");
        String password = list.get("password");

        lp.enterEmail(email);
        lp.enterPassword(password);
    }

    // Common login button step
    @When("the user clicks the Login button")
    public void the_user_clicks_the_login_button() {

        lp.clickLoginButton();
    }

    // Common validation step for both scenarios
    @Then("the user should see {string}")
    public void the_user_should_see(String expectedMessage) {

        String actualMessage;

        if (expectedMessage.equalsIgnoreCase("Logged in as")) {

            actualMessage = uhp.userLoginValidation();

            if (actualMessage.contains(expectedMessage)) {
                Assert.assertTrue(true);
                uhp.userLogOut();
            } else {
                Assert.assertTrue(false);
            }

        } else if (expectedMessage.equalsIgnoreCase("incorrect!")) {

            actualMessage = lp.emailandPasswordValidation();

            if (actualMessage.contains(expectedMessage)) {
                Assert.assertTrue(true);

            } else if (actualMessage.contains("logged in as")) {
                Assert.assertTrue(false);
                uhp.userLogOut();
            }
        }
    }

}
