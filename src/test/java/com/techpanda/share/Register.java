package com.techpanda.share;

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Register extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeTest
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        homePage = PageGenerator.getPage(HomePO.class, driver);

        loginPage = homePage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccountLink();

        registerPage.enterFirstName("Automation");
        registerPage.enterLastName("SSI");
        registerPage.enterToEmail("automation" + getRandomNumber() +"gmail.com");
        registerPage.enterToPassword("123Abc@");
        registerPage.enterToConfirmPassword("123Abc@");
        registerPage.enterToRegisterButton();

        myAccountPage= PageGenerator.getPage(MyAccountPO.class,driver);


        verifyEquals(myAccountPage.getSuccessMessage(),"Thank you for registering....");

        cookies = myAccountPage.getPageCookies(driver);

        closeBrowser();
    }



    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private RegisterPO registerPage;
    public static Set<Cookie> cookies;
}