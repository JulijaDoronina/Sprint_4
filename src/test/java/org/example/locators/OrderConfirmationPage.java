package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage {
    // Модальное окно подтверждения заказа, кнопка "Да"
    public By yes = By.xpath("//button[contains(text(), 'Да') and contains(@class, 'Button_Middle__1CSJM')]");

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5L);
    }

    public void clickYesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(yes)).click();
    }
}
