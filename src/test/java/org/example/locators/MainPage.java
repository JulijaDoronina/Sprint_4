package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    // Главная страница кнопки "Заказать"
    //Локатор кнопки, закрытия куки в модальном окне, "да все привыкли"
    public By cookieButton = By.className("App_CookieButton__3cvqF");

    // Верхняя кнопка "Заказать"
    public By topOrderButton = By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    public By downOrderButton = By.className("Button_Button__ra12g");

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5L);
    }

    public void clickCookieButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
    }
}