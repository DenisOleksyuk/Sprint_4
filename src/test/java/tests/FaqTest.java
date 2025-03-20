package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class FaqTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testFaqSection() {
        // Прокрутка страницы до раздела "Вопросы о важном"
        WebElement faqSection = mainPage.getQuestionElement(0); // get первый вопрос
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);

        // Ожидание, пока раздел станет видимым
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(faqSection));

        // Проверка всех вопросов и ответов
        checkQuestionAndAnswer(1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        checkQuestionAndAnswer(2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        checkQuestionAndAnswer(3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        checkQuestionAndAnswer(4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        checkQuestionAndAnswer(5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        checkQuestionAndAnswer(6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        checkQuestionAndAnswer(7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        checkQuestionAndAnswer(8, "Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }

    // Метод для проверки вопроса и ответа
    private void checkQuestionAndAnswer(int questionNumber, String expectedAnswer) {
        // Жмак на вопрос
        switch (questionNumber) {
            case 1:
                mainPage.clickQuestion1();
                break;
            case 2:
                mainPage.clickQuestion2();
                break;
            case 3:
                mainPage.clickQuestion3();
                break;
            case 4:
                mainPage.clickQuestion4();
                break;
            case 5:
                mainPage.clickQuestion5();
                break;
            case 6:
                mainPage.clickQuestion6();
                break;
            case 7:
                mainPage.clickQuestion7();
                break;
            case 8:
                mainPage.clickQuestion8();
                break;
            default:
                throw new IllegalArgumentException("Неверный номер вопроса: " + questionNumber);
        }

        // Получение текста ответа
        String actualAnswer;
        switch (questionNumber) {
            case 1:
                actualAnswer = mainPage.getAnswer1Text();
                break;
            case 2:
                actualAnswer = mainPage.getAnswer2Text();
                break;
            case 3:
                actualAnswer = mainPage.getAnswer3Text();
                break;
            case 4:
                actualAnswer = mainPage.getAnswer4Text();
                break;
            case 5:
                actualAnswer = mainPage.getAnswer5Text();
                break;
            case 6:
                actualAnswer = mainPage.getAnswer6Text();
                break;
            case 7:
                actualAnswer = mainPage.getAnswer7Text();
                break;
            case 8:
                actualAnswer = mainPage.getAnswer8Text();
                break;
            default:
                throw new IllegalArgumentException("Неверный номер вопроса: " + questionNumber);
        }

        // Проверка текста ответа
        assertEquals("Текст ответа на вопрос " + questionNumber + " не совпадает", expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}