package com.carbooking.gui.tests;

import org.testng.annotations.AfterSuite;
import java.io.IOException;

public class ReportHook {

    @AfterSuite(alwaysRun = true)
    public void autoOpenReport() {
        try {
            Runtime.getRuntime().exec("cmd /c start mvn allure:serve");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
