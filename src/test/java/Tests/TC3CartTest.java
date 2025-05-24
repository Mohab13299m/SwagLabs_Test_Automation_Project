package Tests;

import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_CartPage;
import Utilities.DataUtil;
import Utilities.LogsUtil;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtil.getPropertiesData;


@Listeners({InvokedMethod.class, Itest.class})
public class TC3CartTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    String username = new Faker().name().username();

    public TC3CartTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException {
        LogsUtil.info("Edge Driver is opened");
        setupDriver(getPropertiesData("enviroments", "Browser"));
        getDriver().get(getPropertiesData("enviroments", "BaseUrl"));
        LogsUtil.info("Page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void PricesCompareTestCase() {
        String totalPrice = new P01_LoginPage(getDriver())
                .enterusername(ValidUsername)
                .enterpassword(ValidPassword)
                .clickbutton()
                .addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();
        new P02_HomePage(getDriver()).ClickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).ComparingPrices(totalPrice));
    }

    @AfterMethod
    public void quit() {
        quitdriver();
    }

}
