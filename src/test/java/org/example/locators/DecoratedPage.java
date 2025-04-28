package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DecoratedPage {

    // Модальное окно "Заказ оформлен"
    public By status = By.xpath("//button[contains(text(), 'Посмотреть статус') and contains(@class, 'Button_Middle__1CSJM')]");
    private WebDriver driver;
    private WebDriverWait wait;

    public DecoratedPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickViewStatusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(status)).click();
    }
}
