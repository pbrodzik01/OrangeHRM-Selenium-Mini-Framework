package tests;

import base.BaseTest;
import models.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import utils.RetryAnalyzer;
import utils.TestDataFactory;

public class EmployeeCrudTests extends BaseTest {

    private static Employee employee;

    private DashboardPage loginToApplication() {
        LoginPage loginPage = new LoginPage(driver).open();
        return loginPage.loginValidUser("Admin", "admin123");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_add_employee() {
        employee = TestDataFactory.createRandomEmployee();

        DashboardPage dashboardPage = loginToApplication();
        EmployeeListPage employeeListPage = dashboardPage.goToPim();
        AddEmployeePage addEmployeePage = employeeListPage.clickAddEmployee();
        PersonalDetailsPage personalDetailsPage = addEmployeePage.addEmployee(employee);

        Assert.assertTrue(personalDetailsPage.isAt(), "Personal Details page should be visible.");
        Assert.assertEquals(personalDetailsPage.getFirstName(), employee.getFirstName(), "First name mismatch.");
        Assert.assertEquals(personalDetailsPage.getMiddleName(), employee.getMiddleName(), "Middle name mismatch.");
        Assert.assertEquals(personalDetailsPage.getLastName(), employee.getLastName(), "Last name mismatch.");
    }

    @Test(dependsOnMethods = "test_add_employee", groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_search_employee() {
        DashboardPage dashboardPage = loginToApplication();
        EmployeeListPage employeeListPage = dashboardPage.goToPim();

        employeeListPage.searchByEmployeeId(employee.getEmployeeId());

        Assert.assertTrue(
                employeeListPage.isEmployeeVisible(employee.getEmployeeId()),
                "Employee should be visible in search results."
        );
    }

    @Test(dependsOnMethods = "test_search_employee", groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_edit_employee() {
        DashboardPage dashboardPage = loginToApplication();
        EmployeeListPage employeeListPage = dashboardPage.goToPim();

        employeeListPage.searchByEmployeeId(employee.getEmployeeId());
        PersonalDetailsPage personalDetailsPage = employeeListPage.clickEditForEmployee(employee.getEmployeeId());
        personalDetailsPage.updateLastName(employee.getUpdatedLastName());

        Assert.assertEquals(
                personalDetailsPage.getLastName(),
                employee.getUpdatedLastName(),
                "Last name was not updated."
        );

        employee.setLastName(employee.getUpdatedLastName());
    }

    @Test(dependsOnMethods = "test_edit_employee", groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_delete_employee() {
        DashboardPage dashboardPage = loginToApplication();
        EmployeeListPage employeeListPage = dashboardPage.goToPim();

        employeeListPage.searchByEmployeeId(employee.getEmployeeId());
        employeeListPage.deleteEmployee(employee.getEmployeeId());

        employeeListPage.searchByEmployeeId(employee.getEmployeeId());

        Assert.assertTrue(
                employeeListPage.isNoRecordsFoundVisible(),
                "Employee should not exist after delete."
        );
    }
}