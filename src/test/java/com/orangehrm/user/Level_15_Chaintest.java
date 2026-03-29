package com.orangehrm.user;

import com.aventstack.chaintest.plugins.ChainTestListener;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

import java.lang.reflect.Method;


public class Level_15_Chaintest extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        loginPage = PageGenerator.getPage(LoginPageObject.class,driver);
        adminUserName = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void Employee_01_CreateNewEmployee(Method method) {

        ChainTestListener.log("NewEmployee - Step 01: Enter Username and Password: " + adminUserName + "/" + adminPassword);
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        ChainTestListener.log("NewEmployee - Step 02: Click button Login");
        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
    }

    public void Employee_02 (Method method) {
        ChainTestListener.log("NewEmployee - Step 03: Navigate to PIM Module");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("NewEmployee - Step 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("NewEmployee - Step 05: Enter to Firstname= "+employeeFirstName+" and Lastname= "+employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        ChainTestListener.log("NewEmployee - Step 06: Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        ChainTestListener.log( "NewEmployee - Step 07:Verify Firstname");
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);

        ChainTestListener.log("NewEmployee - Step 08:Verify Lastname");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);

        ChainTestListener.log( "NewEmployee - Step 09:Verify ID");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
}