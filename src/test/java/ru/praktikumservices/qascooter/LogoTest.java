package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikumservices.qascooter.extensions.WebDriverFactory;
import ru.praktikumservices.qascooter.pages.HomePage;

public class LogoTest {
    private static WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void checkNewWindowIsOpenedByClickingLogo() {
        new HomePage(driver)
                .getLogo().waitNewWindowIsOpenedAfterClick();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
