package org.example.locators;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class FaqPageObjects {
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

}