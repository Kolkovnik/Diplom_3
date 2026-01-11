import api.authorizationApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LogInPage;
import pages.MainPage;
import pages.RegistrationPage;
import pojo.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationTests extends BaseTest {
    private MainPage mainPage;
    private LogInPage logInPage;
    private RegistrationPage registrationPage;
    private User user;

    @Before
    public void startWith() {
        mainPage = new MainPage(driver);
        logInPage = new LogInPage(driver);
        registrationPage = new RegistrationPage(driver);
        user = new User();
        mainPage.openMainPage();
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
    @DisplayName("Успешная регистрация пользователя с валидными данными")
    @Description("Успешная регистрация пользователя с валидными данными и проверка логирования через API")
    public void registerNewUserWithCorrectDataTest() {
        user.setName(RandomStringUtils.randomAlphabetic(8));
        user.setEmail(RandomStringUtils.randomAlphabetic(8) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(8));

        mainPage.clickLogInButtonInMainPage();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.clickRegistrationLink();

        registrationPage.waitForVisibleRegistrationButton();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());


        logInPage.waitForVisibleLogInButtonInLoginPage();

        authorizationApi
                .loginUser(user)
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("accessToken", startsWith("Bearer"))
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Ошибка при вводе некорректного 'Password' при регистрации")
    @Description("Появление ошибки при вводе 'Password' меньше 6 значений при регистрации")
    public void messagePasswordErrorWithIncorrectPasswordTest() {
        user.setName(RandomStringUtils.randomAlphabetic(8));
        user.setEmail(RandomStringUtils.randomAlphabetic(8) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(4));

        mainPage.clickLogInButtonInMainPage();

        logInPage.waitForVisibleLogInButtonInLoginPage();
        logInPage.clickRegistrationLink();

        registrationPage.waitForVisibleRegistrationButton();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.checkVisibilityOfPasswordError();
    }
}