package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeoptions = new ChromeOptions();
                chromeoptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(chromeoptions));
                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions edgeoptions = new EdgeOptions();
                edgeoptions.addArguments("--start-maximized");// it will open the browser in Maxmize mode
                driverThreadLocal.set(new EdgeDriver(edgeoptions));
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitdriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
