package com.carbooking.gui.tests.all;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void firstTestExample() {
        // 1. Используем getDriver() вместо driver для перехода на сайт
        getDriver().get("http://localhost:5173");

        // 2. Передаем getDriver() в конструктор страницы
        HomePage home = new HomePage(getDriver());

        Assert.assertTrue(home.isLoaded(), "Главная страница не загрузилась!");
    }
}