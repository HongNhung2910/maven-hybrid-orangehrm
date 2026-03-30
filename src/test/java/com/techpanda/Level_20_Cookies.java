package com.techpanda;

import com.techpanda.share.Register;
import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;

import java.util.Set;


public class Level_20_Cookies extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(Level_20_Cookies.class);

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        this.cookies= Register.cookies;

        homePage=PageGenerator.getPage(HomePO.class,driver);
        loginPage=homePage.openLoginPage();
        loginPage.setPageCookies(driver,this.cookies);


    }

    @Test
    public void Employee_01_CreateNewEmployee() {

    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private Set<Cookie> cookies;
}