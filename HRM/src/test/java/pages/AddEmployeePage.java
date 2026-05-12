package pages;

import models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage extends BasePage {

    private final By firstNameInput = By.cssSelector("input.orangehrm-firstname");
    private final By middleNameInput = By.cssSelector("input.orangehrm-middlename");
    private final By lastNameInput = By.cssSelector("input.orangehrm-lastname");
    private final By employeeIdInput = By.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//following::input[1]");
    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isDisplayed(firstNameInput, 10);
    }

    public PersonalDetailsPage addEmployee(Employee employee) {
        clearAndType(firstNameInput, employee.getFirstName());
        clearAndType(middleNameInput, employee.getMiddleName());
        clearAndType(lastNameInput, employee.getLastName());
        clearAndType(employeeIdInput, employee.getEmployeeId());
        click(saveButton);
        return new PersonalDetailsPage(driver);
    }
}