import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPageObjectTest {
    private static final Logger logger = Logger.getLogger(MtsPageObjectTest.class.getName());
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait shortWait;

    // Локаторы
    private final By cookieButton = By.xpath("//*[@id='cookie-agree']");
    private final By phoneInput = By.xpath("//*[@id='connection-phone']");
    private final By sumInput = By.xpath("//*[@id='connection-sum']");
    private final By emailInput = By.xpath("//*[@id='connection-email']");
    private final By continueButton = By.xpath("//*[@id='pay-connection']/button");
    private final By homeInternetAccountInput = By.xpath("//input[@placeholder='Номер абонента']");
    private final By installmentAccountInput = By.xpath("//input[@placeholder='Номер счета на 44']");
    private final By debtAccountInput = By.xpath("//input[contains(@placeholder, 'Номер счета на 2073')]");
    private final By homeInternetTab = By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button/span[1]");
    private final By servicesTab = By.xpath("//span[text()='Услуги связи']");
    private final By installmentTab = By.xpath("//option[@value='Рассрочка']");
    private final By debtTab = By.xpath("//option[@value='Задолженность' and @data-open='pay-arrears']");
    private final By cardNumberInput = By.xpath("//input[@id='cc-number']");
    private final By cardExpiryInput = By.xpath("//input[@formcontrolname='expirationDate']");
    private final By cardCvvInput = By.xpath("//input[@formcontrolname='cvc']");
    private final By cardNameInput = By.xpath("//input[@placeholder='Имя держателя']");
    private final By modalPhone = By.xpath("//span[contains(., 'Номер:375297777777')]");
    private final By modalSum = By.xpath("//span[contains(., 'BYN')]");
    private final By modalButtonSum = By.xpath("//button[contains(@class, 'colored') and contains(., 'BYN')]");
    private final By paymentSystemIcons = By.xpath("//div[contains(@class, 'cards-brands__container')]");
    private final By visaIcon = By.xpath("//img[contains(@src, 'visa')]");
    private final By mastercardIcon = By.xpath("//img[contains(@src, 'mastercard')]");
    private final By belcardIcon = By.xpath("//img[contains(@src, 'belkart') or contains(@alt, 'Белкарт')]");
    private final By titleElement = By.xpath("//h2[contains(., 'Онлайн пополнение без комиссии')]");
    private final By logosContainer = By.cssSelector(".pay__partners");
    private final By detailsLink = By.xpath("//a[contains(., 'Подробнее о сервисе')]");

    // Тестовые данные
    private static final String PHONE_NUMBER = "(29)777-77-77";
    private static final String EMAIL_ADDRESS = "aston29@mail.ru";
    private static final String SUM_CHECK = "10";
    private static final String EXPECTED_TITLE = "Онлайн пополнение без комиссии";

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        open();
        acceptCookies();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Основные методы для работы со страницей
    public void open() {
        driver.get("https://www.mts.by/");
    }

    public void acceptCookies() {
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
        } catch (Exception e) {
            logger.info("Cookie banner not found or already accepted: " + e.getMessage());
        }
    }

    public void enterText(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void clickContinue() {
        getWait().until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void selectTab(By tabLocator, By inputLocator) {
        getWait().until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(inputLocator));
    }

    // Методы для получения значений полей
    private String getElementAttribute(By locator, String attribute) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator))
                .getAttribute(attribute);
    }

    private String getElementText(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public boolean isPaymentSystemIconDisplayed(String systemName) {
        By iconLocator;
        switch (systemName.toLowerCase()) {
            case "visa":
                iconLocator = visaIcon;
                break;
            case "mastercard":
                iconLocator = mastercardIcon;
                break;
            case "белкарт":
                iconLocator = belcardIcon;
                break;
            default:
                throw new IllegalArgumentException("Unknown payment system: " + systemName);
        }
        try {
            return driver.findElement(iconLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isLogoPresent(List<WebElement> logos, String logoName) {
        for (WebElement logo : logos) {
            String altText = logo.getAttribute("alt");
            String src = logo.getAttribute("src");
            if ((altText != null && altText.contains(logoName)) ||
                    (src != null && src.toLowerCase().contains(logoName.toLowerCase()))) {
                return true;
            }
        }
        return false;
    }

    private WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    private WebDriverWait getShortWait() {
        if (shortWait == null) {
            shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return shortWait;
    }

    private void assertCommonPaymentFormFields() {
        assertAll("Общие проверки формы оплаты",
                () -> assertEquals("Сумма", getElementAttribute(sumInput, "placeholder")),
                () -> assertEquals("E-mail для отправки чека", getElementAttribute(emailInput, "placeholder"))
        );
    }

    // Тесты
    @Test
    @DisplayName("Проверка заголовка блока")
    void verifyBlockTitle() {
        String actualText = getElementText(titleElement)
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();
        assertEquals(EXPECTED_TITLE, actualText);
    }

    @Test
    @DisplayName("Проверка логотипов платежных систем")
    void verifyPaymentLogo() {
        WebElement logosContainerElement = driver.findElement(logosContainer);
        List<WebElement> logoImages = logosContainerElement.findElements(By.tagName("img"));

        assertAll("Проверка основных логотипов",
                () -> assertTrue(isLogoPresent(logoImages, "Visa")),
                () -> assertTrue(isLogoPresent(logoImages, "Mastercard")),
                () -> assertTrue(isLogoPresent(logoImages, "Белкарт"))
        );
    }

    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    void verifyDetailsLink() {
        WebElement detailsLinkElement = driver.findElement(detailsLink);

        assertAll("Проверка ссылки",
                () -> assertTrue(detailsLinkElement.isDisplayed()),
                () -> assertTrue(detailsLinkElement.isEnabled()),
                () -> assertNotNull(detailsLinkElement.getAttribute("href"))
        );
    }

    @Test
    @DisplayName("Проверка формы оплаты")
    void verifyPaymentForm() {
        selectTab(servicesTab, phoneInput);
        enterText(phoneInput, PHONE_NUMBER);
        enterText(emailInput, EMAIL_ADDRESS);
        enterText(sumInput, SUM_CHECK);

        assertAll("Проверка формы оплаты",
                () -> assertTrue(driver.findElement(continueButton).isEnabled()),
                () -> assertEquals(PHONE_NUMBER, getElementAttribute(phoneInput, "value")),
                () -> assertEquals(SUM_CHECK, getElementAttribute(sumInput, "value"))
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Услуг связи")
    void verifyEmptyFieldsPlaceholdersForMobileServices() {
        selectTab(servicesTab, phoneInput);

        assertAll("Проверка плейсхолдеров",
                () -> assertEquals("Номер телефона", getElementAttribute(phoneInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Домашнего интернета")
    void verifyEmptyFieldsPlaceholdersForHomeInternet() {
        selectTab(homeInternetTab, homeInternetAccountInput);

        assertAll("Проверка для Домашнего интернета",
                () -> assertEquals("Номер абонента", getElementAttribute(homeInternetAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Рассрочки")
    void verifyEmptyFieldsPlaceholdersForInstallment() {
        selectTab(installmentTab, installmentAccountInput);

        assertAll("Проверка для Рассрочки",
                () -> assertEquals("Номер счета на 44", getElementAttribute(installmentAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Задолженности")
    void verifyEmptyFieldsPlaceholdersForDebt() {
        selectTab(debtTab, debtAccountInput);

        assertAll("Проверка для Задолженности",
                () -> assertEquals("Номер счета на 2073", getElementAttribute(debtAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка процесса оплаты для Услуг связи")
    void verifyMobileServicesPaymentProcess() {
        selectTab(servicesTab, phoneInput);
        enterText(phoneInput, PHONE_NUMBER);
        enterText(emailInput, EMAIL_ADDRESS);
        enterText(sumInput, SUM_CHECK);
        clickContinue();

        assertAll("Проверка данных в модальном окне",
                () -> assertEquals(SUM_CHECK + ".00 BYN", getElementText(modalSum)),
                () -> assertEquals(SUM_CHECK + " BYN", getElementText(modalButtonSum)),
                () -> assertEquals(PHONE_NUMBER, getElementText(modalPhone)),
                () -> assertEquals("Номер карты", getElementAttribute(cardNumberInput, "placeholder")),
                () -> assertEquals("Срок действия", getElementAttribute(cardExpiryInput, "placeholder")),
                () -> assertEquals("CVC", getElementAttribute(cardCvvInput, "placeholder")),
                () -> assertEquals("Имя держателя (как на карте)", getElementAttribute(cardNameInput, "placeholder")),
                () -> assertTrue(isPaymentSystemIconDisplayed("Visa")),
                () -> assertTrue(isPaymentSystemIconDisplayed("Mastercard")),
                () -> assertTrue(isPaymentSystemIconDisplayed("Белкарт"))
        );
    }
}