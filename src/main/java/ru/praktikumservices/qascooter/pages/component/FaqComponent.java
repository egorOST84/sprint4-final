package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.config.WebDriverConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class FaqComponent {

    private final WebDriver driver;

    // Локатор для элемента со списком вопросов и ответов в разделе "Вопросы о важном"
    private final By questionContainer = By.cssSelector(".Home_FourPart__1uthg");

    public FaqComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void checkFaqComponentIsValidTrue(int questionNum, String expectedQuestion, String expectedAnswer) throws RuntimeException {

        String questionItem = "accordion__heading-%s";
        WebElement questionElement = driver.findElement(By.id(String.format(questionItem, questionNum)));
        String answerItem = "accordion__panel-%s";
        WebElement answerElement = driver.findElement(By.id(String.format(answerItem, questionNum)));

        waitForElementVisibility(driver.findElement(questionContainer), WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT);
        scrollToElement(questionElement);
        waitForElementIsClickable(questionElement, WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT);
        questionElement.click();
        waitForElementVisibility(answerElement, WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT);

        assertEquals("Заданный вопрос: \"" + questionElement.getText() + "\" не найден", expectedQuestion, questionElement.getText());
        assertEquals("Заданный ответ: \"" + answerElement.getText() + "\" не найден", expectedAnswer, answerElement.getText());

    }

    public void waitForElementVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void waitForElementIsClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // вынести отдельно?
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}