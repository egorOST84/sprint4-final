package ru.praktikumservices.qascooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.constants.Colour;
import ru.praktikumservices.qascooter.constants.ErrorMessage;
import ru.praktikumservices.qascooter.constants.OrderData;
import ru.praktikumservices.qascooter.pages.component.DropDownComponent;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.praktikumservices.qascooter.pages.component.DatePickerComponent.getCurrentDay;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы для полей формы заказа
    // Шаг 1
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationSelectField = By.cssSelector(".select-search__input");
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Шаг 2
    private final By dateToDelivery = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By commentToDelivery = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Шаг 3
    // Локатор для модального окна на последнем шаге создания заказа
    private final By orderModal = By.cssSelector(".Order_Modal__YZ-d3");
    private final By orderSuccessCreatedModal = By.xpath(".//div[contains(text(),'Заказ оформлен')]");


    // Локатор контейнера с сообщениями ошибок
    private final By errorMessageContainer = By.cssSelector(".Input_ErrorMessage__3HvIb.Input_Visible___syz6");

    // Кнопки
    // Локатор для кнопки "Далее" на 1 шаге
    private final By btnForward = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Локатор для кнопки "Заказать"
    private final By btnOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для кнопки "Да" в модальном окне на 3 шаге
    private final By btnOrderYes = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[2]/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final DropDownComponent dpRentPeriodMenu;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.dpRentPeriodMenu = new DropDownComponent(driver);
    }

    public OrderPage fillInOrderApplicationFormStepOne(OrderData data) {
        inputFirstNameInOrder(data.getFirstName());
        inputLastNameInOrder(data.getLastName());
        inputAddressInOrder(data.getAddress());
        chooseMetroStationByName(data.getMetroStationName());
        inputPhoneNumberInOrder(data.getPhoneNumber());

        return this;
    }

    public OrderPage fillInOrderApplicationFormStepTwo(OrderData data) {
        selectDateToDelivery(getCurrentDay());
        dpRentPeriodMenu.selectDropDownMenu();
        chooseScooterColourInCheckBox();
        inputCommentToDelivery(data.getCommentToDelivery());

        return this;
    }

    public void inputFirstNameInOrder(String firstName) {
        driver.findElement(this.firstName).sendKeys(firstName);
    }

    public void inputLastNameInOrder(String lastName) {
        driver.findElement(this.lastName).sendKeys(lastName);
    }

    public void inputAddressInOrder(String address) {
        driver.findElement(this.address).sendKeys(address);
    }

    public void inputPhoneNumberInOrder(String phoneNumber) {
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }

    public void selectDateToDelivery(String day) {
        driver.findElement(dateToDelivery).click();
        driver.findElement(By.xpath(".//div[contains(@class, 'react-datepicker__day') and text()='" + day + "']")).click();
    }

    public void inputCommentToDelivery(String comment) {
        driver.findElement(commentToDelivery).sendKeys(comment);
    }

    public void chooseMetroStationByName(String stationName) {
        By stationElement = By.xpath("//li//div[text() = '" + stationName + "']");

        clickMetroStationInputField();
        scrollToElement(stationElement);
        chooseElement(stationElement);
    }

    public void chooseElement(By locator) {
        driver.findElement(locator).click();
    }

    public void chooseScooterColourInCheckBox() {
        Random random = new Random();
        int colourOption = random.nextInt(2);
        By colourElement = By.xpath("//input[@id='" + getRandomScooterColour(colourOption) + "']");

        chooseElement(colourElement);
    }

    public void clickMetroStationInputField() {
        driver.findElement(metroStationSelectField).click();
    }

    public OrderPage clickBtnForward() {
        driver.findElement(btnForward).click();
        return this;
    }

    public OrderPage clickBtnPlaceOrder() {
        driver.findElement(btnOrder).click();
        return this;
    }

    public OrderPage clickCreateOrderYes() {
        driver.findElement(btnOrderYes).click();
        return this;
    }

    public void checkIsOrderCreated() {
        assertTrue(driver.findElement(orderSuccessCreatedModal).isDisplayed());
    }

    // вынести отдельно?
    public void scrollToElement(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    public static String getRandomScooterColour(int elInCheckBox) {
        if (elInCheckBox == 0) {
            return Colour.SCOOTER_COLOUR_BLACK;
        } else if (elInCheckBox == 1) {
            return Colour.SCOOTER_COLOUR_GREY;
        } else {
            throw new RuntimeException("Unknown colour option: " + elInCheckBox);
        }
    }

    public void waitForElementVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public OrderPage waitOrderStepOnePageIsLoaded(int timeout) {
        waitForElementVisibility(driver.findElement(firstName), timeout);
        waitForElementVisibility(driver.findElement(lastName), timeout);
        waitForElementVisibility(driver.findElement(address), timeout);
        waitForElementVisibility(driver.findElement(metroStationSelectField), timeout);
        waitForElementVisibility(driver.findElement(phoneNumber), timeout);

        return this;
    }

    public OrderPage waitForOrderModalOpened(int timeout) {
        waitForElementVisibility(driver.findElement(orderModal), timeout);
        return this;
    }

    public void checkErrorMessage() {
        List<WebElement> errors = driver.findElements(errorMessageContainer);
        String[] expectedErrors = {
                ErrorMessage.ERROR_FIRST_NAME,
                ErrorMessage.ERROR_LAST_NAME,
                ErrorMessage.ERROR__ADDRESS,
                ErrorMessage.ERROR_PHONE_NUMBER
        };

        for (int i = 0; i < errors.size(); i++) {
            checkTextErrorMessage(errors.get(i), expectedErrors[i]);
            checkColorErrorMessage(errors.get(i));
        }
    }

    private void checkTextErrorMessage(WebElement error, String expectedMessage) {
        String actualText = error.getText();
        assertEquals(expectedMessage, actualText);
    }

    public void checkColorErrorMessage(WebElement error) {
        String actualColour = error.getCssValue("color");
        assertEquals(Colour.ERROR_MESSAGE_COLOR, Color.fromString(actualColour).asHex());
    }
}
