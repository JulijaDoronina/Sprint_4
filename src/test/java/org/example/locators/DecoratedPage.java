package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DecoratedPage {
    // Локатор для надписи "Заказ оформлен"
    private final By orderSuccessHeader = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    private final WebDriver driver;

    public DecoratedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderStatusDisplayed() {
        return driver.findElement(orderSuccessHeader).isDisplayed();
    }
}
