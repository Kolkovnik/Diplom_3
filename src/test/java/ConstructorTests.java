import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTests extends BaseTest {
    private MainPage mainPage;

    @Before
    public void startWith() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Переход к секции 'Соусы'")
    @Description("Переход к секции 'Соусы' и сравнение активности по атрибуту 'class'")
    public void selectSaucesSectionTest() {
        mainPage.clickSaucesSection();
        assertTrue(mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к секции 'Начинки'")
    @Description("Переход к секции 'Начинки' и сравнение активности по атрибуту 'class'")
    public void selectFillingsSectionTest() {
        mainPage.clickFillingsSection();
        assertTrue(mainPage.isFillingsSectionActive());
    }

    @Test
    @DisplayName("Переход к секции 'Булки'")
    @Description("Переход к секции 'Булки' и сравнение активности по атрибуту 'class'")
    public void selectBunsSectionTest() {
        mainPage.clickFillingsSection();
        mainPage.clickBunsSection();
        assertTrue(mainPage.isBunsSectionActive());
    }
}