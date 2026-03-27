package com.carbooking.gui.core;

import com.carbooking.gui.fw.ApplicationManager;
import com.carbooking.gui.pages.HomePage;
import com.carbooking.gui.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.lang.reflect.Method;

public class TestBase {


    protected ApplicationManager app;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser, Method method) {
        app = new ApplicationManager(browser);
        app.init();

        loginPage = new LoginPage(app.getDriver());
        homePage = new HomePage(app.getDriver());

        System.out.println("Starting test: " + method.getName() + " on browser: " + browser);
    }

    public WebDriver getDriver() {
        return app.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (app != null) {
            app.quit();
            System.out.println("Browser session closed.");
        }
    }
}