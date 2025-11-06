import ApiBase.model.UserAuthModel;
import ApiBase.model.UserModel;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import static ApiBase.constants.ApiConstant.URL;
import static ApiBase.userApi.*;

@RunWith(Parameterized.class)
public class LoginTest {
    private MainPage mainPage;
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage passwordPage;
    private String browser;
    private String name;
    private String email;
    private String password;

    public LoginTest(String browser) {
        this.browser = browser;
    }


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        RestAssured.baseURI = URL;
        if (browser.equals("chromedriver")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browser.equals("yandexdriver")) {
            WebDriverManager.chromedriver().browserVersion("140").setup();
            options.setBinary("C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        }

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        passwordPage = new ForgotPasswordPage(driver);

        mainPage.open();
    }

    @Parameterized.Parameters(name = "Тест в браузере: {0}")
    public static Object[][] browser() {
        return new Object[][]{
                {"chromedriver"},
                {"yandexdriver"},
        };
    }


    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginButtonMainTest() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        UserModel user = new UserModel(email, password, name);
        createUser(user);
        mainPage.waitForLoadButtonLogin();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' на главной")
    public void loginPersonalAccountButtonTest() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        UserModel user = new UserModel(email, password, name);
        createUser(user);
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginButtonRegisterFormTest() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        UserModel user = new UserModel(email, password, name);
        createUser(user);
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginForgotPasswordPageButtonTest() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        UserModel user = new UserModel(email, password, name);
        createUser(user);
        mainPage.waitForLoadButtonLogin();
        mainPage.clickPersonalAccount();
        loginPage.clickForgotPassword();
        passwordPage.clickLoginLinkPasswordPage();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoadButtonOrder();

    }



    @After
    public void tearDown() {
        // Обязательно закрываем браузер после теста
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
