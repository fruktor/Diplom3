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

import page.MainPage;



@RunWith(Parameterized.class)
public class MainPageConstructorTest {
    private MainPage mainPage;
    private WebDriver driver;
    private String browser;

    public MainPageConstructorTest(String browser) {
        this.browser = browser;
    }


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (browser.equals("chromedriver")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browser.equals("yandexdriver")) {
            WebDriverManager.chromedriver().browserVersion("140").setup();
            options.setBinary("C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        }

        mainPage = new MainPage(driver);
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
    @DisplayName("Переход к разделу Булки")
    public void transitionToBun() {
        mainPage.clickSauce();
        mainPage.clickBun();
        mainPage.waitBunActive();
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void transitionToSauce() {
        mainPage.clickSauce();
        mainPage.waitSaucesActive();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void transitionToFillings() {
        mainPage.clickFilling();
        mainPage.waitFillingsActive();
    }



    @After
    public void tearDown() {
        // Обязательно закрываем браузер после теста
        if (driver != null) {
            driver.quit();
        }

    }
}
