package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P04_CheckoutPage {
    private final WebDriver driver;

    private final By FirstName = By.id("first-name");
    private final By LastName = By.id("last-name");
    private final By PostalCode = By.id("postal-code");
    private final By ContinueButton = By.cssSelector("input[type='submit']");


    public P04_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckoutPage FillForm(String Fname, String Lname, String ZipCode) {
        Utility.senddata(driver, FirstName, Fname);
        Utility.senddata(driver, LastName, Lname);
        Utility.senddata(driver, PostalCode, ZipCode);
        return this;
    }

    public P05_OverViewPage clickOnContinue() {
        Utility.clickonElement(driver, ContinueButton);
        return new P05_OverViewPage(driver);
    }
}
