package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    WebElement driver;
    public WebElement getWebElement(String locator){
        return driver.findElement(By.cssSelector(locator));
    }
}
