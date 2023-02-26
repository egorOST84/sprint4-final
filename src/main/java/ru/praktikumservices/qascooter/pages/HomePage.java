package ru.praktikumservices.qascooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.config.WebDriverConfig;
import ru.praktikumservices.qascooter.constants.OrderData;
import ru.praktikumservices.qascooter.pages.component.CookieComponent;
import ru.praktikumservices.qascooter.pages.component.FaqComponent;
import ru.praktikumservices.qascooter.pages.component.LogoComponent;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    // Локатор для элемента кнопки "Статус заказа"
    private final By btnMakeOrderStatus = By.className("Header_Link__1TAG7");

    //  Локатор для input-поля поиска заказа по его номеру
    private final By inputOrderNumber = By.cssSelector(".Input_Input__1iN_Z");

    // Локатор для кнопки Go! для выполнения поиска
    private final By btnOrderSearch = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    private final LogoComponent logo;
    private final FaqComponent faq;
    private final CookieComponent cookie;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.logo = new LogoComponent(driver);
        this.faq = new FaqComponent(driver);
        this.cookie = new CookieComponent(driver);
    }

    public FaqComponent getFaq() {
        return faq;
    }

    public LogoComponent getLogo() {
        return logo;
    }

    public HomePage clickOrderStatusButton() {
        driver.findElement(btnMakeOrderStatus).click();
        return this;
    }

    public HomePage setOrderNumber(String orderNumber) {
        driver.findElement(inputOrderNumber).sendKeys(orderNumber);
        return this;
    }

    public HomePage waitForLoadOrderSearchButton() {
        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(btnOrderSearch));
        return this;
    }

    public HomePage waitForOrderBtnIsLoaded(String btnName) {
        new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btnName)));
        return this;
    }

    public OrderPage clickOrderCreateButton(String btnName) {
        driver.findElement(By.xpath(btnName)).click(); //
        return new OrderPage(driver);
    }
    public TrackOrderPage clickOrderSearchButton() {
        driver.findElement(btnOrderSearch).click();
        return new TrackOrderPage(driver);
    }

    public void createOrderBy(String btnName, OrderData data) {

        this.cookie.clickAcceptCookieIfPresentOnPage()
            .waitForOrderBtnIsLoaded(btnName)
            .clickOrderCreateButton(btnName)
            .waitOrderStepOnePageIsLoaded(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT)
            .fillInOrderApplicationFormStepOne(data)
            .clickBtnForward()
            .fillInOrderApplicationFormStepTwo(data)
            .clickBtnPlaceOrder()
            .waitForOrderModalOpened(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT)
            .clickCreateOrderYes()
            .checkIsOrderCreated();
    }

    public void checkErrorInCasOfInvalidData(String btnName, OrderData data){

        this.cookie.clickAcceptCookieIfPresentOnPage()
            .waitForOrderBtnIsLoaded(btnName)
            .clickOrderCreateButton(btnName)
            .waitOrderStepOnePageIsLoaded(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT)
            .fillInOrderApplicationFormStepOne(data)
            .checkErrorMessage();
    }
}
