package com.carbooking.api.cars;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Car Management")
@Feature("Car Categories")
public class GetCarCategoriesApiTest extends BaseApiTest {

    @Test(description = "Получение списка категорий автомобилей")
    public void getCarCategories() {
        var response = given()
                .filter(new AllureRestAssured())
                .when()
                .get("/api/v3/car-categories")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Ошибка при получении категорий машин");
    }
}
