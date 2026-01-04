package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneratorManager;
import pageUIs.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementInvisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.FIRST_NAME_TEXTBOX,firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementInvisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.LAST_NAME_TEXTBOX,lastName);
    }

    public String getEmployeeID() {
        waitElementInvisible(driver,AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementText(driver,AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementClickable(driver,AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver,AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
      //  return new PersonalDetailPageObject(driver);
      //  return PageGeneratorManager.getPersonalDetailPage(driver);
        return PageGeneratorGeneric.getPage(PersonalDetailPageObject.class,driver);
    }
}