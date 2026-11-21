
package base;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

    public static Properties p;
    public static WebDriver driver;

    public static WebDriver initilizeDriver() throws IOException {

        p = getProperties();

        String execution_type = p.getProperty("execution_type");
        String platform = p.getProperty("platform");
        String browser = p.getProperty("browser");

        if (execution_type == null || browser == null) {
            throw new IllegalArgumentException(
                    "Please configure execution_type and browser in config.properties");
        }

        if (execution_type.equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Platform configuration
            switch (platform.toLowerCase()) {

                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;

                case "unix":
                    capabilities.setPlatform(Platform.UNIX);
                    break;

                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported platform: " + platform);
            }

            // Browser configuration
            switch (browser.toLowerCase()) {

                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;

                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;

                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser);
            }

            driver = new RemoteWebDriver(
                    new URL(p.getProperty("gridurl")),
                    capabilities);

        } else if (execution_type.equalsIgnoreCase("local")) {

            // Local browser execution
            switch (browser.toLowerCase()) {

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser);
            }

        } else {
            throw new IllegalArgumentException(
                    "Unsupported execution type: " + execution_type);
        }

        driver.manage().window().maximize();

        return driver;
    }
    

    public static Properties getProperties() throws IOException {
    	
        Properties properties = new Properties();
        try (FileReader file = new FileReader("src/test/resources/config.properties")) 
        {
            properties.load(file);
        }
        return properties;
    }
    
    public static Logger getLogger() {
    	Logger logger = LogManager.getLogger(BaseClass.class);
    	return logger;
    }
}
