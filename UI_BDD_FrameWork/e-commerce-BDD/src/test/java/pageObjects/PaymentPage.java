package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage {
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='name_on_card']") WebElement cardName;
	@FindBy(xpath="//input[@name='card_number']") WebElement cardNumber;
	@FindBy(xpath="//input[@placeholder='ex. 311']") WebElement cvv;
	@FindBy(xpath="//input[@placeholder='MM']") WebElement expiryMonth;
	@FindBy(xpath="//input[@placeholder='YYYY']") WebElement expiryYear;

	public void enterPaymentDetails(String name, String cardNo, String cvvNo,
            String month, String year) {

		wait.until(ExpectedConditions.visibilityOf(cardName)).sendKeys(name);
		
		wait.until(ExpectedConditions.visibilityOf(cardNumber)).sendKeys(cardNo);
		
		wait.until(ExpectedConditions.visibilityOf(cvv)).sendKeys(cvvNo);
		
		wait.until(ExpectedConditions.visibilityOf(expiryMonth)).sendKeys(month);
		
		wait.until(ExpectedConditions.visibilityOf(expiryYear)).sendKeys(year);
		}
			
}
