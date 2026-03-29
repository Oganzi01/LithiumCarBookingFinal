package com.carbooking.api.bookings;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Booking System")
@Feature("User Bookings")
public class GetUserBookingsApiTest extends BaseApiTest {

    @Test(description = "Попытка получить бронирования пользователя без авторизации")
    public void getMyBookingsWithoutAuth() {
        var response = given()
                .filter(new AllureRestAssured())
                .when()
                .get("/api/v3/users/me/bookings")
                .then()
                .extract()
                .response();

        // Проверяем, что сервер вернул ошибку авторизации (401), а не отдал чужие данные
        Assert.assertEquals(response.statusCode(), 401, "Сервер отдал данные без токена авторизации!");
    }
}