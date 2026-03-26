package com.carbooking.taskApp.api.config;

import com.carbooking.taskApp.utils.LogUtil;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite
    public void setup() {
        LogUtil.info("Установка базовых настроек API...");
        // Указываем базовый адрес сервера
        RestAssured.baseURI = "https://car-booking-back.herokuapp.com";

        LogUtil.info("Base URL установлен: " + RestAssured.baseURI);
    }
}