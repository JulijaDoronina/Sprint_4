package org.example.locators;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class ArendaPageObjects {
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


}