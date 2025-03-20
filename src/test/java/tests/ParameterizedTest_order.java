package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ParameterizedTest_order {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    // Параметры для теста
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String deliveryDay;
    private String rentalPeriod;
    private String color;
    private String note;
    private boolean useTopButton;

    // Конструктор для параметров
    public ParameterizedTest_order(boolean useTopButton, String name, String surname, String address, String phoneNumber, String deliveryDay, String rentalPeriod, String color, String note) {
        this.useTopButton = useTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDay = deliveryDay;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.note = note;
    }

    // Метод для предоставления данных
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Первый набор данных (кнопка вверху)
                {
                        true, // Использовать кнопку вверху
                        "Райан",
                        "Гослинг",
                        "Москва",
                        "+79999999999",
                        "20",
                        "1",
                        "black",
                        "Мне нужны твоя одежда и самокат"
                },
                // Второй набор данных (кнопка внизу)
                {
                        false, // Использовать кнопку внизу
                        "Хью",
                        "Джекман",
                        "ТожеМосква",
                        "+78888888888",
                        "14",
                        "2",
                        "grey",
                        "Какой еще самокат?! Мне 56 лет!"
                }
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testOrderFlow() {
        // Жму на кнопку "Заказать" (вверху или внизу)
        if (useTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        // Переход на страницу заказа
        orderPage = new OrderPage(driver);

        // Заполнение поля первого шага заказа
        orderPage.enterName(this.name);
        orderPage.enterSurname(this.surname);
        orderPage.enterAddress(this.address);
        orderPage.selectFirstMetroStation();
        orderPage.enterPhone(this.phoneNumber);

        // Жму кнопку "Далее"
        orderPage.clickNextButton();

        // Заполнение поля второго шага заказа
        if (this.deliveryDay.equals("20")) {
            orderPage.selectDeliveryDate20();
        } else if (this.deliveryDay.equals("14")) {
            orderPage.selectDeliveryDate14();
        }

        if (this.rentalPeriod.equals("1")) {
            orderPage.selectFirstRentalPeriod();
        } else if (this.rentalPeriod.equals("2")) {
            orderPage.selectSecondRentalPeriod();
        }

        if (this.color.equals("black")) {
            orderPage.selectBlackColor();
        } else if (this.color.equals("grey")) {
            orderPage.selectGreyColor();
        }

        orderPage.enterComment(this.note);

        // Жму кнопку "Заказать"
        orderPage.clickOrderButton();

        // Подтверждаю заказ
        orderPage.confirmOrder();

        // Проверка, что диалоговое окно подверждения закрылось
        assertTrue("Диалоговое окно не закрылось. Заказ не подтвержден", orderPage.isOrderConfirmationDialogClosed());

        // Проверка, что появилось всплывающее окно с сообщением об успешном создании заказа
        assertTrue("Всплывающее окно с сообщением об успешном создании заказа не отображается", orderPage.isOrderConfirmationHeaderDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}