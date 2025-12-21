package javaOOP;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorMethod {
    public static void main(String[] args){
        FirefoxDriver ffDriver;

        //Cách 1
        ffDriver= new FirefoxDriver();

        //Cách 2
        FirefoxOptions ffOption = new FirefoxOptions();
        ffOption.addArguments("--header");
        ffDriver = new FirefoxDriver(ffOption);

        //Cách 3

        WebDriverWait explicitWait;
        explicitWait = new WebDriverWait(ffDriver, Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(ffDriver,Duration.ofSeconds(10),Duration.ofSeconds(1));
    }
}