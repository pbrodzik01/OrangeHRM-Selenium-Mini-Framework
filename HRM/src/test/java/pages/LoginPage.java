package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(url);
        waitForVisibility(usernameInput);
        return this;
    }

    public boolean isAt() {
        return isDisplayed(usernameInput, 10);
    }

    public DashboardPage loginValidUser(String username, String password) {
        clearAndType(usernameInput, username);
        clearAndType(passwordInput, password);
        click(loginButton);
        return new DashboardPage(driver);
    }

    public LoginPage loginInvalidUser(String username, String password) {
        clearAndType(usernameInput, username);
        clearAndType(passwordInput, password);
        click(loginButton);
        waitForVisibility(errorMessage);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}