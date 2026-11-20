
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomeCataloguePage;
import pageObjects.LoginPage;
import pageObjects.UserHomePage;

public class LoginDataDrivenTestCases extends BaseTest {

    @Test(dataProvider = "LoginData",
          dataProviderClass = utilities.DataProviders.class,
          groups = {"datadriven"})
    public void userDataDrivenLoginTests(String email, String Password, String testtype) {

        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        LoginPage lp = new LoginPage(driver);
        lp.enterEmail(email);
        lp.enterPassword(Password);
        lp.clickLoginButton();

        UserHomePage uhp = new UserHomePage(driver);
        //String validationMessage = uhp.userLoginValidation();

        String testType = testtype;

        if (testType.equalsIgnoreCase("valid")) {
        	String validationMessage = uhp.userLoginValidation();
            if (validationMessage.contains("Logged in as")) {

                Assert.assertTrue(true, "Valid user logged in successfully");
                uhp.userLogOut();

            } else {

                Assert.fail("Valid user login failed");
            }

        } else if (testType.equalsIgnoreCase("invalid")) {
        	String validationMessage = lp.emailandPasswordValidation();
            if (validationMessage.contains("incorrect!")) {
            	//Assert.assertTrue(false);
            	Assert.assertTrue(true);
            	System.out.println("Invalid user should not be logged in");
            } else {
            	String userHomeMessage = uhp.userLoginValidation();
                if(userHomeMessage.contains("Logged in as")) {
                	Assert.fail("Invalid user was logged in");
                	uhp.userLogOut();
                }
            }
        }
        
    }
}
