package com.orangehrm.user;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import utilities.DataConfigNet;


public class Level_25_Data_Test_I_Testclass extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        dataConfig= DataConfigNet.getData();

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "automation";
        adminPassword = "Auto123$$##";
        employeeID = String.valueOf(getRandomNumber());

        employeeFirstName = dataConfig.getFirstName();
        employeeLastName = dataConfig.getLastName();
        employeeUsername = dataConfig.getUserName();
        employeePassword = dataConfig.getPassword();

        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        Assert.assertFalse(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyFalse(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
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
    private DataConfigNet dataConfig;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername,employeePassword;


}