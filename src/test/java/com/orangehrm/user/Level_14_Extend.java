package com.orangehrm.user;

import com.relevantcodes.extentreports.LogStatus;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;


public class Level_14_Extend extends BaseTest {

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
        ExtentManager.startTest(method.getName(),"Employee_01_CreateNewEmployee");

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 01: Enter Username and Password: "+adminUserName+"/"+adminPassword);
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 02: Click button Login");
        dashboardPage = loginPage.clickToLoginButton();
        takeScreenshot();

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 03: Navigate to PIM Module");
        employeeListPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 05: Enter to Firstname= "+employeeFirstName+" and Lastname= "+employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 06: Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 07:Verify Firstname");
        verifyEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 08:Verify Lastname");
        verifyEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);
        takeScreenshot();

        ExtentManager.getTest().log(LogStatus.INFO, "NewEmployee - Step 09:Verify ID");
        verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);
        takeScreenshot();

        ExtentManager.endTest();
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