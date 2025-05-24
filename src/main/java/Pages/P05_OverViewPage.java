package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverViewPage {
    private final WebDriver driver;

    private final By Subtotal = By.className("summary_subtotal_label");
    private final By Tax = By.className("summary_tax_label");
    private final By Total = By.className("summary_total_label");
    private final By Finish_button = By.id("finish");

    public P05_OverViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public Float getSubTotal() {
        return Float.parseFloat(Utility.GetText(driver, Subtotal).replace("Item total: $", ""));
    }

    public Float getTax() {
        return Float.parseFloat(Utility.GetText(driver, Tax).replace("Tax: $", ""));
    }

    public Float getTotal() {
        return Float.parseFloat(Utility.GetText(driver, Total).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        LogsUtil.info("sub total= " + getSubTotal() + " Tax= " + getTax() + " Total= " + getTotal());
        return String.valueOf(getSubTotal() + getTax());
    }

    public boolean ComparingPrices() {
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public P06_FinishOrder clickOnFinish() {
        Utility.clickonElement(driver, Finish_button);
        return new P06_FinishOrder(driver);
    }
}
