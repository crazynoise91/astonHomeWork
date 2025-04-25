import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object класс для работы со страницей оплаты услуг МТС.
 * Содержит методы для взаимодействия с элементами страницы и выполнения тестовых сценариев.
 */
public class MtsPaymentPage {
    private final WebDriver driver;
    // Ожидание с таймаутом 15 секунд для стандартных операций
    private final WebDriverWait wait;
    // Короткое ожидание с таймаутом 5 секунд для быстрых операций
    private final WebDriverWait shortWait;

    // Локаторы элементов страницы
    private final By cookie = By.xpath("//*[@id='cookie-agree']");
    private final By phoneInput = By.xpath("//*[@id='connection-phone']");
    private final By sumInput = By.xpath("//*[@id='connection-sum']");
    private final By emailInput = By.xpath("//*[@id='connection-email']");
    final By continueButton = By.xpath("//*[@id='pay-connection']/button");
    private final By homeInternetTab = By.xpath("//*[@id='pay-section']//div[contains(@class,'home-internet')]//button/span[1]");
    private final By homeInternetAccountInput = By.xpath("//input[@placeholder='Номер абонента']");
    private final By servicesTab = By.xpath("//span[text()='Услуги связи']");
    private final By installmentTab = By.xpath("//button[contains(text(),'Рассрочка')]");
    private final By debtTab = By.xpath("//button[contains(text(),'Задолженность') and @data-open='pay-arrears']");

    // Тестовые данные по умолчанию
    public static final String PHONE_NUMBER = "(29)777-77-77";
    public static final String EMAIL_ADDRESS = "aston29@mail.ru";
    public static final String SUM_CHECK = "10";

    /**
     * Конструктор инициализирует WebDriver и настраивает ожидания.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Открывает главную страницу МТС.
     */
    public void open() {
        driver.get("https://www.mts.by/");
    }

    /**
     * Принимает cookies, если отображается баннер.
     * В случае ошибки выводит сообщение в консоль.
     */
    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookie)).click();
        } catch (Exception e) {
            System.out.println("Баннер с файлами cookie не найден: " + e.getMessage());
        }
    }

    /**
     * Вводит номер телефона в соответствующее поле.
     *
     * @param phone номер телефона для ввода
     */
    public void enterPhoneNumber(String phone) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        element.sendKeys(phone);
    }

    /**
     * Вводит email в соответствующее поле.
     *
     * @param email email адрес для ввода
     */
    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        element.sendKeys(email);
    }

    /**
     * Вводит сумму платежа.
     *
     * @param sum сумма для оплаты
     */
    public void enterSum(String sum) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(sumInput));
        element.sendKeys(sum);
    }

    /**
     * Нажимает кнопку "Продолжить" после заполнения формы.
     */
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    /**
     * Выбирает вкладку "Услуги связи" для оплаты мобильных услуг.
     */
    public void selectServicesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(servicesTab)).click();
    }

    /**
     * Выбирает вкладку "Домашний интернет" и ожидает появления поля для ввода номера абонента.
     */
    public void selectHomeInternetTab() {
        wait.until(ExpectedConditions.elementToBeClickable(homeInternetTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeInternetAccountInput));
    }

    /**
     * Выбирает вкладку "Рассрочка" для оплаты услуг в рассрочку.
     */
    public void selectInstallmentTab() {
        wait.until(ExpectedConditions.elementToBeClickable(installmentTab)).click();
    }

    /**
     * Выбирает вкладку "Задолженность" для оплаты задолженностей.
     */
    public void selectDebtTab() {
        wait.until(ExpectedConditions.elementToBeClickable(debtTab)).click();
    }

    /**
     * Получает текущее значение из поля ввода телефона.
     *
     * @return введенный номер телефона
     */
    public String getEnterPhoneNumber() {
        WebElement phoneInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput));
        return phoneInputElement.getAttribute("value");
    }

    /**
     * Получает текущее значение из поля ввода суммы.
     *
     * @return введенная сумма платежа
     */
    public String getEnteredSum() {
        WebElement sumInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(sumInput));
        return sumInputElement.getAttribute("value");
    }

    /**
     * Получает текст placeholder из поля ввода телефона.
     *
     * @return текст placeholder поля ввода телефона
     */
    public String getPhoneFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput))
                .getAttribute("placeholder");
    }

    /**
     * Получает текст placeholder из поля ввода суммы.
     *
     * @return текст placeholder поля ввода суммы
     */
    public String getSumFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(sumInput))
                .getAttribute("placeholder");
    }

    /**
     * Получает текст placeholder из поля ввода email.
     *
     * @return текст placeholder поля ввода email
     */
    public String getEmailFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(emailInput))
                .getAttribute("placeholder");
    }

    /**
     * Получает текст placeholder из поля ввода номера абонента домашнего интернета.
     *
     * @return текст placeholder поля ввода номера абонента
     */
    public String getHomeInternetAccountPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(homeInternetAccountInput))
                .getAttribute("placeholder");
    }

    /**
     * Заполняет и отправляет форму оплаты, возвращая Page Object для страницы оплаты.
     *
     * @param phone номер телефона для оплаты
     * @param amount сумма платежа
     * @param email email для отправки чека
     * @return экземпляр PaymentPageTest для работы со страницей оплаты
     */
    public PaymentFrame fillAndSubmitPaymentForm(String phone, String amount, String email) {
        open();
        acceptCookies();
        selectServicesTab();
        enterPhoneNumber(phone);
        enterEmail(email);
        enterSum(amount);
        clickContinue();
        return new PaymentFrame(driver);
    }
}