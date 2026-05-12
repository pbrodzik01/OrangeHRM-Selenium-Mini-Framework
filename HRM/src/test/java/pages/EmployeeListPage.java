package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {

    private final By pimHeader = By.xpath("//h6[normalize-space()='PIM']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By employeeIdSearchInput = By.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//following::input[1]");
    private final By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private final By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By noRecordsFoundLabel = By.xpath("//*[normalize-space()='No Records Found']");

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isDisplayed(pimHeader, 10);
    }

    public AddEmployeePage clickAddEmployee() {
        click(addButton);
        return new AddEmployeePage(driver);
    }

    public EmployeeListPage searchByEmployeeId(String employeeId) {
        clearAndType(employeeIdSearchInput, employeeId);
        click(searchButton);
        waitForLoaderToDisappear();
        return this;
    }

    public boolean isEmployeeVisible(String employeeId) {
        By employeeRow = By.xpath(getEmployeeRowXpath(employeeId));
        return isDisplayed(employeeRow, 10);
    }

    public PersonalDetailsPage clickEditForEmployee(String employeeId) {
        By editButton = By.xpath(getEmployeeRowXpath(employeeId) + "//button[.//i[contains(@class,'bi-pencil-fill')]]");
        click(editButton);
        return new PersonalDetailsPage(driver);
    }

    public EmployeeListPage deleteEmployee(String employeeId) {
        By deleteButton = By.xpath(getEmployeeRowXpath(employeeId) + "//button[.//i[contains(@class,'bi-trash')]]");
        click(deleteButton);
        click(confirmDeleteButton);
        waitForLoaderToDisappear();
        return this;
    }

    public boolean isNoRecordsFoundVisible() {
        return isDisplayed(noRecordsFoundLabel, 5);
    }

    private String getEmployeeRowXpath(String employeeId) {
        return "//div[contains(@class,'oxd-table-row')][.//div[normalize-space()='" + employeeId + "']]";
    }
}