package org.example.locators;

import org.openqa.selenium.By;

public class OrderConfirmationPageObjects {
    // Модальное окно подтверждения заказа, кнопка "Да"
    public By yes = By.xpath("//button[contains(text(), 'Да') and contains(@class, 'Button_Middle__1CSJM')]");
}
