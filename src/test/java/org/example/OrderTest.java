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
    private MainPageObjects mainPageObjects;
    private ArendaPageObjects arendaPageObjects;
    private DecoratedPageObjects decoratedPageObjects;
    private OrderConfirmationPageObjects orderConfirmationPageObjects;
    private UserPageObjects userPageObjects;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver-win64\\chromedriver.exe");

        mainPageObjects = new MainPageObjects();
        arendaPageObjects = new ArendaPageObjects();
        decoratedPageObjects = new DecoratedPageObjects();
        orderConfirmationPageObjects = new OrderConfirmationPageObjects();
        userPageObjects = new UserPageObjects();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

//      Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    @Test
    public void createOrderTest1() {
        createOrder(mainPageObjects.topOrderButton, "Юлия", "Доронина", "г.Екатеринбург Ленина 32б",
                "87673457689", userPageObjects.kashirskayaStation, arendaPageObjects.rental3Days,
                true,true, "Шлагбаум при въезде", 2025, 5, 15);
       }

    @Test
    public void createOrderTest2() {
        createOrder(mainPageObjects.topOrderButton, "Иван", "Петров", "г.Москва Петровская 124",
                "1232", userPageObjects.lubyankaStation, arendaPageObjects.rental4Days,
                false,true, "Шлагбаум при въезде", 2026, 7, 15);

    }

    @Test
    public void createOrderTest3() {
        createOrder(mainPageObjects.downOrderButton, "Елена", "Иванова", "г.Новосибирск Красный проспект 38",
                "21674787689", userPageObjects.sportivnayaStation, arendaPageObjects.rental5Days,
                true,true, "Шлагбаум при въезде", 2024, 9, 8);

    }

    @Test
    public void createOrderTest4() {
        createOrder(mainPageObjects.downOrderButton, "fgf", "Доронина", "г.Екатеринбург Ленина 32б",
                "87673457689", userPageObjects.socolStation, arendaPageObjects.rental6Days,
                true,true, "Шлагбаум при въезде", 2025, 5, 15);

    }

    private void createOrder(By firstOrderButton, String firstName, String lastName, String address, String phone,
                             By stationName, By rentalPeriod,
                             boolean isUseCheckBox1, boolean isUseCheckBox2, String comment, int year, int month, int day) {
        WebDriverWait wait = new WebDriverWait(driver, 5L);
// прокрутка
        driver.findElement(firstOrderButton).click();

        driver.findElement(userPageObjects.firstName).sendKeys(firstName);
        driver.findElement(userPageObjects.lastName).sendKeys(lastName);;
        driver.findElement(userPageObjects.address).sendKeys(address);;
        driver.findElement(userPageObjects.phone).sendKeys(phone);;

        WebElement textFieldStation = driver.findElement(userPageObjects.station);
        textFieldStation.click();
        // Ожидаем появления вариантов и кликаем
        WebElement element = driver.findElement(stationName);
        // Скролл до элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        // Явное ожидание кликабельности и клик
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(userPageObjects.nextButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", nextButton);

        WebElement textFieldRentalPeriod = driver.findElement(arendaPageObjects.rentalPeriod);
        WebElement textFieldColor = driver.findElement(arendaPageObjects.color);
        WebElement textFieldСomment = driver.findElement(arendaPageObjects.comment);;

        selectDate(driver, year, month, day);

        textFieldRentalPeriod.click();

        // Ожидаем появления вариантов срока аренды и кликаем
        WebElement rental = driver.findElement(rentalPeriod);
        // Скролл до элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", rental);
        // Явное ожидание кликабельности и клик
        wait.until(ExpectedConditions.elementToBeClickable(rental)).click();

        textFieldColor.click();
        // Найдем чекбокс
        WebElement checkBox1 = wait.until(ExpectedConditions.elementToBeClickable(arendaPageObjects.checkBox1));
        // Проверим, если чекбокс еще не выбран, то выбираем его
        if (isUseCheckBox1 && !checkBox1.isSelected()) {
            checkBox1.click();  // Кликаем по чекбоксу, чтобы выбрать его
        }

        WebElement checkBox2 = wait.until(ExpectedConditions.elementToBeClickable(arendaPageObjects.checkBox2));
        // Проверим, если чекбокс еще не выбран, то выбираем его
        if (isUseCheckBox2 && !checkBox2.isSelected()) {
            checkBox2.click();  // Кликаем по чекбоксу, чтобы выбрать его
        }

        textFieldСomment.sendKeys(comment);
//закрываем модальное окно куки кнопкой да все привыкли
        WebElement CookieButton = wait.until(ExpectedConditions.elementToBeClickable(mainPageObjects.cookieButton));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", CookieButton);
        CookieButton.click();
        // нажать кнопку заказать
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(arendaPageObjects.order));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", orderButton);
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        // нажать кнопку Да
        WebElement OkButton = wait.until(ExpectedConditions.elementToBeClickable(orderConfirmationPageObjects.yes));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OkButton);
        wait.until(ExpectedConditions.elementToBeClickable(OkButton)).click();

        // Посмотреть заказ
        WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(decoratedPageObjects.status));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", viewButton);
        wait.until(ExpectedConditions.elementToBeClickable(viewButton)).click();
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }

    private void selectDate(WebDriver driver, int year, int month, int day) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);

        // Кликаем на поле ввода даты
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(arendaPageObjects.date));
        dateField.click();

        // Ждем появления календаря
        wait.until(ExpectedConditions.visibilityOfElementLocated(arendaPageObjects.calendar));

        // Получаем текущую дату
        WebElement currentMonthElement = driver.findElement(arendaPageObjects.currentDate);
        String currentDate = currentMonthElement.getText();

        // Парсим текущий месяц и год
        int currentYear = Integer.parseInt(currentDate.split(" ")[1]);
        int currentMonth = parseMonth(currentDate.split(" ")[0]);

        // Вычисляем разницу в месяцах между целевой и текущей датой
        int monthDiff = (year - currentYear) * 12 + (month - currentMonth);

        // Определяем направление навигации (вперед или назад)
        By navigationButtonLocator = monthDiff > 0 ? arendaPageObjects.nextMonth : arendaPageObjects.prevMonth;

        // Переключаем месяцы пока не достигнем нужного
        int steps = Math.abs(monthDiff);
        for (int i = 0; i < steps; i++) {
            WebElement navButton = wait.until(ExpectedConditions.elementToBeClickable(navigationButtonLocator));
            navButton.click();

            // Небольшая пауза между кликами
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        // Формируем локатор для дня
        String dayLocator = String.format(
                "//div[contains(@class, 'react-datepicker__day') and text()='%d' " +
                        "and not(contains(@class, 'react-datepicker__day--outside-month'))]", day);

        // Кликаем по выбранному дню
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayLocator)));
        dayElement.click();
    }

    private int parseMonth(String monthName) {
        String[] months = {"январь", "февраль", "март", "апрель", "май", "июнь",
                "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        for (int i = 0; i < months.length; i++) {
            if (monthName.toLowerCase().contains(months[i])) {
                return i + 1; // Месяцы нумеруются с 1
            }
        }
        throw new IllegalArgumentException("Неизвестное название месяца: " + monthName);
    }
}
