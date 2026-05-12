package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPage extends BasePage {

    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By firstNameInput = By.cssSelector("input.orangehrm-firstname");
    private final By middleNameInput = By.cssSelector("input.orangehrm-middlename");
    private final By lastNameInput = By.cssSelector("input.orangehrm-lastname");
    private final By saveButton = By.xpath("(//button[@type='submit' and normalize-space()='Save'])[1]");

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isDisplayed(personalDetailsHeader, 10);
    }

    public String getFirstName() {
        return getValue(firstNameInput);
    }

    public String getMiddleName() {
        return getValue(middleNameInput);
    }

    public String getLastName() {
        return getValue(lastNameInput);
    }

    public PersonalDetailsPage updateLastName(String newLastName) {
        clearAndType(lastNameInput, newLastName);
        click(saveButton);
        waitForLoaderToDisappear();
        return this;
    }
}