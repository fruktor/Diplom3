import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.MainPage;
import static org.junit.Assert.assertTrue;


public class MainPageConstructorTest {
    private MainPage mainPage;
    private WebDriver driver;

    @Before
    public void setUp() {

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

        mainPage = new MainPage(driver);
        mainPage.open();

    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void transitionToBun() {
        mainPage.clickSauce();
        mainPage.clickBun();
        mainPage.waitBunActive();

        assertTrue(mainPage.getTextClassBun().contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void transitionToSauce() {
        mainPage.clickSauce();
        mainPage.waitSaucesActive();

        assertTrue(mainPage.getTextClassSauce().contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void transitionToFillings() {
        mainPage.clickFilling();
        mainPage.waitFillingsActive();

        assertTrue(mainPage.getTextClassFillings().contains("tab_tab_type_current__2BEPc"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
