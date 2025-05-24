package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_FinishOrder {
    private final WebDriver driver;
    private By ThanksMessage = By.tagName("h2");


    public P06_FinishOrder(WebDriver driver) {
        this.driver = driver;
    }

    public boolean VisibilityOfThanksMessgage() {
        return Utility.bytoWebelement(driver, ThanksMessage).isDisplayed();
    }
}
