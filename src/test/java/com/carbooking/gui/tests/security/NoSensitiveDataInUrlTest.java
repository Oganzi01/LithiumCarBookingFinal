package com.carbooking.gui.tests.security;

import com.carbooking.gui.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NoSensitiveDataInUrlTest extends BaseTest {

    @Test
    public void testUrlSecurity() {
        getDriver().get("http://localhost:5173");

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("password"),
                "Критическая ошибка: URL содержит конфиденциальные данные (пароль)!");
    }
}