package org.example;

import org.example.locators.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ArendaPage arendaPage;
    private DecoratedPage decoratedPage;
    private OrderConfirmationPage orderConfirmationPage;
    private UserPage userPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
//      Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        arendaPage = new ArendaPage(driver);
        decoratedPage = new DecoratedPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        userPage = new UserPage(driver);
    }

    @Test
    public void createOrderTest1() {
        createOrderWithTopOrderButton("Юлия", "Доронина", "г.Екатеринбург Ленина 32б",
                "87673457689", userPage.kashirskayaStation, arendaPage.rental3Days,
                true,true, "Шлагбаум при въезде", 2025, 5, 15);
       }

    @Test
    public void createOrderTest2() {
        createOrderWithTopOrderButton("Иван", "Петров", "г.Москва Петровская 124",
                "1232", userPage.lubyankaStation, arendaPage.rental4Days,
                false,true, "Шлагбаум при въезде", 2026, 7, 15);

    }

    @Test
    public void createOrderTest3() {
        createOrderWithDownOrderButton("Елена", "Иванова", "г.Новосибирск Красный проспект 38",
                "21674787689", userPage.sportivnayaStation, arendaPage.rental5Days,
                true,true, "Шлагбаум при въезде", 2024, 9, 8);

    }

    @Test
    public void createOrderTest4() {
        createOrderWithDownOrderButton("fgf", "Доронина", "г.Екатеринбург Ленина 32б",
                "87673457689", userPage.socolStation, arendaPage.rental6Days,
                true,true, "Шлагбаум при въезде", 2025, 5, 15);

    }

    private void createOrderWithTopOrderButton(String firstName, String lastName, String address, String phone,
                             By stationName, By rentalPeriod,
                             boolean isUseCheckBox1, boolean isUseCheckBox2, String comment, int year, int month, int day) {
        mainPage.clickCookieButton();
        mainPage.clickTopOrderButton();

        userPage.fillUserData(firstName, lastName, address,
                phone, stationName);
        userPage.clickNextButton();

        arendaPage.fillRentalData(year, month, day, rentalPeriod,
                isUseCheckBox1, isUseCheckBox2, comment);
        arendaPage.clickOrderButton();

        orderConfirmationPage.clickYesButton();
        decoratedPage.clickViewStatusButton();
    }

    private void createOrderWithDownOrderButton(String firstName, String lastName, String address, String phone,
                                               By stationName, By rentalPeriod,
                                               boolean isUseCheckBox1, boolean isUseCheckBox2, String comment, int year, int month, int day) {
        mainPage.clickCookieButton();
        mainPage.clickDownOrderButton();

        userPage.fillUserData(firstName, lastName, address,
                phone, stationName);
        userPage.clickNextButton();

        arendaPage.fillRentalData(year, month, day, rentalPeriod,
                isUseCheckBox1, isUseCheckBox2, comment);
        arendaPage.clickOrderButton();

        orderConfirmationPage.clickYesButton();
        decoratedPage.clickViewStatusButton();
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
