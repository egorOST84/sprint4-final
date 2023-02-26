package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.praktikumservices.qascooter.pages.HomePage;

public class CookieComponent {

    private final WebDriver driver;

    // Локатор элемента использования кук на странице
    private final By cookie = By.cssSelector(".App_CookieConsent__1yUIN");

    // Локатор кнопки принятия кук "да все привыкли"
    private final By acceptCookie = By.cssSelector(".App_CookieButton__3cvqF");

    public CookieComponent(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickAcceptCookieIfPresentOnPage() {
        if (driver.findElement(cookie).isDisplayed()) {
            driver.findElement(acceptCookie).click();
        }

        return new HomePage(driver);
    }

}
