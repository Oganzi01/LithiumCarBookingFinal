package com.carbooking.gui.tests.positive;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.BookingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {

    @Test
    public void bookingCarSuccessfully() {
        BookingPage booking = new BookingPage(getDriver());

        // 1. Заполняем даты и ищем машины
        booking.fillDatesAndSearch("01.04.2026", "05.04.2026");

        // 2. Кликаем по первой доступной машине
        booking.clickFirstAvailableCar();

        // 3. Проверяем успешность бронирования
        Assert.assertTrue(
                booking.isBookingSuccessful(),
                "Бронирование не было подтверждено"
        );
    }
}



