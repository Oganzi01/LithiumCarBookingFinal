package com.carbooking.api.core;

import com.carbooking.api.utils.ApiLoggingFilter;
import com.carbooking.api.utils.TokenManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected String token;

    @BeforeClass
    public void setupApi() {

        RestAssured.baseURI = "https://dev.pshacks.org";

        RestAssured.filters(
                new AllureRestAssured(),
                new ApiLoggingFilter()
        );

        token = TokenManager.getToken("user@gmail.com", "123456");
    }
}