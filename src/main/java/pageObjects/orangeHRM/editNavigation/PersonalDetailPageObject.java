package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

public class PersonalDetailPageObject extends EditNavigatorPageObject {

    private WebDriver driver;

    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.LAST_NAME_TEXTBOX,"value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver,PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

    public void clickToProfileImage() {
        waitElementClickable(driver,PersonalDetailPageUI.PROFILE_IMAGE);
        clickToElement(driver,PersonalDetailPageUI.PROFILE_IMAGE);
    }

    public String getErrorMessageAtProfileImage() {
        waitElementVisible(driver,PersonalDetailPageUI.PROFILE_IMAGE_ERROR_MSG);
        return  getElementText(driver,PersonalDetailPageUI.PROFILE_IMAGE_ERROR_MSG);

    }

    public Dimension getProfileNaturalImageSize() {
        waitElementVisible(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        int x =Integer.parseInt(getElementDOMProperty(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE,"naturalWidth"));
        int y =Integer.parseInt(getElementDOMProperty(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE,"naturalHeight"));
        return new Dimension(x,y);

    }
}
