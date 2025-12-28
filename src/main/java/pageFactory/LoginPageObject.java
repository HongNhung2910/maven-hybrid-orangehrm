package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(css = "input[name='username']")
    private WebElement usernameTextbox;

    @FindBy(name = "password")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver,usernameTextbox);
        sendKeyToElement(usernameTextbox,username);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver,passwordTextbox);
        sendKeyToElement(passwordTextbox,password);
    }

    public void clickToLoginButton() {
        waitElementClickable(driver,loginButton);
        clickToElement(loginButton);


    }
}
