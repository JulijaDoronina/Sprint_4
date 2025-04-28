package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArendaPage {
    //Локатор кнопки, закрытия куки в модальном окне, "да все привыкли"
    public By cookieButton = By.className("App_CookieButton__3cvqF");

     // Страница "Про аренду"
    // Поле "Когда привезти самокат"
    public By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
        // Календарь
        public By calendar = By.className("react-datepicker");
        // Текущая дата
        public By currentDate = By.className("react-datepicker__current-month");
        // Навигация вперед и назад
        public By prevMonth = By.xpath("//button[contains(@class, 'react-datepicker__navigation--previous')]");
    public By nextMonth = By.xpath("//button[contains(@class, 'react-datepicker__navigation--next')]");

    // Поле "Срок аренды"
    public By rentalPeriod = By.className("Dropdown-placeholder");
        //Локаторы расписаны для 4х сроков аренды, для остальных создаются аналогично
        // Срок "трое суток"
        public By rental3Days = By.xpath("//div[contains(text(), 'трое суток')]");
        // Срок "четверо суток"
        public By rental4Days = By.xpath("//div[contains(text(), 'четверо суток')]");
        // Срок "пятеро суток"
        public By rental5Days = By.xpath("//div[contains(text(), 'пятеро суток')]");
        // Срок "шестеро суток"
        public By rental6Days = By.xpath("//div[contains(text(), 'шестеро суток')]");
    // Поле "Цвет самоката"
    public By color = By.className("Order_Title__3EKne");
        // Чекбоксы выбора цвета самоката
        // Чекбокс "Чёрный жемчуг"
        public By checkBox1 = By.id("black");
        // Чекбокс "Серая безысходность"
        public By checkBox2 = By.id("grey");
    // Поле "Комментарий для курьера"
    public By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    public By order =By.xpath("//button[contains(text(), 'Заказать') and contains(@class, 'Button_Middle__1CSJM')]");

    private WebDriver driver;
    private WebDriverWait wait;

    public ArendaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5L);
    }

    public void fillRentalData(int year, int month, int day, By rentalPeriodLocator,
                               boolean useCheckBox1, boolean useCheckBox2, String comment) {
        selectDate(year, month, day);
        selectRentalPeriod(rentalPeriodLocator);
        selectColor(useCheckBox1, useCheckBox2);
        setComment(comment);
    }

    private void selectDate(int year, int month, int day) {
        driver.findElement(date).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendar));

        WebElement currentMonthElement = driver.findElement(currentDate);
        String currentDateText = currentMonthElement.getText();
        int currentYear = Integer.parseInt(currentDateText.split(" ")[1]);
        int currentMonth = parseMonth(currentDateText.split(" ")[0]);

        int monthDiff = (year - currentYear) * 12 + (month - currentMonth);
        By navigationButton = monthDiff > 0 ? nextMonth : prevMonth;

        for (int i = 0; i < Math.abs(monthDiff); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(navigationButton)).click();
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        String dayLocator = String.format(
                "//div[contains(@class, 'react-datepicker__day') and text()='%d' " +
                        "and not(contains(@class, 'react-datepicker__day--outside-month'))]", day);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayLocator))).click();
    }

    private int parseMonth(String monthName) {
        String[] months = {"январь", "февраль", "март", "апрель", "май", "июнь",
                "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        for (int i = 0; i < months.length; i++) {
            if (monthName.toLowerCase().contains(months[i])) {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Неизвестное название месяца: " + monthName);
    }

    private void selectRentalPeriod(By rentalPeriodLocator) {
        driver.findElement(rentalPeriod).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodLocator)).click();
    }

    private void selectColor(boolean useCheckBox1, boolean useCheckBox2) {
        if (useCheckBox1) {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkBox1));
            if (!checkbox.isSelected()) checkbox.click();
        }
        if (useCheckBox2) {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkBox2));
            if (!checkbox.isSelected()) checkbox.click();
        }
    }

    private void setComment(String text) {
        driver.findElement(comment).sendKeys(text);
    }

    public void clickOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(order)).click();
    }
}