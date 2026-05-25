package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_22_LiveCode extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "Admin";
        adminPassword = "admin123";

        employeeID=String.valueOf(getRandomNumber());
        employeeFirstName= "Automation";
        employeeLastName="Tester";
        employeeUsername="Automation"+getRandomNumber();
        employeePassword="Automation123#";

        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

        loginPage.clickToButtonByText(driver,"Login");

        dashboardPage= PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

      //  verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
    }

    @Test
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver,"Add");
        addEmployeePage= PageGenerator.getPage(AddEmployeePageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeLastName);
        addEmployeePage.enterToTextboxByLabel(driver,"Employee Id",employeeID);
        //employeeID = addEmployeePage.getTextboxValueByLabel(driver,"Employee ID");

        addEmployeePage.clickToCheckboxByLabel(driver,"Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver,"Password",employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",employeePassword);

        addEmployeePage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

       // verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName);
       // verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        // verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee ID"), employeeID);

        //Logout
        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",employeePassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        //Den man Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));
        dashboardPage.clickToModuleByTextInMenuItem(driver,"My info");
        personalDetailPage=PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee ID"), employeeID);
    }

    @Test
    public void Employee_02_UploadAvatar() {
        personalDetailPage.clickToProfileImage();

        Dimension oldProfileImageSize = personalDetailPage.getProfileNaturalImageSize();

        //File type
        personalDetailPage.uploadMultipleFiles(driver,"excel.xlsx");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(),"File type not allowed");

        //maximum size
        personalDetailPage.uploadMultipleFiles(driver,"avatar1.jpg");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(),"Attachment Size Exceeded");

        //Valid File type
        personalDetailPage.uploadMultipleFiles(driver,"avatar2.jpg");

        personalDetailPage.clickToButtonByText(driver,"Save");

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Updated"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);

        Dimension newProfileImageSize = personalDetailPage.getProfileNaturalImageSize();

        verifyNotEquals(oldProfileImageSize,newProfileImageSize);
    }



    @Test
    public void Employee_03_EditPersonalDetail() {
        //Edit voi quyen admin

        //view lai voi quyen employee

        //edit voi quyen employee

        //view lai voi quyen admin

    }

    @Test
    public void Employee_04_ContactDetail() {

    }

    @Test
    public void Employee_05_EmergencyDetail() {

    }

    @Test
    public void Employee_06_Dependents() {

    }

    @Test
    public void Employee_07_Jobs() {

    }

    @Test
    public void Employee_08_Salary() {

    }

    @Test
    public void Employee_09_Tax() {

    }

    @Test
    public void Employee_10_Dependents() {

    }

    @Test
    public void Employee_11_Qualification() {

    }

    @Test
    public void Employee_12_Search() {

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
    private String employeeUsername,employeePassword;


}