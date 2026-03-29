package com.carbooking.api.auth;

import com.carbooking.api.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Security")
@Feature("Authentication")
public class LoginNegativeApiTest extends BaseApiTest {

    @Test(description = "Попытка логина с неверным паролем")
    public void loginWithInvalidPassword() {
        var response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "email": "user@gmail.com",
                                  "password": "WrongPassword123!"
                                }
                                """)
                        .when()
                        .post("/api/v3/auth/login")
                        .then()
                        .extract()
                        .response();

        // Проверяем, что сервер послал нас подальше (401 код)
        Assert.assertEquals(response.statusCode(), 401, "Статус код должен быть 401!");
    }
}