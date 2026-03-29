package com.carbooking.gui.tests;

import com.carbooking.gui.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenGoogleTest extends BaseTest {

    @Test
    public void openGoogle() {
        // Заменяем driver.get на getDriver().get
        getDriver().get("https://www.google.com");

        String title = getDriver().getTitle();
        Assert.assertTrue(title.contains("Google"), "Заголовок страницы не содержит Google");
    }
}
