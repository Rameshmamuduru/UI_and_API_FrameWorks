package testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utilities.LoggerUtils;

public class BaseTest {

    public static Logger logger;
    public static RequestLoggingFilter requestLoggingFilter;
    public static ResponseLoggingFilter responseLoggingFilter;
    public static Properties properties;

    @BeforeClass (groups = {"sanity","smoke","datadriven","auth"})
    public void setup() throws IOException {

        logger = LoggerUtils.getLogger(this.getClass());

        logger.info("Test setup started");
        
        
        // Create log file
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log", true);

        PrintStream log = new PrintStream(fos, true);

        // Setup request logging filter
        requestLoggingFilter = new RequestLoggingFilter(log);

        // Setup response logging filter
        responseLoggingFilter = new ResponseLoggingFilter(log);

        // Register filters globally
        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
        
        
        
        // Config File Reader
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
        properties = new Properties();
        properties.load(file);
        
        RestAssured.baseURI = Routes.BASE_URL;

    }
}