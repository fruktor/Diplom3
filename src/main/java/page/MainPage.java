package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final String url = "https://stellarburgers.education-services.ru";


    private final By accountButton = By.xpath(".//a[@href='/account']");
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

    public void open() {
        driver.get(url);
    }

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void waitForLoadButtonOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    public void waitForLoadButtonLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    public void clickBun() {
        driver.findElement(bun).click();
    }

    public void clickSauce() {
        driver.findElement(sauces).click();
    }

    public void clickFilling() {
        driver.findElement(fillings).click();
    }

    public void waitBunActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunActive));
    }

    public void waitSaucesActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesActive));
    }

    public void waitFillingsActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsActive));
    }

}
