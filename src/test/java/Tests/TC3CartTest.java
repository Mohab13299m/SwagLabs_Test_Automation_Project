package Tests;

import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_CartPage;
import Utilities.DataUtil;
import Utilities.LogsUtil;
import com.github.javafaker.Faker;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtil.getPropertiesData;
import static Utilities.Utility.getAllCookies;
import static Utilities.Utility.restoreSession;


@Listeners({InvokedMethod.class, Itest.class})
public class TC3CartTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    String username = new Faker().name().username();

    private Set<Cookie> cookies;

    public TC3CartTest() throws FileNotFoundException {
    }

    @BeforeClass
    public void login() throws IOException {
        LogsUtil.info("Edge Driver is opened");
        setupDriver(getPropertiesData("enviroments", "Browser"));
        getDriver().get(getPropertiesData("enviroments", "BaseUrl"));
        LogsUtil.info("Page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver())
                .enterusername(ValidUsername)
                .enterpassword(ValidPassword)
                .clickbutton();
        cookies = getAllCookies(getDriver());
        quitdriver();
    }

    @BeforeMethod
    public void setup() throws IOException {
        LogsUtil.info("Edge Driver is opened");
        setupDriver(getPropertiesData("enviroments", "Browser"));
        getDriver().get(getPropertiesData("enviroments", "BaseUrl"));
        LogsUtil.info("Page is redirected to url");
        restoreSession(getDriver(), cookies);
        getDriver().get(getPropertiesData("enviroments", "HomeUrl"));
        getDriver().navigate().refresh();
    }

    @Test
    public void PricesCompareTestCase() {
        String totalPrice = new P02_HomePage(getDriver())
                .addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();
        new P02_HomePage(getDriver()).ClickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).ComparingPrices(totalPrice));
    }

    @AfterMethod
    public void quit() {
        quitdriver();
    }

    @AfterClass
    public void deleteSession() {
        cookies.clear();
    }

}
