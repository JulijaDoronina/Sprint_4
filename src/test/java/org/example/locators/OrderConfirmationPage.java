package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {
    // Модальное окно подтверждения заказа, кнопка "Да"
    public By yes = By.xpath("//button[contains(text(), 'Да') and contains(@class, 'Button_Middle__1CSJM')]");

    private final WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYesButton() {
        driver.findElement(yes).click();
    }
}
