import config.DriverFactory;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void startUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        RestAssured.baseURI = "https://stellarburgers.education-services.ru/";
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}