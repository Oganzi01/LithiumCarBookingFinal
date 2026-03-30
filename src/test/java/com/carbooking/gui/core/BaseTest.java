package com.carbooking.gui.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        WebDriver driver;
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThread.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Если тест упал — делаем скриншот ПЕРЕД закрытием драйвера
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
                // Пауза 500мс помогает избежать "пустых" белых скринов
                Thread.sleep(500);
                return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании скриншота: " + e.getMessage());
        }
        return new byte[0];
    }
}