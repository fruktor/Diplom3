package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;

    private final By fieldName = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    private final By fieldEmail = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By fieldPassword = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    private final By buttonRegister = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registerPage = By.xpath(".//div/h2[text()='Регистрация']");
    private final By incorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени")
    public void inputName(String name){
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Ввод Email")
    public void inputEmail(String email){
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password){
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрировать'")
    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Ожидание появления страницы регистрации")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage));
    }

    @Step("Ожидание появление ошибки в поле 'Пароль'")
    public void waitForLoadIncorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPassword));
    }

    @Step("Получение текста ошибки при вводе недопустимого пароля")
    public String getTextIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }

    @Step("Нажатие на кнопку 'Войти' на странице Регистрации")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
