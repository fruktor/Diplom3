package page;

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

    public void inputName(String name){
        driver.findElement(fieldName).sendKeys(name);
    }

    public void inputEmail(String email){
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void register(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
    }

    public void clickRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage));
    }

    public void waitForLoadIncorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPassword));
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
