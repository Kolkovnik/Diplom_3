package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {
    private final WebDriver driver;
    WebDriverWait wait;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поле "Email"
    private final By emailField = By.xpath(".//input[@name='name']");
    //Поле "Пароль"
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Ссылка на "Зарегистрироваться"
    private final By registrationLink = By.xpath("//p[@class='undefined text text_type_main-default text_color_inactive mb-4']/a[@class='Auth_link__1fOlj']");
    //Ссылка на "Восстановить пароль"
    private final By recoverThePasswordLink = By.xpath("//p[@class='undefined text text_type_main-default text_color_inactive']/a[@class='Auth_link__1fOlj']");
    //Кнопка "Войти"
    private final By logInButtonInLoginPage = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickRecoverThePasswordLink() {
        driver.findElement(recoverThePasswordLink).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLogInButtonInLoginPage() {
        driver.findElement(logInButtonInLoginPage).click();
    }

    @Step("Залогиниться с логином и паролем и нажать кнопку 'Войти'")
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        clickLogInButtonInLoginPage();
    }

    @Step("Ожидание загрузки кнопки 'Войти'")
    public void waitForVisibleLogInButtonInLoginPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonInLoginPage));
    }
}