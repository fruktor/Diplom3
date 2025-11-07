import io.qameta.allure.Description;
import user_api.model.UserAuthModel;
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
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import static org.junit.Assert.assertEquals;
import static user_api.User.*;
import static user_api.constants.ApiConstant.URL;


public class RegisterTest{
    private MainPage mainPage;
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;;
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


        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);

        mainPage.open();
    }




    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация уникального пользователя пользователя ")
    public void registerUserTest() {

            mainPage.clickPersonalAccount();
            loginPage.clickRegisterLink();
            registerPage.waitForLoadRegisterPage();
            registerPage.register(name, email, password);
            registerPage.clickRegisterButton();
            loginPage.waitForLoadLoginPage();

            assertEquals("Вход", loginPage.getTextLoginPage());
    }

    @Test
    @DisplayName("Ошибка регистрации при пароли меньше 6 символов")
    @Description("Регистрация пользователя с недопустимым паролем")
    public void registerUserWithIncorrectPasswordTest() {
        password = "1111";

        mainPage.clickPersonalAccount();
        loginPage.clickRegisterLink();
        registerPage.waitForLoadRegisterPage();
        registerPage.register(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.waitForLoadIncorrectPassword();

        assertEquals("Некорректный пароль", registerPage.getTextIncorrectPassword());
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

