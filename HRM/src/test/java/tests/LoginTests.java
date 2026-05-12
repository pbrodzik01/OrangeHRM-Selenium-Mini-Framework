package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.RetryAnalyzer;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        loginPage = new LoginPage(driver).open();
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"Admin", "wrongPassword123"},
                {"WrongUser", "admin123"}
        };
    }

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_valid_login() {
        DashboardPage dashboardPage = loginPage.loginValidUser("Admin", "admin123");

        Assert.assertTrue(dashboardPage.isAt(), "Dashboard should be visible after valid login.");

        LoginPage loginPageAfterLogout = dashboardPage.logout();

        Assert.assertTrue(loginPageAfterLogout.isAt(), "Login page should be visible after logout.");
    }

    @Test(dataProvider = "invalidLoginData", groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void test_invalid_login(String username, String password) {
        loginPage.loginInvalidUser(username, password);

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Wrong validation message for invalid login."
        );
    }
}