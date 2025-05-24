package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class P02_HomePage {
    static float totalPrice;

    private static List<WebElement> allproducts;

    private final WebDriver driver;
    private final By addTocartButtonAllproducts = By.xpath("//button[@class]");
    private final By NumberofProductsonCartIcon = By.xpath("//span[contains(@class,'shopping_cart_badge')]");
    private final By NumberofSelectedProducts = By.xpath("//button[text()='Remove']");
    private final By PricesOfSelectedProducts = By.xpath("//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price']");


    private final By Open_Menu = By.xpath("//button[text()='Open Menu']");
    private final By Logout_button = By.id("logout_sidebar_link");

    private final By CartIconButton = By.className("shopping_cart_container");

    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getNumberofProductsonCartIcon() {
        return NumberofProductsonCartIcon;
    }

    public P02_HomePage AddallProducts() {
        allproducts = driver.findElements(addTocartButtonAllproducts);
        LogsUtil.info("Number of all products" + allproducts.size());
        for (int i = 1; i <= allproducts.size(); i++) {
            By addTocartButtonAllproducts = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickonElement(driver, addTocartButtonAllproducts);
        }
        return this;
    }

    public String getNumberofProductsonCart() {
        try {
            LogsUtil.info("Number of products on cart:" + Utility.GetText(driver, NumberofProductsonCartIcon));
            return Utility.GetText(driver, NumberofProductsonCartIcon);
        } catch (Exception e) {
            LogsUtil.Error(e.getMessage());
            return "0";
        }
    }

    public String NumberofSelectedProducts() {
        try {
            List<WebElement> SelectedProducts =
                    driver.findElements(NumberofSelectedProducts);
            LogsUtil.info("Number of selected products on cart:" + SelectedProducts.size());
            return String.valueOf(SelectedProducts.size());
        } catch (Exception e) {
            LogsUtil.Error(e.getMessage());
            return "0";
        }
    }

    public P02_HomePage addRandomProducts(int Numberofproductsneeded, int totalnumberofproduct) {
        Set<Integer> randomNumbers = Utility.generateUniqueNumber(Numberofproductsneeded, totalnumberofproduct);
        for (int i : randomNumbers) {
            LogsUtil.info("random product number :" + i);
            By addTocartButtonAllproducts = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickonElement(driver, addTocartButtonAllproducts);
        }
        return this;
    }

    public P03_CartPage ClickOnCartIcon() {
        Utility.clickonElement(driver, CartIconButton);
        return new P03_CartPage(driver);
    }

    public boolean comparingNumberofSelectedProductWithCart() {
        return getNumberofProductsonCart().equals(NumberofSelectedProducts());
    }


    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(PricesOfSelectedProducts);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements =
                        By.xpath("(//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fulltext = Utility.GetText(driver, elements);
                LogsUtil.info("Price of Selected Product: " + fulltext);

                totalPrice += Float.parseFloat(fulltext.replace("$", ""));
            }
            LogsUtil.info("Total Price of Selected Product: " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }

    public P02_HomePage AcceptAlert() {
        Utility.GeneralWait(driver).until(ExpectedConditions.alertIsPresent());
        Utility.clickonElement(driver, By.cssSelector("[onclick='jsAlert()']"));
        driver.switchTo().alert().accept();
        return this;
    }

    public P02_HomePage OpenMenu() {
        Utility.clickonElement(driver, Open_Menu);
        return this;
    }

    public P01_LoginPage Logout() {
        Utility.clickonElement(driver, Logout_button);
        return new P01_LoginPage(driver);
    }
}
