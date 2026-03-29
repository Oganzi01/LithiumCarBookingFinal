package com.carbooking.gui.tests.positive;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageLoads() {
        // 1. Сначала ПЕРЕХОДИМ на сайт (используем getDriver())
        getDriver().get("http://localhost:5173");

        // 2. Потом проверяем
        HomePage home = new HomePage(getDriver());
        Assert.assertTrue(home.isLoaded(), "Главная страница не загрузилась");
    }
}
