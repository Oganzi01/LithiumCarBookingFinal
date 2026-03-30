package com.carbooking.gui.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driverThread.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        boolean isCI = System.getenv("CI") != null; // GitHub Actions sets CI=true
        WebDriver driver;

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (isCI) {
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }

            driver = new FirefoxDriver(options);

        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThread.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot();
        }

        if (getDriver() != null) {
            getDriver().quit();
            driverThread.remove();
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] captureScreenshot() {
        try {
            if (getDriver() != null) {
                Thread.sleep(500);
                return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception ignored) {}
        return new byte[0];
    }
}