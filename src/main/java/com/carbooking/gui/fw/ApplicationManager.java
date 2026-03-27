package com.carbooking.gui.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class ApplicationManager {

    private WebDriver driver;
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:5173");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}