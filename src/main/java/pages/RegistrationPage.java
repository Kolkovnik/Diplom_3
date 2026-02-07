package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поле "Имя"
    private final By nameField = By.xpath(".//label[contains(@class, 'input__placeholder text noselect text_type_main-default') and contains(text(), 'Имя')]/following-sibling::input");
    //Поле "Email"
    private final By emailField = By.xpath(".//label[contains(@class, 'input__placeholder text noselect text_type_main-default') and contains(text(), 'Email')]/following-sibling::input");
    //Поле "Пароль"
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    //Ссылка на "Войти"
    private final By logInLinkInRegistrationPage = By.xpath(".//a[@class='Auth_link__1fOlj']");
    //Ошибка "Некорректный пароль"
    private final By passwordError = By.xpath(".//p[@class='input__error text_type_main-default']");


    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLogInLinkInRegistrationPage() {
        driver.findElement(logInLinkInRegistrationPage).click();
    }

    @Step("Ввести данные аккаунта и нажать кнопку 'Зарегистрироваться'")
    public void registration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        clickRegistrationButton();
    }

    @Step("Отображение ошибки 'Некорректный пароль'")
    public boolean checkVisibilityOfPasswordError() {
        WebElement visibilityOfOrderButton = driver.findElement(passwordError);
        return visibilityOfOrderButton.isDisplayed();
    }

    @Step("Ожидание загрузки кнопки 'Зарегистрироваться'")
    public void waitForVisibleRegistrationButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }
}