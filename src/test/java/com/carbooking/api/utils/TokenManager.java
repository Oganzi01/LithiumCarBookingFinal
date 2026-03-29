package com.carbooking.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TokenManager {

    public static String getToken(String email, String password) {

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "%s",
                          "password": "%s"
                        }
                        """.formatted(email, password))
                .when()
                .post("/api/v3/auth/login")
                .then()
                .extract()
                .jsonPath()
                .getString("token");
    }
}