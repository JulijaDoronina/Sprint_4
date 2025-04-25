package org.example;

import org.example.locators.FaqPageObjects;
import org.example.locators.MainPageObjects;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FaqSectionByIdTest {
    private WebDriver driver;
    private MainPageObjects mainPageObjects;
    private FaqPageObjects faqPageObjects;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver-win64\\chromedriver.exe");

        mainPageObjects = new MainPageObjects();
        faqPageObjects = new FaqPageObjects();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

//         Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    @Test
    public void testFaqSectionById() {
        WebDriverWait wait = new WebDriverWait(driver, 5L);

        //закрываем модальное окно куки кнопкой да все привыкли
        wait.until(ExpectedConditions.elementToBeClickable(mainPageObjects.cookieButton)).click();

        // переход к странице "Вопросы о важном", скролл до раздела о важном
        WebElement faqSection = driver.findElement(faqPageObjects.faq);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqSection);

        List<String> expectedAnswers = Arrays.asList(
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        );

        // Проверка каждого вопроса
        for (int i = 0; i < faqPageObjects.questionIds.size(); i++) {
            // Находим вопрос по ID
            WebElement question = driver.findElement(faqPageObjects.questionIds.get(i));

            // Кликаем на вопрос
            question.click();

            // Ожидаем появления ответа
            WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    faqPageObjects.answerIds.get(i)
            ));

            // Проверяем текст ответа
            String actualAnswer = answer.getText();
            assert actualAnswer.equals(expectedAnswers.get(i)) :
                    "Для вопроса с ID '" + faqPageObjects.questionIds.get(i) + "' ожидался ответ '" + expectedAnswers.get(i) +
                            "', но получен '" + actualAnswer + "'";

            System.out.println("Проверен вопрос с ID: " + faqPageObjects.questionIds.get(i));
            System.out.println("Ответ корректный: " + actualAnswer);
        }
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
