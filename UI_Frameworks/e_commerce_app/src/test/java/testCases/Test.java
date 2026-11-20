package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Test {
	
	public WebDriver driver;

	public static void main(String[] args) {
		
		
		WebDriver driver = new EdgeDriver(); 
		 driver.get("https://automationexercise.com/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		 
		 List<WebElement> items = driver.findElements(
				    By.xpath("//div[@class='features_items']")
				);
		 
		 int size = items.size();
		 for(WebElement item: items) {
			 System.out.println(item.getText());
		 }
		 
		 

	}

}
