package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By pimMenuItem = By.xpath("//span[normalize-space()='PIM']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-name");
    private final By logoutButton = By.xpath("//a[normalize-space()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isDisplayed(dashboardHeader, 10);
    }

    public EmployeeListPage goToPim() {
        click(pimMenuItem);
        return new EmployeeListPage(driver);
    }

    public LoginPage logout() {
        click(userDropdown);
        click(logoutButton);
        return new LoginPage(driver);
    }
}