package pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
	
	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='search_product']") WebElement searchProducts;
	@FindBy(xpath="//button[@id='submit_search']") WebElement searchButton;
	@FindBy(xpath="//div[@class='features_items']") List<WebElement> products;
	
	@FindBy(xpath="//a[normalize-space()='Women']") WebElement womenCategory;
	@FindBy(xpath="//div[@id='Women']//a") List<WebElement> womenCategorySubOptions;
	@FindBy(xpath="//a[normalize-space()='Men']") WebElement menCategory;
	@FindBy(xpath="//div[@id='Men']//a") WebElement menCategorySubOptions;
	@FindBy(xpath="//a[normalize-space()='Kids']") WebElement kidsCategory;
	@FindBy(xpath="//div[@id='Kids']//a") WebElement kidsCategorySubOptions;
	@FindBy(xpath="//div[@class='brands-name']//ul/li/a") List<WebElement> brandNames;
	
	@FindBy(xpath="//p[@class='text-center']//a") WebElement viewCartPopUp;
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']") WebElement continueShopingPopUp;
	
	//p[@class='text-center']//a
	
	
	
	public void searchproduct(String product) {
		searchProducts.sendKeys(product);
	}
	
	public void searchButton() {
		searchButton.click();
	}
	
	public List<WebElement> productResults() {
		List<WebElement> items = products;
		return items;
	}
	
	public void clickWomenSubCategory(String categoryName) {

	    // Expand Women category
	    wait.until(ExpectedConditions.elementToBeClickable(womenCategory)).click();
	    // Wait until subcategories are visible
	    wait.until(ExpectedConditions.visibilityOfAllElements(womenCategorySubOptions));
	    // Check each subcategory
	    for (WebElement option : womenCategorySubOptions) {
	        String actualName = option.getText().trim();
	        System.out.println("Subcategory: [" + actualName + "]");
	        if (actualName.equalsIgnoreCase(categoryName)) {
	            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	            return;
	        }
	    }
	    // Execute only after checking all elements
	    throw new NoSuchElementException("Subcategory not found: " + categoryName);
	}
	
	public void varifyBrandBasedFiltering(String name) {
		
		
		wait.until(ExpectedConditions.visibilityOfAllElements(brandNames));

	    System.out.println("Total brands: " + brandNames.size());

	    for (WebElement brand : brandNames) {

	        String actualName = brand.getText().trim();

	        System.out.println("Available brand: [" + actualName + "]");

	        if (actualName.toLowerCase().contains(name.toLowerCase())) {

	            wait.until(ExpectedConditions.elementToBeClickable(brand)).click();

	            return;
	        }
	    }

	    throw new NoSuchElementException("Brand not found: " + name);
		
	}
	
	public void clickViewCart() {
		wait.until(ExpectedConditions.elementToBeClickable(viewCartPopUp)).click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
