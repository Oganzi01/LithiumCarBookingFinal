package com.carbooking.gui.tests.all;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.HomePage;
import org.testng.annotations.Test;

public class FullWorkflowTest extends BaseTest {

    @Test
    public void fullWorkflow() {
        // Переход на сайт через потокобезопасный драйвер
        getDriver().get("http://localhost:5173");

        HomePage homePage = new HomePage(getDriver());
        // Добавь здесь шаги своего теста, используя getDriver() вместо driver
    }
}

