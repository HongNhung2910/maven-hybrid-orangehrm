package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;


public class Level_09_Switch_Url extends BaseTest {
    private String userURL,adminURL;


    @Parameters({"userUrl","adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String userUrl,String adminUrl) {
       this.userURL=userUrl;
       this.adminURL=adminUrl;

        adminUser="automationfc";
        adminPassword ="Auto@2025";
        userFirstname ="Kim";
        userLastname= "Woo Bin";
        userEmailAddress="Woo.Bin"+getRandomNumber()+"@gmail.com";
        userPassword="";

       driver = getBrowserDriver(browser, userUrl);
       userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
    }

    @Test
    public void OpenCart_01_Logging_And_Logout() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage=PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());

        userRegisterPage.clickToLogoutLinkAtUserSite(driver);
        userHomePage= userRegisterPage.clickToContinueButton();

        adminLoginPage =userRegisterPage.openAdminSite(driver,adminURL);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();

        adminLoginPage = adminCustomerPage.ClickToLogoutLinkAtAdminSite(driver);

        userHomePage = adminLoginPage.openUserSite(driver,userURL);

        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPO = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());
    }

    @Test
    public void OpenCart_02_Logging_Without_Logout(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());

        //user=> admin
        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage =PageGenerator.getPage(AdminLoginPO.class,driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();

        //admin => user
        userHomePage = adminLoginPage.openUserSite(driver,userURL);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPO =PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user=> admin
        userMyAccountPO.openAdminSite(driver,adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class,driver);

        //verify hien thi
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
    }

    @Test
    public void OpenCart_03_Multiple_Tab(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        userRegisterPage.openUrlByNewTAB(driver,adminURL);

        //user-> admin
        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage =PageGenerator.getPage(AdminLoginPO.class,driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();
        adminWindowID =adminCustomerPage.getCurrentWindowID(driver);

        //admin -> user
        adminCustomerPage.switchToWindowByID(driver,userWindowID);
        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class,driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,driver);
        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user-> admin
        userMyAccountPO.switchToWindowByID(driver,userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class,driver);


        //verify hien thi
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserMyAccountPO userMyAccountPO;
    private  String userFirstname,userLastname,userPassword,userEmailAddress;
    private String adminUser,adminPassword;
    private String userWindowID, adminWindowID;

}