package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ru.praktikumservices.qascooter.extensions.WebDriverFactory;
import ru.praktikumservices.qascooter.pages.HomePage;

import static org.junit.Assert.assertTrue;
public class OrderStatusTest {


    private static WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void checkErrorInCasOfNonexistentOrder() {
        String incorrectOrderNumber = "5";
        boolean isContainerOrderNotFoundVisible = new HomePage(driver)
                .clickOrderStatusButton()
                .setOrderNumber(incorrectOrderNumber)
                .waitForLoadOrderSearchButton() //добавлено ожидание для firefox иначе ошибка org.openqa.selenium.ElementNotInteractableException could not be scrolled into view
                .clickOrderSearchButton()
                .isContainerOrderNotFoundVisible();
        assertTrue(isContainerOrderNotFoundVisible);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
