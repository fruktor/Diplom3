package page;

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


    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage));
    }

    public void inputEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void inputPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickForgotPassword(){
        driver.findElement(forgotPasswordLink).click();
    }
}

