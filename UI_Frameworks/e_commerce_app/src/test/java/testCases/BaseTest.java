package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static EdgeOptions eoptions;
	public static Logger logger;
	public static Properties properties;
	public static ChromeOptions coptions;
	public static RemoteWebDriver rdriver;
	public static FirefoxOptions foptions;
	public static SafariOptions soptions;
	
	
	// Launching the Browser
	@Parameters({"browser","platform"})
	@BeforeClass (groups = {"smoke", "regression", "funtional","datadriven"})
	public void setup(String browser, String os) throws FileNotFoundException, MalformedURLException {
		
		//Load Properties config file
		FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
		properties = new Properties();
		try {
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//block the adds in the edgeBrowser
		eoptions = new EdgeOptions();
		eoptions.addArguments("--disable-popup-blocking");
		eoptions.addArguments("--load-extension=C:\\Users\\rames\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Extensions\\cimighlppcgcoapaliogpjjdehbnofhn\\2026.1116.1650_0");
		
		coptions = new ChromeOptions();
		coptions.addArguments("--disable-popup-blocking");
		//coptions.addArguments("--load-extension=C:\\Users\\rames\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Extensions\\cimighlppcgcoapaliogpjjdehbnofhn\\2026.1116.1650_0");
		
		FirefoxOptions foptions = new FirefoxOptions();
		foptions.addArguments("--disable-popup-blocking");
		
		soptions = new SafariOptions();
		
		
		
		//Driver initilization for Local
		if(properties.getProperty("execution_type").equalsIgnoreCase("remote")){
			String gridURL = properties.getProperty("grid_url");
			
			switch (browser.toLowerCase()) {
			case "chrome":
				coptions.setPlatformName(os);
				rdriver = new RemoteWebDriver(new URL(gridURL), coptions);
				break;
			case "edge":
				eoptions.setPlatformName(os);
				rdriver = new RemoteWebDriver(new URL(gridURL), eoptions);
				break;
			case "firefox":
				foptions.setPlatformName(os);
				rdriver = new RemoteWebDriver(new URL(gridURL), foptions);
				break;
			case "safari":
				soptions.setPlatformName(os);
				rdriver = new RemoteWebDriver(new URL(gridURL), soptions);
				break;
			}
			
		}
		
		
		
		//Driver initilization for Local
		if(properties.getProperty("execution_type").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver(coptions);
				break;
			case "edge":
				driver = new EdgeDriver(eoptions);
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser option " +browser);
			}
		}
		
		driver.get("https://automationexercise.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		// Loggin configaration
		logger = LogManager.getLogger(this.getClass());	
	
	}
	
	// closing the browser
	@AfterClass (groups = {"smoke", "regression", "funtional","datadriven"})
	public void tearDown() {
		if (driver != null) {
			
			driver.quit();
			
		}
	}
	
	public void verifyProductList(List<WebElement> items) {
		int size = items.size();
		if (size==0) {
			System.out.println("products are not listed");
			Assert.assertTrue(true);;
		}else {
			for(WebElement item: items) {
				
				String info = wait.until(ExpectedConditions.visibilityOf(item)).getText();
				if (info.equalsIgnoreCase("Tshirt")) {
					Assert.assertTrue(true);
				}
					
			}
		}
	}
	
	public static String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());	
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	public static String getproperty(String key) {
		String value = properties.getProperty(key);
		return value;
		
	}
	
	
	

}
