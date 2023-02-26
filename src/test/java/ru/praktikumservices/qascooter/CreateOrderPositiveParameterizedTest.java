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
public class CreateOrderPositiveParameterizedTest {
    private static WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStationName;
    private final String phoneNumber;
    private final String commentToDelivery;

    public CreateOrderPositiveParameterizedTest(String firstName, String lastName, String address, String metroStationName, String phoneNumber, String commentToDelivery) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStationName = metroStationName;
        this.phoneNumber = phoneNumber;
        this.commentToDelivery = commentToDelivery;
    }

    @Parameterized.Parameters(name = "{index}: {0},{1},{2},{3},{4},{5}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва", "Белорусская", "+79501000001", "Комментарий для курьера 1"},
                {"Петр", "Петров", "Санкт-Петербург", "Минская", "+79501000002", "Комментарий для курьера 2"}
        };
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void checkCreateOrderWithBtnInHeader() {
        String btnOrderInHeader = ".//button[@class='Button_Button__ra12g']";
        new HomePage(driver)
                .createOrderBy(btnOrderInHeader, new OrderData(firstName, lastName, address, metroStationName, phoneNumber, commentToDelivery));
    }

    @Test
    public void checkCreateOrderWithBtnInFooter() {
        String btnOrderInFooter = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']";
        new HomePage(driver)
                .createOrderBy(btnOrderInFooter, new OrderData(firstName, lastName, address, metroStationName, phoneNumber, commentToDelivery));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
