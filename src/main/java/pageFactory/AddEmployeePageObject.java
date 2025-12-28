package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy (xpath = "//input[@name()='firstName']")
    private WebElement firstNameTextbox;

    @FindBy (xpath = "//input[@name()='lastName']")
    private WebElement lastNameTextbox;

    @FindBy (xpath = "//label[text()='Employee Id']/parent::div/following=sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy (xpath = "//button[contrains(string(),'Save')]")
    private WebElement saveBtn;

    @FindBy (xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementInvisible(driver,firstNameTextbox);
        sendKeyToElement(firstNameTextbox,firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementInvisible(driver,lastNameTextbox);
        sendKeyToElement(lastNameTextbox,lastName);
    }

    public String getEmployeeID() {
        waitElementInvisible(driver,employeeIDTextbox);
       return getElementDOMProperty(employeeIDTextbox,"value");
    }

    public void clickToSaveButton() {
        waitElementClickable(driver,saveBtn);
        clickToElement(saveBtn);
    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}