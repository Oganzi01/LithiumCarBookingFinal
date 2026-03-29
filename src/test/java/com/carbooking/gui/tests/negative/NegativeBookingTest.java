package com.carbooking.gui.tests.negative;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.BookingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeBookingTest extends BaseTest {

    @Test
    public void testInvalidBooking() {
        getDriver().get("http://localhost:5173/bookings");

        BookingPage bookingPage = new BookingPage(getDriver());

        // Проверка URL через getDriver()
        Assert.assertTrue(getDriver().getCurrentUrl().contains("bookings"),
                "URL не содержит ожидаемую часть 'bookings'");
    }
}