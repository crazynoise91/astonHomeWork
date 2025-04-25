import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности страницы МТС,
 * в частности блока "Онлайн пополнение без комиссии".
 */
@DisplayName("MTS")
@Feature("Проверка блока Онлайн пополнение без комиссии")
public class MtsPaymentFeatureTest {

    protected WebDriver driver;
    private MtsPaymentPage mtsPay;

    /**
     * Настройка WebDriver перед всеми тестами.
     * Инициализирует chromedriver с помощью WebDriverManager.
     */
    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Настройка перед каждым тестом:
     * - Создает новый экземпляр ChromeDriver
     * - Максимизирует окно браузера
     * - Инициализирует page object
     * - Устанавливает неявное ожидание 10 секунд
     */
    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mtsPay = new MtsPaymentPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Проверка корректности отображения заголовка блока.
     */
    @Test
    @DisplayName("Проверка заголовка 'Онлайн пополнение без комиссии'")
    @Description("Проверка правильного отображения заголовка блока")
    void verifyBlockTitle() {
        try {
            openPageAndAcceptCookies();

            WebElement title = driver.findElement(By.xpath("//h2[contains(., 'Онлайн пополнение без комиссии')]"));
            String actualText = title.getText()
                    .replace("\n", " ")
                    .replaceAll("\\s+", " ")
                    .trim();
            assertEquals("Онлайн пополнение без комиссии", actualText);
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Проверка наличия логотипов основных платежных систем (Visa, Mastercard, Белкарт).
     */
    @Test
    @DisplayName("Проверка логотипов платежных систем")
    void verifyPaymentLogo() {
        try {
            openPageAndAcceptCookies();

            WebElement logosContainer = driver.findElement(By.cssSelector(".pay__partners"));
            List<WebElement> logoImages = logosContainer.findElements(By.tagName("img"));

            assertAll("Проверка основных логотипов",
                    () -> assertTrue(isLogoPresent(logoImages, "Visa")),
                    () -> assertTrue(isLogoPresent(logoImages, "Mastercard")),
                    () -> assertTrue(isLogoPresent(logoImages, "Белкарт"))
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Проверка работоспособности ссылки "Подробнее о сервисе".
     */
    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    void verifyDetailsLink() {
        try {
            openPageAndAcceptCookies();

            WebElement detailsLink = driver.findElement(By.xpath("//a[contains(., 'Подробнее о сервисе')]"));
            assertAll("Проверка ссылки 'Подробнее о сервисе'",
                    () -> assertTrue(detailsLink.isDisplayed()),
                    () -> assertTrue(detailsLink.isEnabled()),
                    () -> assertNotNull(detailsLink.getAttribute("href"))
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Проверка функциональности формы оплаты:
     * - Ввод номера телефона, email и суммы
     * - Проверка активности кнопки продолжения
     * - Верификация введенных данных
     */
    @Test
    @DisplayName("Проверка форм оплаты")
    void verifyPaymentForm() {
        try {
            openPageAndAcceptCookies();
            selectServicesTabStep();
            enterPhoneNumberStep();
            enterEmailStep();
            enterSumStep();

            assertTrue(driver.findElement(mtsPay.continueButton).isEnabled());
            assertEquals(MtsPaymentPage.PHONE_NUMBER, mtsPay.getEnterPhoneNumber());
            assertEquals(MtsPaymentPage.SUM_CHECK, mtsPay.getEnteredSum());
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Проверка placeholder'ов полей формы для услуг связи.
     */
    @Test
    @DisplayName("Проверка плейсхолдеров для Услуг связи")
    void verifyEmptyFieldsPlaceholdersForMobileServices() {
        try {
            openPageAndAcceptCookies();
            selectServicesTabStep();

            assertAll("Проверка placeholders для Услуг связи",
                    () -> assertEquals("Номер телефона", mtsPay.getPhoneFieldPlaceholder(),
                            "Неверный placeholder для номера телефона"),
                    () -> assertEquals("Сумма", mtsPay.getSumFieldPlaceholder(),
                            "Неверный placeholder для суммы"),
                    () -> assertEquals("E-mail для отправки чека", mtsPay.getEmailFieldPlaceholder(),
                            "Неверный placeholder для поля email")
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Проверка процесса оплаты:
     * - Заполнение и отправка формы
     * - Проверка отображения платежного окна
     * - Верификация данных в платежном окне
     */
    @Test
    @DisplayName("Проверка диалогового окна оплаты")
    void verifyMobileServicesPaymentProcess() {
        try {
            PaymentFrame paymentFrame = fillAndSubmitPaymentFormStep(
            );

            assertAll("Проверка страницы оплаты",
                    () -> assertTrue(paymentFrame.isPaymentFrameDisplayed(),
                            "Платежное окно не отображается"),
                    () -> assertEquals("10.00 BYN", paymentFrame.getDisplayedSum(),
                            "Неверная сумма"),
                    () -> assertTrue(paymentFrame.getDisplayedPhoneNumber().contains("375297777777"),
                            "Номер телефона не соответствует ожидаемому"),
                    () -> assertEquals("Номер карты", paymentFrame.getCardNumberLabel(),
                            "Неверный placeholder для номера карты"),
                    () -> assertEquals("Срок действия", paymentFrame.getExpiryDateLabel(),
                            "Неверный placeholder срока действия карты"),
                    () -> assertEquals("CVC", paymentFrame.getCvcLabel(),
                            "Неверный placeholder для CVC"),
                    () -> assertEquals(4, paymentFrame.getPaymentSystemsCount(),
                            "Неверное количество платежных систем"),
                    () -> assertTrue(paymentFrame.getSubmitButtonText().contains("10.00 BYN"),
                            "Неверная сумма на кнопке оплаты")
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    /**
     * Завершающие действия после каждого теста:
     * Закрывает браузер и освобождает ресурсы.
     */
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Шаги тестов с аннотацией @Step

    @Step("Открыть страницу и принять куки")
    private void openPageAndAcceptCookies() {
        mtsPay.open();
        mtsPay.acceptCookies();
    }

    @Step("Выбрать вкладку 'Услуги'")
    private void selectServicesTabStep() {
        mtsPay.selectServicesTab();
    }

    @Step("Ввести номер телефона: {phone}")
    private void enterPhoneNumberStep() {
        mtsPay.enterPhoneNumber(MtsPaymentPage.PHONE_NUMBER);
    }

    @Step("Ввести email: {email}")
    private void enterEmailStep() {
        mtsPay.enterEmail(MtsPaymentPage.EMAIL_ADDRESS);
    }

    @Step("Ввести сумму: {sum}")
    private void enterSumStep() {
        mtsPay.enterSum(MtsPaymentPage.SUM_CHECK);
    }

    @Step("Заполнить и отправить форму оплаты: телефон={phone}, сумма={sum}, email={email}")
    private PaymentFrame fillAndSubmitPaymentFormStep() {
        return mtsPay.fillAndSubmitPaymentForm("(29)777-77-77", "10", "aston29@mail.ru");
    }

    /**
     * Проверяет наличие логотипа в списке элементов.
     *
     * @param logos список элементов с логотипами
     * @param logoName название искомого логотипа
     * @return true если логотип найден, иначе false
     */
    private boolean isLogoPresent(List<WebElement> logos, String logoName) {
        return logos.stream().anyMatch(logo -> {
            String altText = logo.getAttribute("alt");
            String src = logo.getAttribute("src");
            return (altText != null && altText.contains(logoName)) ||
                    (src != null && src.toLowerCase().contains(logoName.toLowerCase()));
        });
    }

    /**
     * Создает скриншот при возникновении исключения.
     * Сохраняет скриншот в папку target и прикрепляет к Allure-отчету.
     */
    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    private void takeScreenshotOnException() {
        if (!(driver instanceof TakesScreenshot)) {
            System.err.println("Driver не поддерживает создание скриншотов");
            return;
        }
        TakesScreenshot tsDriver = (TakesScreenshot) driver;
        byte[] screenshotBytes = tsDriver.getScreenshotAs(OutputType.BYTES);

        // Сохраняем файл в папку target
        File targetDir = new File("target");
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String fileName = "screenshot_" + timestamp + ".png";
        Path targetPath = new File(targetDir, fileName).toPath();

        try {
            Files.write(targetPath, screenshotBytes);
            System.out.println("Скриншот сохранен в: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }

}