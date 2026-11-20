package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserHomePage extends BasePage{
	
	public UserHomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement logOut;
	@FindBy(xpath="//a[normalize-space()='Delete Account']") WebElement deleteAccount;
	@FindBy(xpath="//li[10]//a[1]") WebElement userLoginValidation;
	@FindBy(xpath="//b[normalize-space()='Account Deleted!']") WebElement accountDeletedValidation;
	@FindBy(xpath="/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]") WebElement userCart;
	@FindBy(xpath="//div[@class='features_items']") List<WebElement> featureItems;
	@FindBy(xpath=".//a[contains(@class,'add-to-cart')]") List<WebElement> addToCart;
	@FindBy(xpath="//p[@class='text-center']//a") WebElement viewCartPopup;
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']") WebElement continueShopingPopup;
	@FindBy(xpath="//a[normalize-space()='Cart']") WebElement Cart;
	
	
	public void gotoUserCart() {
		wait.until(ExpectedConditions.visibilityOf(userCart));
		wait.until(ExpectedConditions.elementToBeClickable(userCart));
		userCart.click();
	}
	public void addToCart(int id, int size) {
		for (int i = 0; i < size; i++) {

	        WebElement element = addToCart.get(id);

	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

	        // Wait for the Added to Cart popup
	        wait.until(ExpectedConditions.visibilityOf(continueShopingPopup));

	        // Close the popup
	        wait.until(ExpectedConditions.elementToBeClickable(
	                continueShopingPopup)).click();
	    }
	}
	
	public void viewCartPopup() {
		wait.until(ExpectedConditions.visibilityOf(viewCartPopup));
		viewCartPopup.click();
	}
	
	public void userLogOut() {
		logOut.click();
	}
	
	public void userDeleteAccount() {
		deleteAccount.click();
	}
	
	public String userLoginValidation() {
		String message = userLoginValidation.getText();
		return message;
	}
	
	public String accountDeletedValidation() {
		String message = accountDeletedValidation.getText();
		return message;
	}
	
	public void userCart() {
		wait.until(ExpectedConditions.visibilityOf(userCart)).click();;
		
	}
	

}
