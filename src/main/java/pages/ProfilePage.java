package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    WebDriverWait wait;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка "Войти"
    private final By saveButtonInProfile = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Сохранить']");

    @Step("Ожидание загрузки кнопки 'Сохранить'")
    public void waitForVisibleSaveButtonInProfile() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButtonInProfile));
    }

    @Step("Отображение кнопки 'Сохранить'")
    public boolean visibilitySaveButtonInProfile() {
        return driver.findElement(saveButtonInProfile).isDisplayed();
    }
}