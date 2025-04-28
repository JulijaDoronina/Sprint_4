package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {

    // Страница "Для кого самокат"
    // Поле "Имя"
    public By firstName = By.xpath("//input[@placeholder='* Имя']");
    // Поле "Фамилия"
    public By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле "Адрес"
    public By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    public By station = By.xpath("//input[@placeholder='* Станция метро']");
        // Локаторы расписаны для 4х станций метро, для остальных станций создаются аналогично
        // Локатор для станции метро "Каширская"
        public By kashirskayaStation = By.xpath("//div[contains(text(), 'Каширская')]");
        // Локатор для станции метро "Лубянка"
        public By lubyankaStation = By.xpath("//div[contains(text(), 'Лубянка')]");
        // Локатор для станции метро "Спортивная"
        public By sportivnayaStation = By.xpath("//div[contains(text(), 'Спортивная')]");
        // Локатор для станции метро "Сокол"
        public By socolStation = By.xpath("//div[contains(text(), 'Сокол')]");
    // Поле "Телефон:на него позвонит курьер"
    public By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    public By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    private WebDriver driver;
    private WebDriverWait wait;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5L);
    }

    public void fillUserData(String firstName, String lastName, String address, String phone, By stationName) {
        driver.findElement(this.firstName).sendKeys(firstName);
        driver.findElement(this.lastName).sendKeys(lastName);
        driver.findElement(this.address).sendKeys(address);
        driver.findElement(this.phone).sendKeys(phone);

        driver.findElement(station).click();
        WebElement stationElement = wait.until(ExpectedConditions.elementToBeClickable(stationName));
        stationElement.click();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
}