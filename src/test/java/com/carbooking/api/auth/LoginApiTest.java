package com.carbooking.api.auth;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Epic("API Tests")
@Feature("Auth")
public class LoginApiTest extends BaseApiTest {

    @Test(description = "API: Успешная авторизация пользователя")
    public void loginApiSuccess() {
        given()
                .filter(new AllureRestAssured()) // Логи запроса и ответа для Allure
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "user@gmail.com",
                          "password": "123456"
                        }
                        """)
                .when()
                .post("/api/v3/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue()); // Проверяем, что в ответе пришел токен
    }
}