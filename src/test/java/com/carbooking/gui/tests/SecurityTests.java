package com.carbooking.gui.tests;

import com.carbooking.gui.core.BaseTest; // Исправлено имя
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityTests extends BaseTest { // Исправлено имя

    @Test(description = "Проверка на угрозу IDOR")
    public void findIdorThreat() {
        driver.get("https://google.com");
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL проверки безопасности: " + currentUrl);
        Assert.assertFalse(currentUrl.contains("admin"), "ОШИБКА: Доступ к админке открыт!");
    }
}