package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomeCataloguePage;
import pageObjects.LoginPage;
import pageObjects.UserHomePage;

public class UserLoginTestCases extends BaseTest {
	
	@Test (groups = {"smoke", "regression", "funtional"})
	public void TC017_userLoginWithValidCredentials() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("valaidemail"));
		lp.enterPassword(properties.getProperty("validpassword"));
		lp.clickLoginButton();
		
		UserHomePage uhp = new UserHomePage(driver);
		String validationMessage = uhp.userLoginValidation();
		if (validationMessage.equalsIgnoreCase("Logged in as RameshTest")) {
			Assert.assertTrue(true);
			uhp.userLogOut();
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups = {"funtional"})
	public void TC018_userLoginWithInvalidPassword() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("email"));
		lp.enterPassword("test123");
		lp.clickLoginButton();
		
		String message = lp.emailandPasswordValidation();
		if(message.equalsIgnoreCase("Your email or password is incorrect!")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups = {"funtional"})
	public void TC019_userLoginWithUnregistredEmail() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail("rameshrams@gmail.com");
		lp.enterPassword("test123");
		lp.clickLoginButton();
		
		String message = lp.emailandPasswordValidation();
		if(message.equalsIgnoreCase("Your email or password is incorrect!")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups = {"funtional"})
	public void TC020_userLoginWithEmptyCredentials() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.clickLoginButton();
		String actualMessage = lp.getEmailFieldValidation();
		if (actualMessage.equalsIgnoreCase("Please fill out this field.")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test(groups = {"smoke", "regression", "funtional"})
	public void TC020_userLogOutFunctionality() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("email"));
		lp.enterPassword(properties.getProperty("password"));
		lp.clickLoginButton();
		
		UserHomePage uhp = new UserHomePage(driver);
		uhp.userLogOut();
	}
	
	@Test (groups = {"regression", "funtional"})
	public void TC022_userAccountDeletion() {
		UserHomePage uhp = new UserHomePage(driver);
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("email"));
		lp.enterPassword(properties.getProperty("password"));
		lp.clickLoginButton();
		
		uhp.userDeleteAccount();
		String actualDeleteValidationMessage = uhp.accountDeletedValidation();
		if(actualDeleteValidationMessage.equalsIgnoreCase("Account Deleted!")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups = {"funtional"})
	public void TC023_verifyloginAfterAccountDeletion() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("email"));
		lp.enterPassword(properties.getProperty("password"));
		lp.clickLoginButton();
		
		String message = lp.emailandPasswordValidation();
		if (message.equalsIgnoreCase("Your email or password is incorrect!")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}

}
