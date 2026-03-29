package com.carbooking.gui.tests.positive;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void userCanRegisterSuccessfully() {
        RegistrationPage reg = new RegistrationPage(getDriver());

        reg.register(
                "Oleh",
                "QA",
                "oleh" + System.currentTimeMillis() + "@test.com",
                "123456"
        );

        Assert.assertTrue(
                reg.isRegistrationSuccessful(),
                "Регистрация не была подтверждена"
        );
    }
}
