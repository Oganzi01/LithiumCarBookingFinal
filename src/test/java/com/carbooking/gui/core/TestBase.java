package com.carbooking.gui.core;

import com.carbooking.gui.fw.ApplicationManager;
import com.carbooking.gui.pages.HomePage;
import com.carbooking.gui.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser, Method method) {
        // Инициализируем менеджер
        app = new ApplicationManager(browser);
        app.init();

        // Передаем драйвер в страницы через метод getDriver()
        loginPage = new LoginPage(app.getDriver());
        homePage = new HomePage(app.getDriver());

        System.out.println("Starting test: " + method.getName() + " on browser: " + browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (app != null) {
            app.quit();
        }
    }
}