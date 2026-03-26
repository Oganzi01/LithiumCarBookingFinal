package com.carbooking.taskApp.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureAttachments {


    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] saveScreenshot(WebDriver driver, String screenshotName) {
        // Делаем скриншот через Selenium и возвращаем его как массив байтов
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
