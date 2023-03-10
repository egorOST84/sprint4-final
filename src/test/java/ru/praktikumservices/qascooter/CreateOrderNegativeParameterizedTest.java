package ru.praktikumservices.qascooter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikumservices.qascooter.constants.OrderData;
import ru.praktikumservices.qascooter.extensions.WebDriverFactory;
import ru.praktikumservices.qascooter.pages.HomePage;

@RunWith(Parameterized.class)
public class CreateOrderNegativeParameterizedTest {
    private static WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStationName;
    private final String phoneNumber;
    private final String commentToDelivery;

    private final String button;

    public CreateOrderNegativeParameterizedTest(String firstName, String lastName, String address, String metroStationName, String phoneNumber, String commentToDelivery, String button) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStationName = metroStationName;
        this.phoneNumber = phoneNumber;
        this.commentToDelivery = commentToDelivery;
        this.button = button;
    }

    @Parameterized.Parameters(name = "{index}: {0},{1},{2},{3},{4}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Test", "Test", "Test", "Белорусская", "Test", "", ".//button[@class='Button_Button__ra12g']"},
                {"", "", "", "Минская", "","", ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"}
        };
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void checkErrorInCasOfInvalidData() {

        new HomePage(driver)
                .checkErrorInCasOfInvalidData(button, new OrderData(firstName, lastName, address, metroStationName, phoneNumber, commentToDelivery));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
