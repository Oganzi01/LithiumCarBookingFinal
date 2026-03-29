package com.carbooking.api.bookings;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Booking System")
@Feature("Create Booking")
public class CreateBookingNegativeApiTest extends BaseApiTest {

    @Test(description = "Попытка создать бронь без токена авторизации")
    public void createBookingWithoutTokenReturns401() {
        var response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "carId": "12345",
                                  "startDate": "2026-12-01",
                                  "endDate": "2026-12-05"
                                }
                                """)
                        .when()
                        .post("/api/v3/bookings")
                        .then()
                        .extract()
                        .response();

        // Должен быть 401 Unauthorized или 403 Forbidden
        Assert.assertTrue(
                response.statusCode() == 401 || response.statusCode() == 403,
                "Сервер разрешил создать бронь без токена!"
        );
    }
}
