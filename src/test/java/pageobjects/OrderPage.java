package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    // Поля первого шага заказа
    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement surnameField;

    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@placeholder='* Станция метро']")
    private WebElement metroStationField;

    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneField;

    @FindBy(xpath = "//div[@class='select-search__select']//button[1]")
    private WebElement firstMetroStationOption;

    @FindBy(xpath = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']")
    private WebElement nextButton;

    // Поля второго шага заказа
    @FindBy(xpath = "//input[@placeholder='* Когда привезти самокат']")
    private WebElement deliveryDateField;

    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day') and text()='20']")
    private WebElement deliveryDate20;

    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day') and text()='14']")
    private WebElement deliveryDate14;

    @FindBy(xpath = "//div[@class='Dropdown-root']")
    private WebElement rentalPeriodField;

    @FindBy(xpath = "//div[@class='Dropdown-menu']//div[1]")
    private WebElement firstRentalPeriodOption;

    @FindBy(xpath = "//div[@class='Dropdown-menu']//div[2]")
    private WebElement secondRentalPeriodOption;

    @FindBy(xpath = "//input[@class='Checkbox_Input__14A2w' and @id='black']")
    private WebElement blackColorCheckbox;

    @FindBy(xpath = "//input[@class='Checkbox_Input__14A2w' and @id='grey']")
    private WebElement greyColorCheckbox;

    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement commentField;

    @FindBy(xpath = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement orderButton;

    @FindBy(xpath = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")
    private WebElement confirmOrderButton;

    // Локатор для заголовка всплывающего окна об успешном создании заказа
    @FindBy(xpath = "//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']")
    private WebElement orderConfirmationHeader;

    // Конструктор
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Методы для первого шага заказа
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameField.sendKeys(surname);
    }

    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }

    public void enterPhone(String phone) {
        phoneField.sendKeys(phone);
    }

    public void selectFirstMetroStation() {
        metroStationField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(firstMetroStationOption));
        firstMetroStationOption.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    // Методы для второго шага заказа
    public void selectDeliveryDate20() {
        deliveryDateField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(deliveryDate20));
        deliveryDate20.click();
    }

    public void selectDeliveryDate14() {
        deliveryDateField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(deliveryDate14));
        deliveryDate14.click();
    }

    public void selectFirstRentalPeriod() {
        rentalPeriodField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(firstRentalPeriodOption));
        firstRentalPeriodOption.click();
    }

    public void selectSecondRentalPeriod() {
        rentalPeriodField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(secondRentalPeriodOption));
        secondRentalPeriodOption.click();
    }

    public void selectBlackColor() {
        blackColorCheckbox.click();
    }

    public void selectGreyColor() {
        greyColorCheckbox.click();
    }

    public void enterComment(String comment) {
        commentField.sendKeys(comment);
    }

    public void clickOrderButton() {
        orderButton.click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(confirmOrderButton));
        confirmOrderButton.click();
    }

    public boolean isOrderConfirmationDialogClosed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOf(confirmOrderButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для проверки, что всплывающее окно с сообщением об успешном создании заказа отображается
    public boolean isOrderConfirmationHeaderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(orderConfirmationHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
