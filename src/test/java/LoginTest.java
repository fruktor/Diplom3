import io.qameta.allure.Description;
import user_api.model.UserAuthModel;
import user_api.model.UserModel;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import static org.junit.Assert.assertEquals;
import static user_api.constants.ApiConstant.URL;
import static user_api.User.*;


public class LoginTest {
    private MainPage mainPage;
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage passwordPage;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;

        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browser.equals("yandex")) {
            WebDriverManager.chromedriver().browserVersion("140").setup();
            options.setBinary("C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        }

        Faker faker = new Faker();
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        UserModel user = new UserModel(email, password, name);
        createUser(user);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        passwordPage = new ForgotPasswordPage(driver);

        mainPage.open();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Вход в аккаунт через кнопку 'Войти в аккаунт' на главной странице")
    public void loginButtonMainTest() {
        mainPage.waitForLoadButtonLogin();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();
        assertEquals("Оформить заказ", mainPage.getTextOrderButton());

    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' на главной")
    @Description("Вход в аккаунт через кнопку 'Личный кабинет' на главной странице")
    public void loginPersonalAccountButtonTest() {
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

        assertEquals("Оформить заказ", mainPage.getTextOrderButton());

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Вход в аккаунт через кнопку 'Войти' в форме регистрации ")
    public void loginButtonRegisterFormTest() {
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

        assertEquals("Оформить заказ", mainPage.getTextOrderButton());

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Вход через кнопку 'Войти' в форме восстановления пароля")
    public void loginForgotPasswordPageButtonTest() {
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.clickForgotPassword();
        passwordPage.clickLoginLinkPasswordPage();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

        assertEquals("Оформить заказ", mainPage.getTextOrderButton());

    }



    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        UserAuthModel user = new UserAuthModel(email, password);
        try {
            deleteUser(getUserToken(user));
        } catch (AssertionError | NullPointerException e) {

        }
    }
}
