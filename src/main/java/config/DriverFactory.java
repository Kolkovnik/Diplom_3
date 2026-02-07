package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "yandex":
                // 1. Указываем путь к yandexdriver.exe
                System.setProperty("webdriver.chrome.driver", "C:\\cygwin64\\home\\Diplom\\Diplom_3\\yandexdriver.exe");

                ChromeOptions options = new ChromeOptions();
                // 2. Указываем путь к Яндекс.Браузеру
                options.setBinary("C:\\Users\\Никита\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

                return new ChromeDriver(options);

            default:
                throw new RuntimeException("Браузер " + browser + " не поддерживается.");
        }
    }
}