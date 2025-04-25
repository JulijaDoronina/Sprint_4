package org.example.locators;

import org.openqa.selenium.By;

public class DecoratedPageObjects {

    // Модальное окно "Заказ оформлен"
    public By status = By.xpath("//button[contains(text(), 'Посмотреть статус') and contains(@class, 'Button_Middle__1CSJM')]");

}
