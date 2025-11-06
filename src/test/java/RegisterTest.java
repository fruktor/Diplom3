import ApiBase.model.UserAuthModel;
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
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import java.lang.Object;
import java.util.Locale;

import static ApiBase.constants.ApiConstant.URL;
import static ApiBase.userApi.deleteUser;
import static ApiBase.userApi.getUserToken;

@RunWith(Parameterized.class)
public class RegisterTest {
    private MainPage mainPage;
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private String browser;
    private String name;
    private String email;
    private String password;

    public RegisterTest(String browser) {
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
    @DisplayName("Регистрация пользователя")
    public void registerUserTest() {
            Faker faker = new Faker();
            name = faker.name().firstName();
            email = faker.internet().emailAddress();
            password = faker.internet().password();

            mainPage.clickAccountButton();
            loginPage.clickRegisterLink();
            registerPage.waitForLoadRegisterPage();
            registerPage.register(name, email, password);
            registerPage.clickRegisterButton();
            loginPage.waitForLoadLoginPage();
    }

    @Test
    @DisplayName("Ошибка регистрации при пароли меньше 6 символов")
    public void registerUserWithIncorrectPasswordTest() {
        Faker faker = new Faker();
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = "1111";

        mainPage.clickAccountButton();
        loginPage.clickRegisterLink();
        registerPage.waitForLoadRegisterPage();
        registerPage.register(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.waitForLoadIncorrectPassword();
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

