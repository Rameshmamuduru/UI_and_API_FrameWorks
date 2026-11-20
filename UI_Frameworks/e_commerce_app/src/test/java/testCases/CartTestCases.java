package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomeCataloguePage;
import pageObjects.LoginPage;
import pageObjects.UserHomePage;

public class CartTestCases extends BaseTest {
	
	@Test (groups = {"smoke", "regression", "funtional"})
	public void TC024_addOneproductToCart() {
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		LoginPage lp = new LoginPage(driver);
		CartPage cp = new CartPage(driver);
		
		UserHomePage uhp = new UserHomePage(driver);
		hcp.signupLoginPageLink();
		lp.enterEmail(properties.getProperty("validemail"));
		lp.enterPassword(properties.getProperty("validpassword"));
		lp.clickLoginButton();
		uhp.addToCart(2, 1);
		uhp.userLogOut();
		
	}
	
	@Test (groups = {"funtional"})
		public void TC025_addMultipleproductsToCart() {
			HomeCataloguePage hcp = new HomeCataloguePage(driver);
			LoginPage lp = new LoginPage(driver);
			UserHomePage uhp = new UserHomePage(driver);
			hcp.signupLoginPageLink();
			lp.enterEmail(properties.getProperty("validemail"));
			lp.enterPassword(properties.getProperty("validpassword"));
			lp.clickLoginButton();
			uhp.addToCart(2, 1);
			uhp.addToCart(4, 1);
			uhp.userLogOut();
			
		}
		
		@Test (groups = {"funtional"})
		public void TC026_addOneproductMutipleTimesToCart() {
			HomeCataloguePage hcp = new HomeCataloguePage(driver);
			LoginPage lp = new LoginPage(driver);
			UserHomePage uhp = new UserHomePage(driver);
			hcp.signupLoginPageLink();
			lp.enterEmail(properties.getProperty("validemail"));
			lp.enterPassword(properties.getProperty("validpassword"));
			lp.clickLoginButton();
			uhp.addToCart(2, 3);
			uhp.userLogOut();
			
		}
		
		@Test (groups = {"funtional"})
		public void TC030_removeOneproductFromCart() {
			HomeCataloguePage hcp = new HomeCataloguePage(driver);
			LoginPage lp = new LoginPage(driver);
			UserHomePage uhp = new UserHomePage(driver);
			CartPage cp = new CartPage(driver);
			hcp.signupLoginPageLink();
			lp.enterEmail(properties.getProperty("validemail"));
			lp.enterPassword(properties.getProperty("validpassword"));
			lp.clickLoginButton();
			uhp.userCart();
			cp.removeOneProductFromCart(0);
			uhp.userLogOut();
			
		}
		
		@Test (groups = {"funtional"})
		public void TC031_removeAllproductsFromCart() {
			HomeCataloguePage hcp = new HomeCataloguePage(driver);
			LoginPage lp = new LoginPage(driver);
			UserHomePage uhp = new UserHomePage(driver);
			CartPage cp = new CartPage(driver);
			hcp.signupLoginPageLink();
			lp.enterEmail(properties.getProperty("validemail"));
			lp.enterPassword(properties.getProperty("validpassword"));
			lp.clickLoginButton();
			uhp.userCart();
			cp.removeAllProductsFromCart();
			uhp.userLogOut();
			
		}
		
		

}
