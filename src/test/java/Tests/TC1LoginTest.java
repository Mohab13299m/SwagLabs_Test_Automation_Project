package Tests;

import Listeners.InvokedMethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Utilities.DataUtil;
import Utilities.LogsUtil;
import Utilities.Utility;
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
public class TC1LoginTest {
    private final String ValidUsername = DataUtil.getJsonData("Validlogindata", "username");
    private final String ValidPassword = DataUtil.getJsonData("Validlogindata", "password");
    String username = new Faker().name().username();

    public TC1LoginTest() throws FileNotFoundException {
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
    public void ValidLoginTc1() throws IOException {
        new P01_LoginPage(getDriver())
                .enterusername(ValidUsername)
                .enterpassword(ValidPassword)
                .clickbutton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).assertloginTC(getPropertiesData("enviroments", "HomeUrl")));
    }

    @Test
    public void LogoutTc1() throws IOException {
        new P01_LoginPage(getDriver())
                .enterusername(ValidUsername)
                .enterpassword(ValidPassword)
                .clickbutton()
                .OpenMenu()
                .Logout();
        Assert.assertTrue(Utility.VerifyUrl(DataUtil.getPropertiesData("enviroments", "BaseUrl"), getDriver()));
    }


    @AfterMethod
    public void quit() {
        quitdriver();
    }
}
