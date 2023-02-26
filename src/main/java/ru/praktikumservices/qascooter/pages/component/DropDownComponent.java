package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.constants.RentPeriod;

import java.time.Duration;
import java.util.Random;

public class DropDownComponent {
    private final WebDriver driver;
    private final By dropDownRendPeriodMenu = By.cssSelector(".Dropdown-placeholder");
    private final By openedRentPeriodMenu = By.xpath(".//div[@class='Dropdown-menu' and @aria-expanded='true']");

    public DropDownComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRandomOption() {
        Random random = new Random();
        int optionNum = random.nextInt(8);
        if (optionNum != 0) {
            driver.findElement(By.xpath(". //div[@class='Dropdown-menu']/div[" + optionNum + "]")).click();
        } else {
            driver.findElement(By.xpath(". //div[@class='Dropdown-menu']/div[" + RentPeriod.DEFAULT_PERIOD + "]")).click();
        }
    }

    public void selectDropDownMenu() {
        driver.findElement(dropDownRendPeriodMenu).click();
        waitForElementVisibility(driver.findElement(openedRentPeriodMenu), 10);
        selectRandomOption();
    }

    public void waitForElementVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
}
