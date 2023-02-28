package ru.praktikumservices.qascooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikumservices.qascooter.constants.Answer;
import ru.praktikumservices.qascooter.constants.Question;
import ru.praktikumservices.qascooter.extensions.WebDriverFactory;
import ru.praktikumservices.qascooter.pages.HomePage;

@RunWith(Parameterized.class)
public class HomePageFaqTest {
    private static WebDriver driver;

    private final int questionNumber;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public HomePageFaqTest(int questionNumber, String expectedQuestion, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Parameterized.Parameters(name = "{index}: {0},{1},{2}")
    public static Object[][] getData() {
        return new Object[][]{
                {0, Question.QUESTION_1, Answer.ANSWER_1},
                {1, Question.QUESTION_2, Answer.ANSWER_2},
                {2, Question.QUESTION_3, Answer.ANSWER_3},
                {3, Question.QUESTION_4, Answer.ANSWER_4},
                {4, Question.QUESTION_5, Answer.ANSWER_5},
                {5, Question.QUESTION_6, Answer.ANSWER_6},
                {6, Question.QUESTION_7, Answer.ANSWER_7},
                {7, Question.QUESTION_8, Answer.ANSWER_8}
        };
    }
    @Test
    public void checkFaqComponentIsWorkingCorrect() {
        new HomePage(driver)
                .getFaq()
                .checkFaqComponentIsValidTrue(questionNumber, expectedQuestion, expectedAnswer);
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
