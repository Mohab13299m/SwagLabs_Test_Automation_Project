package Tests;

import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P05_OverViewPage;
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
public class TC5OverViewTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    private final String First_Name = DataUtil.getJsonData("information", "fname");
    private final String Last_Name = DataUtil.getJsonData("information", "lname");
    String username = new Faker().name().username();
    String ZipCode = new Faker().number().digits(5);

    public TC5OverViewTest() throws FileNotFoundException {
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
    public void CheckoutStepTwoTestCase() throws IOException {
        new P01_LoginPage(getDriver())
                .enterusername(ValidUsername)
                .enterpassword(ValidPassword)
                .clickbutton()
                .AddallProducts()
                .ClickOnCartIcon()
                .ClickOnCheckout()
                .FillForm(First_Name, Last_Name, ZipCode)
                .clickOnContinue();
        Assert.assertTrue(new P05_OverViewPage(getDriver()).ComparingPrices());
    }

    @AfterMethod
    public void quit() {
        quitdriver();
    }

}

