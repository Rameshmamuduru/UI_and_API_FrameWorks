
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomeCataloguePage;
import pageObjects.RegistrationPage;
import pageObjects.UserHomePage;

public class UserRegistrationTestCases extends BaseTest {

    @Test(groups = {"smoke", "regression", "funtional"})
    public void TC011_userRegistrationWithValidDetails() {

        UserHomePage uhp = new UserHomePage(driver);
        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        RegistrationPage rp = new RegistrationPage(driver);
        rp.enterUserName("Ramesh-Test");
        rp.enterEmail("tesameshsttest@gmail.com");
        rp.clickSignUpButton();

        rp.mrTitle();
        rp.password("Password@123");
        rp.enterFirstName("Ramesh-Test");
        rp.enterLastName("Mamuduru");
        rp.daySelector("18");
        rp.monthSelector("June");
        rp.yearSelector("1999");
        rp.enterAdress1("Podalakuru");
        rp.country("India");
        rp.enterstae("Andhra Pradesh");
        rp.city("Nellore");
        rp.zipCode("524345");
        rp.enterMobileNumber("9485035953");
        rp.clickCreateAccount();

        // Assertions
        String actualMessage = rp.accountCreatedMessage();

        if (actualMessage.equalsIgnoreCase("Account Created!")) {
            Assert.assertTrue(true);
            rp.clickContinueAfterAccountCreation();
            uhp.userDeleteAccount();
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test (groups = { "funtional"})
    public void TC012_userSignUpWithMissedEmailID() {

        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        RegistrationPage rp = new RegistrationPage(driver);
        rp.enterUserName("Ramesh M");
        rp.clickSignUpButton();

        String emailFieldValidationMessage = rp.emailFieldValidation();

        if (emailFieldValidationMessage.equalsIgnoreCase("Please fill out this field.")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(groups = {"funtional"})
    public void TC013_userSignUpWithMissedName() {

        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        RegistrationPage rp = new RegistrationPage(driver);
        rp.enterEmail("testrameshqaqateststage@gmail.com");
        rp.clickSignUpButton();

        String nameFieldlValidationMessage = rp.UserNameFieldValidation();

        if (nameFieldlValidationMessage.equalsIgnoreCase("Please fill out this field.")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(groups = {"funtional"})
    public void TC014_userRegistrationWithMissedOptionalFields() {

        UserHomePage uhp = new UserHomePage(driver);
        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        RegistrationPage rp = new RegistrationPage(driver);
        rp.enterUserName("Ramesh-Test");
        rp.enterEmail("tesahsttest@gmail.com");
        rp.clickSignUpButton();

        // rp.mrTitle();
        rp.password("Password@123");
        rp.enterFirstName("Ramesh-Test");
        rp.enterLastName("Mamuduru");
        // rp.daySelector("18");
        // rp.monthSelector("June");
        // rp.yearSelector("1999");
        rp.enterAdress1("Podalakuru");
        rp.country("India");
        rp.enterstae("Andhra Pradesh");
        rp.city("Nellore");
        rp.zipCode("524345");
        rp.enterMobileNumber("9485035953");
        rp.clickCreateAccount();

        // Assertions
        String actualMessage = rp.accountCreatedMessage();

        if (actualMessage.equalsIgnoreCase("Account Created!")) {
            Assert.assertTrue(true);
            rp.clickContinueAfterAccountCreation();
            uhp.userDeleteAccount();
        } else {
            Assert.assertTrue(false);
        }
    }
    
  @Test (groups = {"funtional"})
    public void TC016_userRegisterWithExsistingEmail() {

        HomeCataloguePage hcp = new HomeCataloguePage(driver);
        hcp.signupLoginPageLink();

        RegistrationPage rp = new RegistrationPage(driver);
        rp.enterUserName("Ramesh");
        rp.enterEmail("test@gmail.com");
        rp.clickSignUpButton();
        
        String message = rp.emailAlreadyExists();
        if (message.equalsIgnoreCase("Email Address already exist!")) {
        	Assert.assertTrue(true);
        }else {
        	Assert.assertTrue(false);
        }

  
    }
    
}
