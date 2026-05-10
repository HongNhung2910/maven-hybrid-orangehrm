    package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
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


public class Level_23_Browser_Config extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName) {
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class,driver);

        /*
        //Selenium 3.x
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setBrowserName("Chrome");
        desiredCapabilities.setVersion("144");
        desiredCapabilities.setPlatform(Platform.WIN10);

        //Selenium 4.x
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.setBrowserVersion("144");
        chromeOptions.useWebView(true);
        chromeOptions.configureFromEnv();

        FirefoxOptions firefoxOptions=new FirefoxOptions();
        firefoxOptions.setEnableDownloads(true);
        firefoxOptions.configureFromEnv();
        firefoxOptions.useWebView(true);

        EdgeOptions edgeOptions=new EdgeOptions();
        edgeOptions.useWebView(true);
        edgeOptions.configureFromEnv();

         */

        adminUserName = "Admin";
        adminPassword = "admin123";

        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);
        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
      }

    @Test
    public void Employee_01_NewEmployee() {
    }

    @Test
    public void Employee_02_UploadAvatar() {
    }

    @Test
    public void Employee_03_NewEmployee() {
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowser();
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