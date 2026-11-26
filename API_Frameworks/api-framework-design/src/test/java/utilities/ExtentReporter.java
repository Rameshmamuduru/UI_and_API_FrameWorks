package utilities;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter implements ITestListener, ISuiteListener {

    private static ExtentReports extent;

    private static ExtentSparkReporter sparkReporter;

    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    private static Map<ISuite, ExtentTest> testSuiteMap =
            new ConcurrentHashMap<>();

    private static String reportName;

    // Initialize ExtentReports
    public synchronized static ExtentReports getExtentInstance() {

        if (extent == null) {

            String timeStamp =
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                    .format(new Date());

            reportName = "Extent-Report-" + timeStamp + ".html";

            String reportPath = System.getProperty("user.dir")
                    + File.separator + "reports"
                    + File.separator + reportName;

            // Create reports directory if it does not exist
            File reportDirectory = new File(
                    System.getProperty("user.dir")
                    + File.separator + "reports");

            if (!reportDirectory.exists()) {
                reportDirectory.mkdirs();
            }

            sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle(
                    "API Automation Test Report");

            sparkReporter.config().setReportName(
                    "Online Store API Test Execution Report");

            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            // System information
            extent.setSystemInfo("Project",
                    "Online Store API Automation");

            extent.setSystemInfo("Framework",
                    "Rest Assured + TestNG");

            extent.setSystemInfo("Host Name",
                    "Localhost");

            extent.setSystemInfo("Environment",
                    "QA");

            extent.setSystemInfo("User",
                    System.getProperty("user.name"));

            extent.setSystemInfo("Java Version",
                    System.getProperty("java.version"));
        }

        return extent;
    }

    // Suite started
    @Override
    public void onStart(ISuite suite) {

        ExtentTest suiteNode =
                getExtentInstance().createTest(suite.getName());

        suiteNode.info("Test suite execution started");

        suiteNode.assignCategory("Suite: " + suite.getName());

        testSuiteMap.put(suite, suiteNode);
    }

    // Test started
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest suiteNode =
                testSuiteMap.get(result.getTestContext().getSuite());

        if (suiteNode == null) {
            return;
        }

        String methodName = result.getMethod().getMethodName();

        ExtentTest methodNode = suiteNode.createNode(methodName);

        testNode.set(methodNode);

        methodNode.info("Test execution started");

        // Test description
        String description = result.getMethod().getDescription();

        if (description != null && !description.isEmpty()) {
            methodNode.info("Description: " + description);
        }

        // Test parameters
        Object[] parameters = result.getParameters();

        if (parameters.length > 0) {
            methodNode.info("Parameters: "
                    + Arrays.deepToString(parameters));
        }

        // Test groups
        String[] groups = result.getMethod().getGroups();

        if (groups.length > 0) {
            methodNode.assignCategory(groups);
        }

    }

    // Test passed
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = testNode.get();

        if (test != null) {

            test.log(Status.PASS,
                    result.getMethod().getMethodName() + " passed");

            test.info("Execution Time: "
                    + getExecutionTime(result) + " ms");
        }

        testNode.remove();
    }

    // Test failed
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testNode.get();

        if (test != null) {

            test.log(Status.FAIL,
                    result.getMethod().getMethodName() + " failed");

            test.fail(result.getThrowable());

            test.info("Execution Time: "
                    + getExecutionTime(result) + " ms");
        }

        testNode.remove();
    }

    // Test skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = testNode.get();

        if (test != null) {

            test.log(Status.SKIP,
                    result.getMethod().getMethodName() + " skipped");

            if (result.getThrowable() != null) {
                test.info("Skip Reason: "
                        + getStackTrace(result.getThrowable()));
            }

            test.info("Execution Time: "
                    + getExecutionTime(result) + " ms");
        }

        testNode.remove();
    }

    // Suite finished
    @Override
    public void onFinish(ISuite suite) {

        ExtentTest suiteNode = testSuiteMap.get(suite);

        if (suiteNode != null) {

            suiteNode.info("Test suite execution completed");
        }

        // Flush report after suite execution
        getExtentInstance().flush();
    }

    // Get current ExtentTest node
    public static ExtentTest getTest() {
        return testNode.get();
    }

    // Calculate execution time
    private long getExecutionTime(ITestResult result) {

        return result.getEndMillis() - result.getStartMillis();
    }

    // Convert exception stack trace into String
    private String getStackTrace(Throwable throwable) {

        StringWriter sw = new StringWriter();

        PrintWriter pw = new PrintWriter(sw);

        throwable.printStackTrace(pw);

        return sw.toString();
    }
}

