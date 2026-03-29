package com.carbooking.gui.tests.positive;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Успешный вход в систему с валидными данными")
    public void loginWithValidCredentials() {
        // 1. Открываем страницу логина
        getDriver().get("http://localhost:5173/login");

        // 2. Инициализируем страницу (передаем getDriver() в конструктор)
        LoginPage loginPage = new LoginPage(getDriver());

        // 3. Выполняем действия
        loginPage.login("user@gmail.com", "123456");

        // 4. Проверка: URL должен измениться после входа
        boolean isRedirected = getDriver().getCurrentUrl().contains("home")
                || getDriver().getCurrentUrl().contains("dashboard");

        Assert.assertTrue(isRedirected, "После логина не произошел переход на главную страницу/дашборд");
    }
}
