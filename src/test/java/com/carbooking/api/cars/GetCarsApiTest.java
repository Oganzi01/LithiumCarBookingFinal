package com.carbooking.api.cars;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Car Management")
@Feature("Get Cars")
public class GetCarsApiTest extends BaseApiTest {

    @Test(description = "Получение списка всех автомобилей")
    public void getAllCars() {
        var response = given()
                .filter(new AllureRestAssured())
                .when()
                .get("/api/v3/cars")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Ошибка при получении списка машин");
    }
}