package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By registerLink = By.className("Auth_link__1fOlj");
    private final By loginPage = By.xpath(".//main/div/h2[text()='Вход']");
    private final By fieldEmail = By.xpath(".//div[label[text()='Email']]/input");
    private final By fieldPassword = By.xpath(".//div[label[text()='Пароль']]/input");
    private final By loginButton = By.xpath(".//button[contains(text(),'Войти')]");
    private final By forgotPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Ожидание загрузки страницы входа")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage));
    }

    @Step("Ввод Email на страницe входа")
    public void inputEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввод пароля на страницe входа")
    public void inputPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Ввод Email и пароля")
    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickForgotPassword(){
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Получение текста заголовка страницы 'Логин'")
    public String getTextLoginPage() {
        return driver.findElement(loginPage).getText();
    }
}

