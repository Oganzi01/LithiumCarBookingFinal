package com.carbooking.gui.tests;

import com.carbooking.gui.core.BaseTest;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void testOpenBrowser() {
        driver.get("https://google.com");
        System.out.println("Браузер запущен успешно!");
    }
}