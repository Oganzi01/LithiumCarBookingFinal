package com.carbooking.api.core;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("User Feedback")
@Feature("Reviews")
public class GetReviewsApiTest extends BaseApiTest {

    @Test(description = "Получение списка отзывов")
    public void getAllReviews() {
        var response = given()
                .filter(new AllureRestAssured())
                .when()
                .get("/api/v3/reviews")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Ошибка при получении отзывов");
    }
}
