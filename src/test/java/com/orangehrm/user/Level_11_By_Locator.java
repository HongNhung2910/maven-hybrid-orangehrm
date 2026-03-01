package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
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
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_11_By_Locator extends BaseTest {

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
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clickToSaveButton();

        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);
    }

    @Test
    public void Employee_03_Page_Navigator(){
        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorPageName("Contact Details");

        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorPageName("Job");

        dependentsPage = (DependentsPageObject) jobPage.openEditNavigatorPageName("Dependents");

        personalDetailPage = (PersonalDetailPageObject) dependentsPage.openEditNavigatorPageName("Personal Details");

       jobPage = (JobPageObject) personalDetailPage.openEditNavigatorPageName("Job");
    }

    @Test
    public void Employee_03_Dynamic_Page(){
        personalDetailPage.openEditNavigatorPageName("Contact Details");
        contactDetailPage =PageGenerator.getPage(ContactDetailPageObject.class,driver);

        contactDetailPage.openEditNavigatorPageName("Job");
        jobPage =PageGenerator.getPage(JobPageObject.class,driver);

        jobPage.openEditNavigatorPageName("Dependents");
        dependentsPage =PageGenerator.getPage(DependentsPageObject.class,driver);

        dependentsPage.openEditNavigatorPageName("Personal Details");
        personalDetailPage =PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        personalDetailPage.openEditNavigatorPageName("Job");
        jobPage =PageGenerator.getPage(JobPageObject.class,driver);
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    private WebDriver driver;
    private JavascriptExecutor jsExcute;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
}