package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikumservices.qascooter.extensions.WebDriverFactory;
import ru.praktikumservices.qascooter.pages.HomePage;

import static org.junit.Assert.assertTrue;


public class HomePageFaqTest {
    private static WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }
    @Test
    public void checkFaqComponentIsWorkingCorrect() {
        boolean isFaqComponentWorksCorrect = new HomePage(driver)
                .getFaq()
                .checkFaqComponentIsValidTrue();
        assertTrue(isFaqComponentWorksCorrect);
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
