package com.carbooking.gui.tests.negative;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeRegistrationTest extends BaseTest {

    @Test
    public void registrationWithInvalidEmail() {
        RegistrationPage reg = new RegistrationPage(getDriver());

        reg.register(
                "Oleh",
                "QA",
                "not-an-email",   // неверный email
                "123456"
        );

        boolean success = reg.isRegistrationSuccessful();

        Assert.assertFalse(success, "Регистрация прошла с неверным email — это ошибка");
    }

    @Test
    public void registrationWithEmptyFields() {
        RegistrationPage reg = new RegistrationPage(getDriver());

        reg.register(
                "",
                "",
                "",
                ""
        );

        boolean success = reg.isRegistrationSuccessful();

        Assert.assertFalse(success, "Регистрация прошла с пустыми полями — это ошибка");
    }
}