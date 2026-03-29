package com.carbooking.gui.tests.negative;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest extends BaseTest {

    @Test
    public void testInvalidLogin() {
        getDriver().get("http://localhost:5173/login");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("wrong@mail.com", "wrongpass");

        // Проверка содержимого страницы
        Assert.assertTrue(getDriver().getPageSource().contains("Invalid credentials"),
                "Сообщение об ошибке входа не найдено на странице");
    }
}