package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneratorManager;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject (WebDriver driver){
        this.driver=driver;
    }

    public EmployeeListPageObject clickToPIMModule() {
        waitElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver,DashboardPageUI.PIM_MODULE);
        //return new EmployeeListPageObject(driver);
        //return PageGeneratorManager.getEmployeeListPage(driver);
        return PageGeneratorGeneric.getPage(EmployeeListPageObject.class,driver);
    }
}