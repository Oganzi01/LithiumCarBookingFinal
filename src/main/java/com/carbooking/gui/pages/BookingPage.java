package com.carbooking.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage {

    private final WebDriver driver;

    private final By pickupInput = By.id("pickup-date");
    private final By dropoffInput = By.id("dropoff-date");
    private final By searchButton = By.id("search-btn");
    private final By firstCar = By.cssSelector(".car-card:first-child");
    private final By successMessage = By.id("booking-success");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillDatesAndSearch(String pickup, String dropoff) {
        driver.findElement(pickupInput).sendKeys(pickup);
        driver.findElement(dropoffInput).sendKeys(dropoff);
        driver.findElement(searchButton).click();
    }

    public void clickFirstAvailableCar() {
        driver.findElement(firstCar).click();
    }

    public boolean isBookingSuccessful() {
        return driver.findElements(successMessage).size() > 0;
    }
}