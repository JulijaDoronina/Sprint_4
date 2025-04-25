package org.example.locators;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class UserPageObjects {

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

}