package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailPageObject extends BasePageFactory {

    private WebDriver driver;

    @FindBy(name = "firstname")
    private WebElement firstNameTextbox;

    @FindBy (name = "lastname")
    private WebElement lastNameTextbox;

    @FindBy (xpath = "//label[text()='Employee Id']/parent::div/following=sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy (xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public PersonalDetailPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getFirstNameTextboxValue() {
        waitElementInvisible(driver,firstNameTextbox);
        return getElementDOMProperty(firstNameTextbox,"value");
    }

    public String getLastNameTextboxValue() {
        waitElementInvisible(driver,lastNameTextbox);
        return getElementDOMProperty(lastNameTextbox,"value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementInvisible(driver,employeeIDTextbox);
        return getElementDOMProperty(employeeIDTextbox,"value");
    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}
