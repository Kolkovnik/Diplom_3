package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {
    private final WebDriver driver;
    WebDriverWait wait;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ссылка на "Войти"
    private final By logInLinkInRecoverPasswordPage = By.xpath(".//a[@class='Auth_link__1fOlj']");

    @Step("Клик по ссылке 'Войти'")
    public void clickLogInLinkInRecoverPasswordPage() {
        driver.findElement(logInLinkInRecoverPasswordPage).click();
    }

    @Step("Ожидание загрузки кнопки 'Восстановить'")
    public void waitForRecoverButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInLinkInRecoverPasswordPage));
    }
}
