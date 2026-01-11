import api.authorizationApi;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import pojo.User;

import static org.junit.Assert.*;

public class LogInTests extends BaseTest {
    private MainPage mainPage;
    private LogInPage logInPage;
    private RecoverPasswordPage recoverPasswordPage;
    private RegistrationPage registrationPage;
    private User user;
    private ProfilePage profilePage;

    @Before
    public void startWith() {
        mainPage = new MainPage(driver);
        logInPage = new LogInPage(driver);
        registrationPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
        user = new User();

        mainPage.openMainPage();
        user.setName(RandomStringUtils.randomAlphabetic(8));
        user.setEmail(RandomStringUtils.randomAlphabetic(8) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        authorizationApi.createUser(user);
    }

    @After
    public void deleteUserAfter() {
        String accessToken = authorizationApi
                .loginUser(user)
                .extract()
                .body()
                .path("accessToken");
        if (accessToken != null) {
            authorizationApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешное логирование через кнопку 'Войти в аккаунт' на главной странице")
    public void logInWithLogInButtonInMainPageTest() {
        mainPage.clickLogInButtonInMainPage();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.login(user.getEmail(), user.getPassword());

        mainPage.waitForVisibleLogInButtonInMainPage();
        mainPage.clickPrivateCabinetButton();

        profilePage.waitForVisibleSaveButtonInProfile();
        assertTrue(profilePage.visibilitySaveButtonInProfile());
    }

    @Test
    @DisplayName("Успешное логирование через кнопку 'Личный кабинет' на главной странице")
    public void logInWithPrivateCabinetButtonTest() {
        mainPage.clickPrivateCabinetButton();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.login(user.getEmail(), user.getPassword());

        mainPage.waitForVisibleLogInButtonInMainPage();
        mainPage.clickPrivateCabinetButton();

        profilePage.waitForVisibleSaveButtonInProfile();
        assertTrue(profilePage.visibilitySaveButtonInProfile());
    }

    @Test
    @DisplayName("Успешное логирование через кнопку 'Войти' в форме регистрации")
    public void logInWithLogInLinkInRegistrationPageTest() {
        mainPage.clickPrivateCabinetButton();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.clickRegistrationLink();

        registrationPage.waitForVisibleRegistrationButton();
        registrationPage.clickLogInLinkInRegistrationPage();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.login(user.getEmail(), user.getPassword());

        mainPage.waitForVisibleLogInButtonInMainPage();
        mainPage.clickPrivateCabinetButton();

        profilePage.waitForVisibleSaveButtonInProfile();
        assertTrue(profilePage.visibilitySaveButtonInProfile());
    }

    @Test
    @DisplayName("Успешное логирование через кнопку 'Войти' в форме восстановления пароля")
    public void logInWithRecoverButton() {
        mainPage.clickPrivateCabinetButton();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.clickRecoverThePasswordLink();

        recoverPasswordPage.waitForRecoverButton();
        recoverPasswordPage.clickLogInLinkInRecoverPasswordPage();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.login(user.getEmail(), user.getPassword());

        mainPage.waitForVisibleLogInButtonInMainPage();
        mainPage.clickPrivateCabinetButton();

        profilePage.waitForVisibleSaveButtonInProfile();
        assertTrue(profilePage.visibilitySaveButtonInProfile());
    }
}