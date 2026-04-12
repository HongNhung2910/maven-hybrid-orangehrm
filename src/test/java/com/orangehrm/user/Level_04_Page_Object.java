package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class Level_04_Page_Object extends BaseTest {

    @Parameters({"appUrl","browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName){
      driver=getBrowserDriver(appURL,browserName);
      loginPage=new LoginPageObject(driver);

      adminUserName ="automationfc";
      adminPassword ="Auto@2025";
      employeeFirstName = "John";
      employeeLastName = "Terry";
    }


    @Test
    public void Employee_01_CreateNewEmployee(){
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        loginPage.clickToLoginButton();
        dashboardPage= new DashboardPageObject(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

        dashboardPage.clickToPIMModule();
        employeeListPage=new EmployeeListPageObject(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToAddEmployeeButton();
        addEmployeePage=new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID= addEmployeePage.getEmployeeID();

       // addEmployeePage=clickSaveToButton();
        personalDetailPage=new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
        private String employeeID,adminUserName,adminPassword,employeeFirstName,employeeLastName;;
}
