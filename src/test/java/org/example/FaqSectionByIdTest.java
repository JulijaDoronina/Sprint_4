package org.example;

import org.example.locators.FaqPage;
import org.example.locators.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FaqSectionByIdTest {
    private WebDriver driver;
    private MainPage mainPage;
    private FaqPage faqPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

//         Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        faqPage = new FaqPage(driver);
    }

    @Test
    public void testFaqSectionById() {
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

        mainPage.clickCookieButton();
        faqPage.scrollToFaqSection();

        for (int i = 0; i < faqPage.questionIds.size(); i++) {
            String actualAnswer = faqPage.getAnswerText(i);
            assert actualAnswer.equals(expectedAnswers.get(i)) :
                    "Для вопроса #" + i + " ожидался ответ '" + expectedAnswers.get(i) +
                            "', но получен '" + actualAnswer + "'";
        }
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
