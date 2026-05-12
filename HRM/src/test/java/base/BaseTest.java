package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.createDriver();
        driver = DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            String path = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
            System.out.println("Screenshot saved: " + path);
        }

        DriverFactory.quitDriver();
    }
}