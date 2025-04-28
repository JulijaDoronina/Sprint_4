package org.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    // Главная страница кнопки "Заказать"
    //Локатор кнопки, закрытия куки в модальном окне, "да все привыкли"
    public By cookieButton = By.className("App_CookieButton__3cvqF");

    // Верхняя кнопка "Заказать"
    public By topOrderButton = By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    public By downOrderButton = By.className("Button_Middle__1CSJM");

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickCookieButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickDownOrderButton() {
        WebElement btn = driver.findElement(downOrderButton);
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", btn);
        btn.click();
    }
}