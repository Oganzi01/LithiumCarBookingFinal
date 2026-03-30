package com.carbooking.gui.tests;

import com.carbooking.gui.core.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Epic("Booking Validation Tests")
@Feature("Date Selection Validation")
public class BookingDateValidationTest extends BaseTest {

    @Test(description = "Проверка блокировки поиска машины на вчерашний день")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вводим прошедшую дату в поле Start Date и проверяем, что система не пускает дальше")
    public void testSearchOnPastDate() {
        getDriver().get("http://localhost:5173");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));


        LocalDate yesterday = LocalDate.now().minusDays(1);
        String pastDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {

            List<WebElement> dateInputs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[type='date']")));

            Assert.assertFalse(dateInputs.isEmpty(), "Поля для ввода даты не найдены на странице!");

            // Берем первое поле (это Start Date)
            WebElement startDateInput = dateInputs.get(0);
            startDateInput.clear();
            startDateInput.sendKeys(pastDate); // Вводим вчерашнюю дату

            // 3. Ищем кнопку "Search Availability" по её тексту и кликаем
            WebElement searchBtn = getDriver().findElement(By.xpath("//button[contains(text(), 'Search Availability')]"));
            searchBtn.click();

            // 4. ПРОВЕРКА: Как система реагирует на ошибку?
            // Вариант А: Появляется встроенная подсказка браузера (HTML5 валидация)
            String validationMsg = startDateInput.getAttribute("validationMessage");

            // Вариант Б: Мы остаемся на той же странице (URL не меняется), потому что форма не отправилась
            String currentUrl = getDriver().getCurrentUrl();

            // Проверяем, что есть сообщение об ошибке ИЛИ нас никуда не перекинуло
            boolean isBlocked = !validationMsg.isEmpty() || currentUrl.equals("http://localhost:5173/");

            Assert.assertTrue(isBlocked, "Баг: Система проигнорировала прошлую дату и попыталась выполнить поиск!");

        } catch (Exception e) {
            Assert.fail("Не удалось найти элементы формы (поля дат или кнопку). Ошибка: " + e.getMessage());
        }
    }
}