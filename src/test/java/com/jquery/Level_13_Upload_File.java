package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;


public class Level_13_Upload_File extends BaseTest {

    @Parameters({"Url", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String Url) {
        driver = getBrowserDriver(browser, Url);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }


    @Test
    public void Upload_01_Single() {
        /*
        homePage.uploadMultipleFiles(driver,mountainFileName);
        homePage.uploadMultipleFiles(driver,riverFileName);
        homePage.uploadMultipleFiles(driver,treeFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFileName));

         */
    }

    @Test
    public void Upload_02_Multiple() {
        homePage.refeshToPage(driver);
        /*

        homePage.uploadMultipleFiles(driver,mountainFileName,riverFileName,treeFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFileName));

         */
    }


    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String mountainFileName = "mountain.png";
    String riverFileName = "River.png";
    String treeFileName = "Tree.png";
    String[] fileName = {mountainFileName, riverFileName, treeFileName};

}