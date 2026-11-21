package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Name']") WebElement InputName;
	@FindBy(xpath="//input[@data-qa='signup-email']") WebElement InputEmail;
	@FindBy(xpath="//button[normalize-space()='Signup']") WebElement signUpButton;
	@FindBy(xpath="//input[@id='id_gender1']") WebElement mrTitle;
	@FindBy(xpath="//div[@id='uniform-id_gender2']") WebElement mrsTitle;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	
	@FindBy(xpath="//select[@id='days']") WebElement bDay;
	@FindBy(xpath="//select[@id='months']") WebElement bMonth;
	@FindBy(xpath="//select[@id='years']") WebElement bYear;
	
	@FindBy(xpath="//input[@id='newsletter']") WebElement checkNewsLetter;
	@FindBy(xpath="//input[@id='optin']") WebElement checkSpecialOffer;
	
	//Address information
	
	@FindBy(xpath="//input[@id='address2']") WebElement address2;
	@FindBy(xpath="//input[@id='first_name']") WebElement firstName;
	@FindBy(xpath="//input[@id='last_name']") WebElement lastName;
	@FindBy(xpath="//input[@id='company']") WebElement company;
	@FindBy(xpath="//input[@id='address1']") WebElement address1;
	@FindBy(xpath="//select[@id='country']") WebElement country;
	@FindBy(xpath="//input[@id='state']") WebElement state;
	@FindBy(xpath="//input[@id='city']") WebElement city;
	@FindBy(xpath="//input[@id='zipcode']") WebElement zipCode;
	@FindBy(xpath="//input[@id='mobile_number']") WebElement mobileNumber;
	@FindBy(xpath="//button[normalize-space()='Create Account']") WebElement buttonCreateAccount;
	@FindBy(xpath="//b[normalize-space()='Account Created!']") WebElement accountCreatedMessage;
	@FindBy(xpath="//a[@class='btn btn-primary']") WebElement clickContinueAfterAccountCreation;
	@FindBy(xpath="//p[normalize-space()='Email Address already exist!']") WebElement emailAlreadyExists;
	
	public String emailAlreadyExists() {
		String message = emailAlreadyExists.getText();
		return message;
	}
	public void clickContinueAfterAccountCreation() {
		clickContinueAfterAccountCreation.click();
	}
	public String accountCreatedMessage() {
		String message = accountCreatedMessage.getText();
		return message;
	}
	public void clickCreateAccount() {
		buttonCreateAccount.click();
	}
	public void enterstae(String sname) {
		state.sendKeys(sname);
	}
	public void city(String cname) {
		city.sendKeys(cname);
	}
	
	public void zipCode(String code) {
		zipCode.sendKeys(code);
	}
	public void enterMobileNumber(String mobile) {
		mobileNumber.sendKeys(mobile);
	}
	
	public void enterFirstName(String fname) {
		firstName.sendKeys(fname);
	}
	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void enterCompany(String cname) {
		company.sendKeys(cname);
	}
	public void enterAdress1(String addrs1) {
		address1.sendKeys(addrs1);
	}
	public void enterAdress2(String addrs2) {
		address2.sendKeys(addrs2);
	}
	public void country(String cntry) {
		Select countrySelect = new Select(country);
		countrySelect.selectByVisibleText(cntry);
	}
	
	
	public void enterUserName(String name) {
		InputName.sendKeys(name);
	}
	public String UserNameFieldValidation() {
		String message = InputName.getAttribute("validationMessage");
		return message;
	}
	public void enterEmail(String email) {
		InputEmail.sendKeys(email);
	}
	public String emailFieldValidation() {
		String message = InputEmail.getAttribute("validationMessage");
		return message;
	}
	public void clickSignUpButton() {
		signUpButton.click();
	}
	public void mrTitle() {
		mrTitle.click();
	}
	public void mrsTitle() {
		mrsTitle.click();
	}
	public void password(String email) {
		password.sendKeys(email);
	}
	public void daySelector(String day) {
		Select selectday = new Select(bDay);
		selectday.selectByVisibleText(day);
	}
	
	public void monthSelector(String month) {
		Select selectmonth = new Select(bMonth);
		selectmonth.selectByVisibleText(month);
	}
	
	public void yearSelector(String year) {
		Select selectyear = new Select(bYear);
		selectyear.selectByVisibleText(year);
	}
	
	public void checkNewsLetter() {
		checkNewsLetter.click();
	}
	
	public void checkSpecialOffer() {
		checkSpecialOffer.click();
	}
	

}
