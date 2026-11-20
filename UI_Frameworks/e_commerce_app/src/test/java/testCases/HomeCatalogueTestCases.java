package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomeCataloguePage;
import pageObjects.ProductsPage;


public class HomeCatalogueTestCases extends BaseTest {
	
	@Test
	public void TC001_verifyHomePage() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		boolean status = hcp.getLogo();
		String textcatrgory = hcp.getTextcategory();
		List<WebElement> items = hcp.getFeatureItems();
		
		Assert.assertTrue(status, "status is not true");
		if(textcatrgory.equalsIgnoreCase(textcatrgory)) {
			Assert.assertTrue(true);
		}
		
		if (items.isEmpty()) {
			Assert.assertTrue(false);
		}else {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC002_verifyHomePageNavigationlinks() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		boolean status = hcp.verifyNavigationLinks();
		
		Assert.assertTrue(status,"Element not clickable");
	}
	
	@Test
	public void TC003_verifyAllProductsDisplayed() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		
		boolean status = hcp.verifyAllProducts();
		
		Assert.assertTrue(status,"Products not listed on the home page");
		
	}
	
	@Test
	public void TC004_TC005_verifyProductsInformation() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		
		hcp.listAllProducts();
		
	}
	
	@Test
	public void TC006_searchProductWithValidName() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.productPageLink();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.searchproduct("tshirt");
		pp.searchButton();
		
		List<WebElement> items = pp.productResults();
		verifyProductList(items);
		
		
		
	}
	
	@Test
	public void TC007_searchProductWithInvalidName() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.productPageLink();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.searchproduct("crocks");
		pp.searchButton();
		
		List<WebElement> items = pp.productResults();
		verifyProductList(items);
		
	}
	
	@Test
	public void TC008_searchProductWithEmptyInput() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.productPageLink();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.searchproduct("");
		pp.searchButton();
		
		List<WebElement> items = pp.productResults();
		verifyProductList(items);
		
	}
	
	@Test
	public void TC009_categoryBasedProductNavigation() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.productPageLink();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.clickWomenSubCategory("SAREE");
		List<WebElement> items = pp.productResults();
		verifyProductList(items);
		
	}
	
	@Test
	public void TC010_brandBasedProductFiltering() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.productPageLink();
		
		ProductsPage pp = new ProductsPage(driver);
		pp.varifyBrandBasedFiltering("Polo");
		List<WebElement> items = pp.productResults();
		verifyProductList(items);
		
	}
	
	@Test
	public void TC050_addRecommendedProductToCart() {
		
		HomeCataloguePage hcp = new HomeCataloguePage(driver);
		hcp.homePageLink();
		hcp.addRecommendedProductToCart("dress");
		
		ProductsPage pp = new ProductsPage(driver);
		pp.clickViewCart();
		
		CartPage cp = new CartPage(driver);
		int length = cp.verifyCartItems();
		
		if(length>0) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
