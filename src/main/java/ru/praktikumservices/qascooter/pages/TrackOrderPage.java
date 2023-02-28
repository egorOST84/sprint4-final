package ru.praktikumservices.qascooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackOrderPage {
    private final WebDriver driver;

    // Локатор для элемента кнопки "Статус заказа"
    private final By containerOrderNotFound  = By.className("Track_NotFound__6oaoY");

    public TrackOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isContainerOrderNotFoundVisible(){
        return driver.findElement(containerOrderNotFound).isDisplayed();
    }

}
