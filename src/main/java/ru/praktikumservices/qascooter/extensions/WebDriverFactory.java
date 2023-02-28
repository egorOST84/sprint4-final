package ru.praktikumservices.qascooter.extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikumservices.qascooter.config.AppConfig;
import ru.praktikumservices.qascooter.config.WebDriverConfig;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver get() {
        //String browserName = System.getenv().get("browser"); // Закомментировано, т.к. по заданию "Нужно отправить тесты на ревью с подключённым Google Chrome."
        String browserName = "chrome";

        WebDriver driver;
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT));
        driver.manage().window().fullscreen();
        driver.navigate().to(AppConfig.BASE_URL);
        return driver;
    }
}
