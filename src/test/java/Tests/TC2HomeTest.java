package Tests;

import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtil;
import Utilities.LogsUtil;
import Utilities.Utility;
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
public class TC2HomeTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    String username = new Faker().name().username();

    private Set<Cookie> cookies;

    public TC2HomeTest() throws FileNotFoundException {
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
    public void CheckingNumberofSelectedproductsTestCase() {
        new P02_HomePage(getDriver()).AddallProducts();
        Assert.assertTrue(new P02_HomePage(getDriver()).comparingNumberofSelectedProductWithCart());
    }

    @Test
    public void AddingRandomProductsTestCase() {
        new P02_HomePage(getDriver())
                .addRandomProducts(3, 6);
        Assert.assertTrue(new P02_HomePage(getDriver()).comparingNumberofSelectedProductWithCart());
    }

    @Test
    public void clickonCartIconTestCase() throws IOException {
        new P02_HomePage(getDriver())
                .ClickOnCartIcon();
        Assert.assertTrue(Utility.VerifyUrl(DataUtil.getPropertiesData("enviroments", "CartURL"), getDriver()));
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
