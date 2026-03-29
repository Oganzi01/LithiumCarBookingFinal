package com.carbooking.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;

    @FindBy(id = "registration-success")
    private WebElement successMessage;

    public void register(String first, String last, String email, String password) {
        firstNameInput.sendKeys(first);
        lastNameInput.sendKeys(last);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        registerButton.click();
    }

    public boolean isRegistrationSuccessful() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}