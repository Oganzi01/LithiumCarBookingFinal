package com.carbooking.gui.tests.positive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchTest {

    private final WebDriver driver;

    // Локаторы такие же, как в BookingPage
    private final By pickupInput = By.id("pickup-date");
    private final By dropoffInput = By.id("dropoff-date");
    private final By searchButton = By.id("search-btn");

    public SearchTest(WebDriver driver) {
        this.driver = driver;
    }

    public void setStartDate(String date) {
        driver.findElement(pickupInput).clear();
        driver.findElement(pickupInput).sendKeys(date);
    }

    public void setEndDate(String date) {
        driver.findElement(dropoffInput).clear();
        driver.findElement(dropoffInput).sendKeys(date);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean isLoaded() {
        return driver.findElements(searchButton).size() > 0;
    }
}





