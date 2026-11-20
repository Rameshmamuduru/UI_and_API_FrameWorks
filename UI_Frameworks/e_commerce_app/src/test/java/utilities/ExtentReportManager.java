
package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseTest;

public class ExtentReportManager implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(ExtentReportManager.class);

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;

    // ThreadLocal supports separate ExtentTest instances per thread
    private static final ThreadLocal<ExtentTest> testThread =
            new ThreadLocal<>();

    private String repName;
    private File reportFile;

    // Initialize Extent Report
    @Override
    public void onStart(ITestContext testContext) {

        logger.info("Test suite execution started");

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        File reportDirectory = new File("./reports");

        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        reportFile = new File(reportDirectory, repName);

        sparkReporter = new ExtentSparkReporter(reportFile);

        sparkReporter.config()
                .setDocumentTitle("Automation Test Report");

        sparkReporter.config()
                .setReportName("Functional Testing");

        sparkReporter.config()
                .setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Application", "Automation Exercise");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest()
                .getParameter("os");

        String browser = testContext.getCurrentXmlTest()
                .getParameter("browser");

        extent.setSystemInfo("Operating System",
                os != null ? os : System.getProperty("os.name"));

        extent.setSystemInfo("Browser",
                browser != null ? browser : "Edge");

        List<String> groups =
                testContext.getCurrentXmlTest().getIncludedGroups();

        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }

        logger.info("Extent report initialized successfully");
    }

    // Test Started
    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getTestClass().getName()
                + " - " + result.getMethod().getMethodName();

        ExtentTest test = extent.createTest(testName);

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.INFO, "Test execution started");

        testThread.set(test);

        logger.info("TEST STARTED: {}", testName);
    }

    // Test Passed
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = testThread.get();

        if (test != null) {

            test.log(Status.PASS, "Test passed successfully");

        }

        logger.info("TEST PASSED: {}", result.getName());

        testThread.remove();
    }

    // Test Failed
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testThread.get();

        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
            testThread.set(test);
        }

        test.log(Status.FAIL, "Test execution failed");

        // Capture complete exception details
        if (result.getThrowable() != null) {

            test.log(Status.FAIL, result.getThrowable());

            logger.error("TEST FAILED: {}", result.getName(),
                    result.getThrowable());
        }

        // Capture Screenshot
        try {

            String screenshotPath =
                    BaseTest.captureScreen(result.getName());

            // Attach screenshot to Extent Report
            test.addScreenCaptureFromPath(screenshotPath,
                    "Failure Screenshot");

            logger.info("Failure screenshot attached: {}", screenshotPath);

        } catch (Exception e) {

            logger.error("Unable to capture failure screenshot", e);

            test.log(Status.WARNING,
                    "Screenshot capture failed: " + e.getMessage());
        }

        testThread.remove();
    }

    // Test Skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = testThread.get();

        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
            testThread.set(test);
        }

        test.log(Status.SKIP, "Test execution skipped");

        if (result.getThrowable() != null) {

            test.log(Status.INFO, result.getThrowable());

            logger.warn("TEST SKIPPED: {}", result.getName(),
                    result.getThrowable());

        } else {

            logger.warn("TEST SKIPPED: {}", result.getName());
        }

        testThread.remove();
    }

    // Flush and Generate Report
    @Override
    public void onFinish(ITestContext testContext) {

        logger.info("Test suite execution completed");

        if (extent != null) {

            extent.flush();

            logger.info("Extent report generated: {}",
                    reportFile.getAbsolutePath());
        }

        // Open report automatically
        if (Desktop.isDesktopSupported() && reportFile != null) {

            try {

                Desktop.getDesktop().browse(reportFile.toURI());

                logger.info("Extent report opened successfully");

            } catch (IOException e) {

                logger.error("Unable to open Extent report", e);
            }
        }
    }
}
