package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By LoginButton = By.id("login-button");
    private WebDriver driver;

    //we create construction to exchange the initiallized driver with the TC drive to avoid exceptions
    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterusername(String name) {
        Utility.senddata(driver, username, name);
        return this;
    }

    public P01_LoginPage enterpassword(String pass) {
        Utility.senddata(driver, password, pass);
        return this;
    }

    public P02_HomePage clickbutton() {
        Utility.clickonElement(driver, LoginButton);
        return new P02_HomePage(driver);
    }

    public boolean assertloginTC(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
}
