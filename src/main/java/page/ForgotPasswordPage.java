package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By loginLinkPasswordPage = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLinkPasswordPage() {
        driver.findElement(loginLinkPasswordPage).click();
    }
}
