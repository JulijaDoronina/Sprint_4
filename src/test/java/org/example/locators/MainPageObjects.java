package org.example.locators;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class MainPageObjects {
    // Главная страница кнопки "Заказать"
    //Локатор кнопки, закрытия куки в модальном окне, "да все привыкли"
    public By cookieButton = By.className("App_CookieButton__3cvqF");

    // Верхняя кнопка "Заказать"
    public By topOrderButton = By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    public By downOrderButton = By.className("Button_Button__ra12g");

}