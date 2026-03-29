package com.carbooking.api.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureAttachments {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return driver != null
                ? ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
                : new byte[0];
    }

    @Attachment(value = "Page HTML", type = "text/html")
    public static String attachPageHtml(WebDriver driver) {
        return driver != null ? driver.getPageSource() : "Driver is null";
    }

    @Attachment(value = "Local Storage", type = "application/json")
    public static String attachLocalStorage(WebDriver driver) {
        if (driver == null) return "{}";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
                "var items = {}, ls = window.localStorage;" +
                        "for (var i = 0; i < ls.length; i++) {" +
                        "  var key = ls.key(i); items[key] = ls.getItem(key);" +
                        "}" +
                        "return JSON.stringify(items);"
        );
    }

    @Attachment(value = "Session Storage", type = "application/json")
    public static String attachSessionStorage(WebDriver driver) {
        if (driver == null) return "{}";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
                "var items = {}, ss = window.sessionStorage;" +
                        "for (var i = 0; i < ss.length; i++) {" +
                        "  var key = ss.key(i); items[key] = ss.getItem(key);" +
                        "}" +
                        "return JSON.stringify(items);"
        );
    }

    @Attachment(value = "API Response", type = "text/plain")
    public static String attachApiResponse(String response) {
        return response == null ? "No API response" : response;
    }
}