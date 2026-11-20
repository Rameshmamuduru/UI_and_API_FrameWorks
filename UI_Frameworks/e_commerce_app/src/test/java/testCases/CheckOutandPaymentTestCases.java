package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomeCataloguePage;
import pageObjects.LoginPage;
import pageObjects.PaymentPage;
import pageObjects.UserHomePage;

public class CheckOutandPaymentTestCases extends BaseTest{
	
	@Test (groups = {"funtional"})
	public void TC033_CheckOutAsGuest() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
	    UserHomePage uhp = new UserHomePage(driver);
	    CartPage cp = new CartPage(driver);

	    hcp.productPageLink();

	    uhp.addToCart(2, 1);

	    hcp.gotoGuestCart();

	    cp.checkOut();

	    String validationMessage = cp.guestCheckOutValidation();

	    Assert.assertEquals(validationMessage,
	            "Register / Login account to proceed on checkout.");
		
	}
	
	@Test (groups = {"smoke", "regression", "funtional"})
	public void TC042_verifySuccessfullOrderPlacement() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		UserHomePage uhp = new UserHomePage(driver);
		CartPage cp = new CartPage(driver);
		PaymentPage pp = new PaymentPage(driver);

		hcp.signupLoginPageLink();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(properties.getProperty("validemail"));
		lp.enterPassword(properties.getProperty("validpassword"));
		lp.clickLoginButton();
		uhp.addToCart(4, 1);
		uhp.gotoUserCart();
		cp.checkOut();
		cp.order();	
		pp.enterPaymentDetails("Ramesh", "95389358993953", "321", "07", "2030");
		cp.confirmOrder();
		cp.orderConfirmationMessage();
	
		
	}

}
