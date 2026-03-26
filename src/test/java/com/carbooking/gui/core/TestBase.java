package com.carbooking.gui.core;

import com.carbooking.gui.fw.ApplicationManager;
import com.carbooking.gui.pages.LoginPage;
import com.carbooking.gui.pages.HomePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
    protected LoginPage loginPage;
    protected HomePage homePage;

    // Вернули этот метод, чтобы старый TestListener не выдавал ошибку компиляции!
    public WebDriver getDriver() {
        return app.driver;
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        app.init(browser);

        loginPage = new LoginPage(app.driver);
        homePage = new HomePage(app.driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Если тест УПАЛ — делаем скриншот СВОИМИ РУКАМИ прямо здесь!
        if (!result.isSuccess()) {
            System.out.println("⚠️ ТЕСТ УПАЛ: " + result.getName() + " -> Начинаем захват экрана...");
            if (app.driver != null) {
                try {
                    // Фотографируем экран
                    byte[] screenshot = ((TakesScreenshot) app.driver).getScreenshotAs(OutputType.BYTES);
                    // Напрямую кладем файл в Allure
                    Allure.addAttachment("Скриншот при ошибке", "image/png", new ByteArrayInputStream(screenshot), ".png");
                    System.out.println("📸 СКРИНШОТ УСПЕШНО ДОБАВЛЕН В ОТЧЕТ!");
                } catch (Exception e) {
                    System.out.println("❌ ОШИБКА КАМЕРЫ: " + e.getMessage());
                }
            } else {
                System.out.println("❌ Драйвер мертв (null), скриншот сделать невозможно.");
            }
        }

        // Только после попытки сделать фото закрываем браузер
        if (app != null) {
            app.stop();
        }
    }
}