package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static WebDriver driver;
	public static Properties p;
	public static WebDriverWait wait;
	public static Logger logger;
	
	@Before
	public void setup() throws IOException {
		
		
		p = BaseClass.getProperties();
		driver = BaseClass.initilizeDriver();
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		logger = BaseClass.getLogger(); 
	}
	
	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}
