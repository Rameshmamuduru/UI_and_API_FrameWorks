package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//tr[contains(@id,'product')]") List<WebElement> cartItems;
	@FindBy(xpath="//tr[contains(@id,'product')]//i[contains(@class,'fa-times')]") List<WebElement> deleteItems;
	@FindBy(xpath="//a[@class='btn btn-default check_out']") WebElement checkOut;
	@FindBy(xpath="//p[normalize-space()='Register / Login account to proceed on checkout.']") WebElement guestCheckOutValidation;
	@FindBy(xpath="//a[normalize-space()='Place Order']") WebElement placeOrder;
	@FindBy(xpath="//button[@id='submit']") WebElement confirmOrder;
	@FindBy(xpath="//p[normalize-space()='Congratulations! Your order has been confirmed!']") WebElement orderConfirmation;
	
	//p[normalize-space()='Congratulations! Your order has been confirmed!']
	//button[@id='submit']
	//a[@class='btn btn-default check_out']
	
	public String orderConfirmationMessage() {
		wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
		String message = orderConfirmation.getText();
		return message;
	}
	public void confirmOrder() {
		wait.until(ExpectedConditions.visibilityOf(confirmOrder));
		wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));
		confirmOrder.click();
	}
	
	public void order() {
		wait.until(ExpectedConditions.visibilityOf(placeOrder));
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		placeOrder.click();
	}
	public String guestCheckOutValidation() {
		String message = wait.until(ExpectedConditions.visibilityOf(guestCheckOutValidation)).getText();
		return message;
	}
	public void checkOut() {
		wait.until(ExpectedConditions.elementToBeClickable(checkOut)).click();
	}
	public int verifyCartItems() {
		wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
		int size = cartItems.size();
		return size;
	}
	
	public void removeOneProductFromCart(int id) {
		WebElement item = deleteItems.get(id);
		wait.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
	}
	
	public void removeAllProductsFromCart() {
		for (WebElement item: deleteItems) {
			wait.until(ExpectedConditions.elementToBeClickable(item));
			item.click();
		}
	}

}
