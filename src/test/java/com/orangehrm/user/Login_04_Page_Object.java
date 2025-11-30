package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_04_Page_Object extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID;

    @Parameters({"appUrl","browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName){
      driver=getBrowserDriver(appURL,browserName);
      loginPage=new LoginPageObject();
    }


    @Test
    public void Employee_01_CreateNewEmployee(){
        loginPage.enterToUsernameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        loginPage.clickToLoginButton();

        dashboardPage= new DashboardPageObject();
        dashboardPage.clickToPIMModule();

        employeeListPage=new EmployeeListPageObject();
        employeeListPage.clickToAddEmployeeButton();

        addEmployeePage=new AddEmployeePageObject();
        addEmployeePage.enterToFirstNameTextbox("");
        addEmployeePage.enterToLastNameTextbox("");
        employeeID= addEmployeePage.getEmployeeID();
        addEmployeePage=clickSaveToButton();

        personalDetailPage=new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),"");
    }

    private AddEmployeePageObject clickSaveToButton() {
        return null;
    }


    @Test
    public void Employee_02_EditEmployee(){

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
