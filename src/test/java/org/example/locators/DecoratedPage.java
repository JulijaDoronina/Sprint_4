package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DecoratedPage {

    // Модальное окно "Заказ оформлен"
    public By status = By.xpath("//button[contains(text(), 'Посмотреть статус') and contains(@class, 'Button_Middle__1CSJM')]");
    private final WebDriver driver;

    public DecoratedPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickViewStatusButton() {
        driver.findElement(status).click();
    }
}
