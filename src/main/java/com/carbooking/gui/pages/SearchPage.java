package com.carbooking.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "pickup-date")
    private WebElement pickupInput;

    @FindBy(id = "dropoff-date")
    private WebElement dropoffInput;

    @FindBy(id = "search-btn")
    private WebElement searchButton;

    public void setStartDate(String date) {
        pickupInput.clear();
        pickupInput.sendKeys(date);
    }

    public void setEndDate(String date) {
        dropoffInput.clear();
        dropoffInput.sendKeys(date);
    }

    public void clickSearch() {
        searchButton.click();
    }
}