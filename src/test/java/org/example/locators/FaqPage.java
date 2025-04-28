package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class FaqPage {
    //Локатор кнопки, закрытия куки в модальном окне, "да все привыкли"
    public By cookieButton = By.className("App_CookieButton__3cvqF");

    //Локатор страницы о важном
    public By faq = By.className("Home_FAQ__3uVm4");

    // Список ID вопросов
    public List<By> questionIds = Arrays.asList(
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    );

    // Список ожидаемых ID ответов
    public List<By> answerIds = Arrays.asList(
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    );

    private WebDriver driver;
    private WebDriverWait wait;

    public FaqPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5L);
    }

    public void clickCookieButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void scrollToFaqSection() {
        WebElement faqSection = driver.findElement(faq);
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqSection);
    }

    public void checkQuestionAnswer(int questionIndex, String expectedAnswer) {
        WebElement question = driver.findElement(questionIds.get(questionIndex));
        question.click();

        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                answerIds.get(questionIndex)));

        String actualAnswer = answer.getText();
        if (!actualAnswer.equals(expectedAnswer)) {
            throw new AssertionError("Для вопроса #" + questionIndex +
                    " ожидался ответ '" + expectedAnswer +
                    "', но получен '" + actualAnswer + "'");
        }
    }
}