package com.carbooking.gui.tests.negative;

import com.carbooking.gui.core.BaseTest;
import com.carbooking.gui.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeSearchTest extends BaseTest {

    @Test
    public void testInvalidSearch() {
        getDriver().get("http://localhost:5173/search");

        SearchPage searchPage = new SearchPage(getDriver());

        // Пример проверки
        Assert.assertTrue(getDriver().getCurrentUrl().contains("search"),
                "Пользователь не находится на странице поиска");
    }
}