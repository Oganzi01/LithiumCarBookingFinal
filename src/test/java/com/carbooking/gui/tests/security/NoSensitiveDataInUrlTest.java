package com.carbooking.gui.tests.security;

import com.carbooking.gui.core.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Security UI Tests")
@Feature("Vulnerability and Privacy Check")
public class NoSensitiveDataInUrlTest extends BaseTest {

    @Test(description = "Проверка отсутствия чувствительных данных в URL")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет, что после навигации URL страницы не содержит паролей или токенов в открытом виде")
    public void testUrlSecurity() {
        getDriver().get("http://localhost:5173");
        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertFalse(currentUrl.contains("password"),
                "Критическая ошибка: URL содержит конфиденциальные данные (пароль)!");
    }

    @Test(description = "Проверка защиты от XSS-инъекций")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Вводим вредоносный скрипт в поле ввода и проверяем, что браузер не исполнил код (алерт не появился)")
    public void testXSSInjection() {
        getDriver().get("http://localhost:5173");
        String xssPayload = "<script>alert('xss')</script>";

        try {
            // Ищем любое поле ввода (input)
            WebElement input = getDriver().findElement(By.cssSelector("input"));
            input.clear();
            input.sendKeys(xssPayload);
        } catch (Exception e) {
            Assert.fail("Поле ввода для теста XSS не найдено! Проверьте локаторы на главной странице.");
        }

        // Проверка на наличие Alert окна
        boolean alertAppeared = false;
        try {
            getDriver().switchTo().alert().accept();
            alertAppeared = true;
        } catch (NoAlertPresentException e) {
            alertAppeared = false;
        }

        Assert.assertFalse(alertAppeared, "Security Failure: Сайт позволил исполнить сторонний JavaScript код!");
    }

    @Test(description = "Проверка маскирования пароля в DOM")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяем, что поле ввода пароля имеет атрибут type='password' для скрытия символов")
    public void testPasswordMasking() {
        // Переходим на страницу, где ТОЧНО есть поле пароля (логин или регистрация)
        getDriver().get("http://localhost:5173/login");

        try {
            // Ищем поле пароля по атрибуту type или name
            WebElement passwordField = getDriver().findElement(By.name("password"));
            String typeAttribute = passwordField.getAttribute("type");

            Assert.assertEquals(typeAttribute, "password",
                    "Ошибка конфиденциальности: Пароль отображается в открытом виде (type != password)!");
        } catch (Exception e) {
            Assert.fail("Тест не выполнен: Поле пароля не найдено по пути /login. Проверьте URL страницы входа.");
        }
    }
}