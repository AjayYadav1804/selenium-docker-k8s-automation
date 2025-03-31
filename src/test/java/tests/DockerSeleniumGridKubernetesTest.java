package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DockerSeleniumGridKubernetesTest {

    public WebDriver driver;
    public static ExtentReports extentReports;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    ExtentTest test;

    //@Parameters("browser")
    //@BeforeTest
    public void setup(String browserType) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserType.equalsIgnoreCase("chrome")) {
            capabilities.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions());
            System.out.println("##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
            test.log(Status.INFO, "##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");

        } else if (browserType.equalsIgnoreCase("firefox")) {
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, new FirefoxOptions());
            System.out.println("##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
            test.log(Status.INFO, "##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
            //test.log(Status.INFO, "##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");

        } else {
            EdgeOptions options = new EdgeOptions();
            capabilities.setCapability("ms:edgeOptions", options);
            System.out.println("##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
            test.log(Status.INFO, "##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
            //test.log(Status.INFO, "##########TEST CASE EXECUTION STARTED ON == > " + browserType + " ###########");
        }
        
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:52814"), capabilities);
    }

    @Parameters("browser")
    @Test
    public void googleOnCHROMETest(String browserType) throws MalformedURLException, InterruptedException {
    	test  = extentReports.createTest("googleOnCHROMETest");
        extentTest.set(test);
        try {
        	setup(browserType);
            driver.get("https://www.google.com");
            test.log(Status.PASS, "Google application is launched");
            Assertion a = new Assertion();
            a.assertEquals(driver.getTitle(), "Google", "WRONG Page Title");
            test.log(Status.PASS,"Title is displayed ->>>"+driver.getTitle());
            test.log(Status.PASS, "Test Passed: googleOnCHROMETest");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
        }
    }

    @Parameters("browser")
    @Test
    public void linkedinOnFFTest(String browserType) throws MalformedURLException, InterruptedException {
    	test  = extentReports.createTest("linkedinOnFFTest");
        extentTest.set(test);
        try {
        	setup(browserType);
            driver.get("https://www.linkedin.com");
            test.log(Status.PASS, "LinkedIn application is launched");
            Assertion a = new Assertion();
            a.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "WRONG Page Title");
            test.log(Status.PASS,"Title is displayed ->>>"+driver.getTitle());
            test.log(Status.PASS, "Test Passed: linkedinOnFFTest");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
        }
    }

    @Parameters("browser")
    @Test
    public void gmailOnEDGETest(String browserType) throws MalformedURLException, InterruptedException {
        test = extentReports.createTest("gmailOnEDGETest");
        extentTest.set(test);
        try {
        	setup(browserType);
            driver.get("https://www.gmail.com");
            test.log(Status.PASS, "Gmail application is launched");
            Assertion a = new Assertion();
            a.assertEquals(driver.getTitle(), "Gmail", "WRONG Page Title");
            test.log(Status.PASS,"Title is displayed ->>>"+driver.getTitle());
            test.log(Status.PASS, "Test Passed: gmailOnEDGETest");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
        }
    }

    @Parameters("browser")
    @Test
    public void facebookOnCHROMETest(String browserType) throws MalformedURLException, InterruptedException {
        test = extentReports.createTest("facebookOnCHROMETest");
        extentTest.set(test);
        try {
        	setup(browserType);
            driver.get("https://www.facebook.com");
            test.log(Status.PASS, "Facebook application is launched");
            Assertion a = new Assertion();
            a.assertEquals(driver.getTitle(), "Facebook – log in or sign up", "WRONG Page Title");
            test.log(Status.PASS,"Title is displayed ->>>"+driver.getTitle());
            test.log(Status.PASS, "Test Passed: facebookOnCHROMETest");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
        }
    }

    @AfterTest
    public void closeDriver() {
        System.out.println("##########TEST CASE EXECUTION ENDED######");
        driver.quit();
    }

    @BeforeSuite
    public void startReport() {
        extentReports = new ExtentReports();
        String path = System.getProperty("user.dir") + "/reports/testReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setDocumentTitle("Selenium Grid - Kubernetes");
        sparkReporter.config().setReportName("Selenium Grid report - Docker, Kubernetes");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Tester", "Ajay Yadav");
    }

    @AfterSuite
    public static void flushReports() {
        try {
			extentReports.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}