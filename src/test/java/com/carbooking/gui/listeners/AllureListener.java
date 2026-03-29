package com.carbooking.gui.listeners;

import com.carbooking.gui.core.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (BaseTest.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", "image/png",
                    new ByteArrayInputStream(screenshot), ".png");
        }
    }
}