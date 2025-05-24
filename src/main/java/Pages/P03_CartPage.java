package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    static float totalPrice;
    private final WebDriver driver;

    private final By PricesOfProducts = By.xpath("//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private final By CheckoutButtonLocator = By.className("checkout_button");

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPrice() {
        try {
            List<WebElement> pricesOfProducts = driver.findElements(PricesOfProducts);
            for (int i = 1; i <= pricesOfProducts.size(); i++) {
                By elements =
                        By.xpath("(//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fulltext = Utility.GetText(driver, elements);
                totalPrice += Float.parseFloat(fulltext.replace("$", ""));
            }
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean ComparingPrices(String price) {
        LogsUtil.info("Total Price of Selected Product: " + price);
        return getTotalPrice().equals(price);
    }

    public P04_CheckoutPage ClickOnCheckout() {
        Utility.clickonElement(driver, CheckoutButtonLocator);
        return new P04_CheckoutPage(driver);
    }
}
