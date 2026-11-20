package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@data-qa='login-email']") WebElement enterEmail;
	@FindBy(xpath="//input[@placeholder='Password']") WebElement enterPassword;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement loginButton;
	@FindBy(xpath="//p[normalize-space()='Your email or password is incorrect!']") WebElement emailandPasswordValidation;
	
	
	
	public String getEmailFieldValidation() {
		String message = enterEmail.getAttribute("validationMessage");
		return message;
		
	}
	public String getPasswordFieldValidation() {
		String message = enterPassword.getAttribute("validationMessage");
		return message;
			
	}
	public String emailandPasswordValidation() {
		String message = emailandPasswordValidation.getText();
		return message;
	}
	public void enterEmail(String email) {
		enterEmail.sendKeys(email);
	}
	
	public void enterPassword(String pass) {
		enterPassword.sendKeys(pass);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}

}
