package Tests;


import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P06_FinishOrder;
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
public class TC6FinishingMessageTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    private final String First_Name = DataUtil.getJsonData("information", "fname");
    private final String Last_Name = DataUtil.getJsonData("information", "lname");
    String username = new Faker().name().username();
    String ZipCode = new Faker().number().digits(5);

    private Set<Cookie> cookies;

    public TC6FinishingMessageTest() throws FileNotFoundException {
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
    public void FinishingOrderMessage() throws IOException {
        new P02_HomePage(getDriver())
                .AddallProducts()
                .ClickOnCartIcon()
                .ClickOnCheckout()
                .FillForm(First_Name, Last_Name, ZipCode)
                .clickOnContinue()
                .clickOnFinish();
        Assert.assertTrue(new P06_FinishOrder(getDriver()).VisibilityOfThanksMessgage());
    }

    @AfterMethod
    public void quit() {
        quitdriver();
    }

}



