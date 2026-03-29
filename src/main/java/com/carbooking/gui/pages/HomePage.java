package com.carbooking.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1")
    private WebElement mainHeader;

    @FindBy(xpath = "//*[contains(translate(text(), 'login', 'LOGIN'), 'LOG IN')]")
    private WebElement loginLink;

    @FindBy(xpath = "//*[contains(translate(text(), 'logout', 'LOGOUT'), 'LOG OUT')]")
    private WebElement logoutButton;

    public void clickOnLoginLink() {
        loginLink.click();
    }

    public boolean isHomePageOpened() {
        return mainHeader.isDisplayed();
    }

    public boolean isLogoutButtonPresent() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public boolean isLoaded() {
        return mainHeader.isDisplayed();
    }
}