package com.carbooking.taskApp.utils;

import com.carbooking.gui.core.TestBase;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("⚠️ СЛУШАТЕЛЬ: Тест упал -> " + result.getName());
        takePhoto(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ СЛУШАТЕЛЬ: Тест сломался -> " + result.getName());
        takePhoto(result);
    }

    private void takePhoto(ITestResult result) {
        // Получаем текущий класс теста напрямую
        Object currentClass = result.getInstance();

        // Проверяем, что это наш UI тест (наследник TestBase)
        if (currentClass instanceof TestBase) {
            // Берем драйвер прямо из теста
            WebDriver driver = ((TestBase) currentClass).getDriver();

            if (driver != null) {
                try {
                    // Жестко делаем скриншот и напрямую пихаем его в Allure
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Скриншот падения", "image/png", new ByteArrayInputStream(screenshot), ".png");
                    System.out.println("📸 Скриншот ЖЕСТКО добавлен в Allure!");
                } catch (Exception e) {
                    System.out.println("❌ Ошибка при снятии скриншота: " + e.getMessage());
                }
            } else {
                System.out.println("❌ Скриншот не сделан: драйвер равен null.");
            }
        }
    }
}