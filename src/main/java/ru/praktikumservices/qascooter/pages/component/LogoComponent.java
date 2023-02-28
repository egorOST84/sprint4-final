package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.config.WebDriverConfig;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LogoComponent {
    private final WebDriver driver;

    // Логотип Яндекс
    private final By yandexLogo = By.cssSelector(".Header_LogoYandex__3TSOI");

    public LogoComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void waitNewWindowIsOpenedAfterClick() {
        String originalWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));

        assert driver.getWindowHandles().size() == 1;

        clickYandexLogo();

        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(titleIs("Дзен"));
    }
}
