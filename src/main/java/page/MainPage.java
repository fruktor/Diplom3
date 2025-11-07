package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    private final String url = "https://stellarburgers.education-services.ru";
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private final By bun = By.xpath(".//span[text()='Булки']");
    private final By sauces = By.xpath(".//span[text()='Соусы']");
    private final By fillings = By.xpath(".//span[text()='Начинки']");
    private final By bunActive = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and .//span[text()='Булки']]");
    private final By saucesActive = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and .//span[text()='Соусы']]");
    private final By fillingsActive = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and .//span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие сайта Stellar Burgers")
    public void open() {
        driver.get(url);
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Ожидание загрузки кнопки 'Оформить заказ'")
    public void waitForLoadButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    @Step("Ожидание загрузки кнопки 'Войти в аккаунт'")
    public void waitForLoadButtonLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    @Step("Нажатие на кнопку 'Булки'")
    public void clickBun() {
        driver.findElement(bun).click();
    }

    @Step("Нажатие на кнопку 'Соус'")
    public void clickSauce() {
        driver.findElement(sauces).click();
    }

    @Step("Нажатие на кнопку 'Начинки'")
    public void clickFilling() {
        driver.findElement(fillings).click();
    }

    @Step("Ожидание активации раздела 'Булки'")
    public void waitBunActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunActive));
    }

    @Step("Ожидание активации раздела 'Соусы'")
    public void waitSaucesActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesActive));
    }

    @Step("Ожидание активации раздела 'Начинки'")
    public void waitFillingsActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsActive));
    }

    @Step("Получение текста кнопки 'Оформить заказ'")
    public String getTextOrderButton() {
        return driver.findElement(orderButton).getText();
    }

    @Step("Получение название класса у раздела 'Булки'")
    public String getTextClassBun() {
        return driver.findElement(bunActive).getAttribute("class");
    }

    @Step("Получение название класса у раздела 'Соусы'")
    public String getTextClassSauce() {
        return driver.findElement(saucesActive).getAttribute("class");
    }

    @Step("Получение название класса у раздела 'Начинки'")
    public String getTextClassFillings() {
        return driver.findElement(fillingsActive).getAttribute("class");
    }

}

