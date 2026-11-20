package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeCataloguePage extends BasePage {
	
	//constructor
	
	public HomeCataloguePage(WebDriver driver) {
		super(driver);
	}
	
	//web Elements
	@FindBy(xpath="//img[@alt='Website for automation practice']") WebElement logo;
	@FindBy(xpath="//h2[normalize-space()='Category']") WebElement categories;
	@FindBy(xpath="//h2[normalize-space()='Features Items']") WebElement textFeatureItems;
	@FindBy(xpath="//div[@class='features_items']") List<WebElement> featureItems;
	@FindBy(xpath="//a") List<WebElement> navigationLinks;
	@FindBy(xpath="//a[normalize-space()='Home']") WebElement homePageLink;
	@FindBy(xpath="//a[@href='/products']") WebElement productPageLink;
	@FindBy(xpath="/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]") WebElement cartPageLink;
	@FindBy(xpath="//a[normalize-space()='Signup / Login']") WebElement signupLoginPageLink;
	@FindBy(xpath="//a[normalize-space()='Contact us']") WebElement contactPageLink;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']") List<WebElement> navLinks;
	
	@FindBy(xpath="//div[@class='recommended_items']") List<WebElement> recommendedProducts;
	@FindBy(xpath="//div[@class='item active']//div[1]//div[1]//div[1]//div[1]//a[1]") WebElement addToCart;
	@FindBy(xpath="//div[@class='modal-content']//u[normalize-space()='View Cart']") WebElement guestViewCartPopup;
	@FindBy(xpath="//button[contains(@class,'close-modal') and contains(normalize-space(),'Continue Shopping')]") WebElement guestContinueShopingPopup;
	@FindBy(xpath="//a[normalize-space()='Cart']") WebElement guestCart;
	
			
	
		
	
	//actions Methods
	public void gotoGuestCart() {
		wait.until(ExpectedConditions.elementToBeClickable(guestCart)).click();
	}
	public void guestContinueShopingPopup() {
		wait.until(ExpectedConditions.visibilityOf(guestContinueShopingPopup));
		wait.until(ExpectedConditions.elementToBeClickable(guestContinueShopingPopup));
		guestContinueShopingPopup.click();
	}
 	public void guestViewCartPopup() {
		wait.until(ExpectedConditions.visibilityOf(guestViewCartPopup));

	    wait.until(ExpectedConditions.elementToBeClickable(guestViewCartPopup));

	    guestViewCartPopup.click();
	}
	public boolean getLogo() {
		boolean status = logo.isDisplayed();
		return status;
	}
	
	public String getTextcategory() {
		return categories.getText();
	}
	
	public String getTextFeatureItems() {
		return textFeatureItems.getText();
	}
	
	public List<WebElement> getFeatureItems() {
		List<WebElement> Items = featureItems;
		return Items;
	}
	
	public boolean verifyNavigationLinks() {
		
		for (WebElement link: navLinks) {
			
			String linkName = link.getText();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(link));
				System.out.println(linkName + " - Clickable");
			}catch(Exception e) {
				System.out.println(linkName + " - Not Clickable");
				return false;
			}
		}
		
		return true;

	}
	
	public boolean verifyAllProducts() {
		
		List<WebElement> Items = featureItems;
		
		for (WebElement item: Items) {
			
			String productName = item.getText();
			
			try {
				wait.until(ExpectedConditions.elementToBeClickable(item));
				System.out.println(productName + " - Product Displayed");
			}catch(Exception e) {
				System.out.println(productName + " - product not displayed");
				return false;
			}		
		}
		
		return true;
		
	}
	
	public void listAllProducts() {
		List<WebElement> Items = featureItems;
		for (WebElement item: Items) {
			System.out.println(item);
		}
	}
	
	
	public void productPageLink() {
		wait.until(ExpectedConditions.elementToBeClickable(productPageLink)).click();
	}
	
	public void homePageLink() {
		wait.until(ExpectedConditions.elementToBeClickable(homePageLink)).click();
	}
	
	public void signupLoginPageLink() {
		wait.until(ExpectedConditions.elementToBeClickable(signupLoginPageLink)).click();
	}
	
	public void addRecommendedProductToCart(String productName) {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(recommendedProducts));
		
		for(WebElement recommendedProduct: recommendedProducts) {
			if(recommendedProduct.getText().toLowerCase().contains(productName)) {
				wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
				
			}
		}
		
	}
	
	
	
	

}
