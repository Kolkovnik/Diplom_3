package pages;

import config.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка "Личный кабинет"
    private final By privateCabinet = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    //Кнопка "Войти в аккаунт"
    private final By logInButtonInMainPage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    //Неактивная секция "Булки"
    private final By notActiveBunsSection = By.xpath(".//span[@class='text text_type_main-default' and contains(text(), 'Булки')]/parent::div");
    //Неактивная секция "Соусы"
    private final By notActiveSaucesSection = By.xpath(".//span[@class='text text_type_main-default' and contains(text(), 'Соусы')]/parent::div");
    //Неактивная секция "Начинки"
    private final By notActiveFillingsSection = By.xpath(".//span[@class='text text_type_main-default' and contains(text(), 'Начинки')]/parent::div");

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPrivateCabinetButton() {
        driver.findElement(privateCabinet).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLogInButtonInMainPage() {
        driver.findElement(logInButtonInMainPage).click();
    }

    @Step("Клик по секции 'Булки'")
    public void clickBunsSection() {
        driver.findElement(notActiveBunsSection).click();
    }

    @Step("Клик по секции 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(notActiveSaucesSection).click();
    }

    @Step("Клик по секции 'Начинка'")
    public void clickFillingsSection() {
        driver.findElement(notActiveFillingsSection).click();
    }

    @Step("Ожидание загрузки кнопки 'Войти в аккаунт'")
    public void waitForVisibleLogInButtonInMainPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonInMainPage));
    }

    @Step("Зайти на главную страницу и дождаться загрузки кнопки 'Войти в аккаунт'")
    public void openMainPage() {
        driver.get(Constants.URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonInMainPage));
    }

    @Step("Активность секции 'Булки'")
    public boolean isBunsSectionActive() {
        return driver.findElement(notActiveBunsSection).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Активность секции 'Соусы'")
    public boolean isSaucesSectionActive() {
        return driver.findElement(notActiveSaucesSection).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Активность секции 'Начинка'")
    public boolean isFillingsSectionActive() {
        return driver.findElement(notActiveFillingsSection).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
}