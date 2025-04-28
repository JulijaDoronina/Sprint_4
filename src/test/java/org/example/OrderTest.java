package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.locators.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private ArendaPage arendaPage;
    private DecoratedPage decoratedPage;
    private OrderConfirmationPage orderConfirmationPage;
    private UserPage userPage;

    // Параметры теста
    @Parameterized.Parameter
    public String testName;

    @Parameterized.Parameter(1)
    public boolean useTopButton;

    @Parameterized.Parameter(2)
    public String firstName;

    @Parameterized.Parameter(3)
    public String lastName;

    @Parameterized.Parameter(4)
    public String address;

    @Parameterized.Parameter(5)
    public String phone;

    @Parameterized.Parameter(6)
    public By stationName;

    @Parameterized.Parameter(7)
    public By rentalPeriod;

    @Parameterized.Parameter(8)
    public boolean isUseCheckBox1;

    @Parameterized.Parameter(9)
    public boolean isUseCheckBox2;

    @Parameterized.Parameter(10)
    public String comment;

    @Parameterized.Parameter(11)
    public int year;

    @Parameterized.Parameter(12)
    public int month;

    @Parameterized.Parameter(13)
    public int day;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Тест 1: Заказ через верхнюю кнопку
                {
                        "Заказ через верхнюю кнопку",
                        true,
                        "Юлия", "Доронина", "г.Екатеринбург Ленина 32б",
                        "87673457689", UserPage.KASHIRSKAYA_STATION, ArendaPage.RENTAL_3_DAYS,
                        true, true, "Шлагбаум при въезде", 2025, 5, 15
                },
                // Тест 2: Заказ через верхнюю кнопку (другой пользователь)
                {
                        "Заказ через верхнюю кнопку (другой пользователь)",
                        true,
                        "Иван", "Петров", "г.Москва Петровская 124",
                        "1232", UserPage.LUBYANKA_STATION, ArendaPage.RENTAL_4_DAYS,
                        false, true, "Шлагбаум при въезде", 2026, 7, 15
                },
                // Тест 3: Заказ через нижнюю кнопку
                {
                        "Заказ через нижнюю кнопку",
                        false,
                        "Елена", "Иванова", "г.Новосибирск Красный проспект 38",
                        "21674787689", UserPage.SPORTIVNAYA_STATION, ArendaPage.RENTAL_5_DAYS,
                        true, true, "Шлагбаум при въезде", 2024, 9, 8
                },
                // Тест 4: Заказ через нижнюю кнопку (другой адрес)
                {
                        "Заказ через нижнюю кнопку (другой адрес)",
                        false,
                        "fgf", "Доронина", "г.Екатеринбург Ленина 32б",
                        "87673457689", UserPage.SOCOL_STATION, ArendaPage.RENTAL_6_DAYS,
                        true, true, "Шлагбаум при въезде", 2025, 5, 15
                }
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        driver.get(MainPage.URL);
        // Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5L));

        mainPage = new MainPage(driver);
        arendaPage = new ArendaPage(driver, wait);
        decoratedPage = new DecoratedPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        userPage = new UserPage(driver);
    }

    @Test
    public void createOrderTest() {
        mainPage.clickCookieButton();

        if (useTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickDownOrderButton();
        }

        userPage.fillUserData(firstName, lastName, address, phone, stationName);
        userPage.clickNextButton();

        arendaPage.fillRentalData(year, month, day, rentalPeriod,
                isUseCheckBox1, isUseCheckBox2, comment);
        arendaPage.clickOrderButton();

        orderConfirmationPage.clickYesButton();

        assertTrue("После оформления заказа должна быть надпись 'Заказ оформлен'",
                decoratedPage.isOrderStatusDisplayed());
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
