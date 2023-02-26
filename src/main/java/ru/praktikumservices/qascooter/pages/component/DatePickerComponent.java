package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.WebDriver;

import java.util.Calendar;

public class DatePickerComponent {
    private final WebDriver driver;

    public DatePickerComponent(WebDriver driver) {
        this.driver = driver;
    }

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);

        return Integer.toString(todayInt);
    }
}