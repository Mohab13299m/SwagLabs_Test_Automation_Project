package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {
    private static final String screenshotsPath = "TestOutputs/Screenshots/";

    //TODO: Clicking on Element

    /**
     * Utility function for clicking after waiting to be clickable
     *
     * @param WebDriver
     * @param By
     */
    public static void clickonElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    //TODO: Sending Data

    public static void senddata(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }
    //TODO: get Data

    public static String GetText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        return driver.findElement(locator).getText();
    }


    //TODO: Selecting from dropdown
    public static void SelectingFromDropdown(WebDriver driver, By dropdown, String Option) {
        new Select(bytoWebelement(driver, dropdown)).selectByVisibleText(Option);
    }

    //TODO: Scrolling to element
    public static void Scrolling(WebDriver driver, By loc) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", bytoWebelement(driver, loc)); // argument[0] means the first element i gave it to him
    }

    //TODO: Taking ScreenShots
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenshots(WebDriver driver, String ScreenshotName) throws IOException {
        try {
            //Capture Screenshot
            File ScreenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Save screenshot to a file
            File screenDest = new File(screenshotsPath + ScreenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(ScreenSrc, screenDest);
            //attach Screen shot to allure
            Allure.addAttachment(ScreenshotName, Files.newInputStream(Path.of(screenDest.getPath())));

        } catch (Exception e) {
            LogsUtil.Error(e.getMessage());
        }
    }

    public static void takefullScreenshot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(bytoWebelement(driver, locator))
                    .save(screenshotsPath);
        } catch (Exception e) {
            LogsUtil.Error(e.getMessage());
        }
    }

    //TODO: generate randoum numbers
    public static int generateRandomNumbers(int upperbound) {
        return new Random().nextInt(upperbound) + 1;
    }

    public static Set<Integer> generateUniqueNumber(int Numberofproductsneeded, int totalnumberofproduct) {
        Set<Integer> generatedNumber = new HashSet<>();
        while (generatedNumber.size() < Numberofproductsneeded) {
            int randomNumber = generateRandomNumbers(totalnumberofproduct);
            generatedNumber.add(randomNumber);
        }
        return generatedNumber;
    }

    //TODO: Converting To by to WebElement
    public static WebElement bytoWebelement(WebDriver driver, By loc) {
        return driver.findElement(loc);
    }

    //TODO: General Wait
    public static WebDriverWait GeneralWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //TODO: Uploading Files


    /**
     * Verifying Url
     *
     * @param expectedUrl
     * @param driver
     * @return
     */
    public static boolean VerifyUrl(String expectedUrl, WebDriver driver) {
        try {
            System.out.println(expectedUrl);
            GeneralWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> Cookies) {
        for (Cookie cookie : Cookies) {
            driver.manage().addCookie(cookie);
        }
    }

}
