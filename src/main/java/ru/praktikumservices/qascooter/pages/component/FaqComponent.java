package ru.praktikumservices.qascooter.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikumservices.qascooter.config.WebDriverConfig;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.praktikumservices.qascooter.constants.Question.*;
import static ru.praktikumservices.qascooter.constants.Answer.*;

public class FaqComponent {

    private final WebDriver driver;

    // Локатор для элемента с вопросом в разделе "Вопросы о важном"
    private final By question = By.cssSelector(".accordion__button");

    // Локатор для элемента с ответом в разделе "Вопросы о важном"
    private final By answer = By.xpath(".//*[@class='accordion__panel']/p");

    public FaqComponent(WebDriver driver) {
        this.driver = driver;
    }

    public static String getExpectedAnswerByQuestion(String q) {
        switch (q) {
            case QUESTION_1:
                return ANSWER_1;
            case QUESTION_2:
                return ANSWER_2;
            case QUESTION_3:
                return ANSWER_3;
            case QUESTION_4:
                return ANSWER_4;
            case QUESTION_5:
                return ANSWER_5;
            case QUESTION_6:
                return ANSWER_6;
            case QUESTION_7:
                return ANSWER_7;
            case QUESTION_8:
                return ANSWER_8;

            default:
                throw new RuntimeException("Unknown question: " + q);
        }
    }

    public boolean checkFaqComponentIsValidTrue() throws RuntimeException {
        WebElement questionElement, answerElement;
        String questionActualText, answerActualText;
        List<WebElement> questionsContainer = driver.findElements(question);
        List<WebElement> answersContainer = driver.findElements(answer);

        for (int i = 0; i < questionsContainer.size(); i++) {

            questionElement = questionsContainer.get(i);
            waitForElementVisibility(questionElement, WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT);
            scrollToElement(questionElement);
            questionActualText = questionElement.getText();
            questionElement.click();

            answerElement = answersContainer.get(i);
            waitForElementVisibility(answerElement, WebDriverConfig.WAIT_OF_SECONDS_TIMEOUT);
            answerActualText = answerElement.getText();

            assertEquals("Заданный вопрос: \"" + questionActualText + "\" не найден", getExpectedAnswerByQuestion(questionActualText), answerActualText);
        }
        return true;
    }

    public void waitForElementVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    // вынести отдельно?
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}