package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By loader = By.cssSelector(".oxd-loading-spinner");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisibility(By locator) {
        waitForLoaderToDisappear();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        waitForLoaderToDisappear();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    protected void type(By locator, String text) {
        waitForVisibility(locator).sendKeys(text);
    }

    protected void clearAndType(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    protected String getValue(By locator) {
        return waitForVisibility(locator).getAttribute("value");
    }

    protected boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void waitForLoaderToDisappear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {
        }
    }
}