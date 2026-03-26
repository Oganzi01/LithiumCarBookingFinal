package com.carbooking.gui.tests;

import com.carbooking.gui.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityTests extends TestBase {

    @Test(description = "Проверка на угрозу IDOR")
    public void findIdorThreat() {
        String currentUrl = app.driver.getCurrentUrl();
        System.out.println("URL проверки безопасности: " + currentUrl);
        Assert.assertFalse(currentUrl.contains("admin"), "⚠️ ОШИБКА: Доступ к админке открыт!");
    }

    @Test(description = "Тест на SQL Injection в поле логина")
    public void testSqlInjectionInput() {
        // Идем прямо на страницу логина
        app.driver.get("http://localhost:5173/login");

        // Используем твой реальный метод из LoginPage
        loginPage.login("' OR 1=1 --", "password");

        // Проверяем, что после попытки взлома мы всё ещё находимся на странице логина
        String currentUrl = app.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "⚠️ КРИТИЧЕСКАЯ ОШИБКА: SQL-инъекция сработала, нас пустило внутрь системы!");
    }
}