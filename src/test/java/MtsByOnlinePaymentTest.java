import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsByOnlinePaymentTest {
    private static final String LINK_URL = "https://www.mts.by/";
    private static final String PHONE_NUMBER = "297777777";
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(30);

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*", // Разрешает доступ к удаленным источникам
                "--start-maximized", // Запускает браузер в полноэкранном режиме
                "--disable-notifications", // Отключает уведомления браузера
                "--no-sandbox", // Отключает режим песочницы для повышения производительности
                "--disable-dev-shm-usage" // Использует временные файлы вместо разделяемой памяти для ускорения работы
        );

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        driver.get(LINK_URL);
        acceptCookiesIfPresent();
        waitForPageLoad();
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Accept')]")));
            acceptButton.click();
            wait.until(ExpectedConditions.invisibilityOf(acceptButton));
        } catch (TimeoutException e) {
            // Если кнопка не появилась, продолжаем выполнение
            System.out.println("Cookie banner не найден или уже закрыт");
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка заголовка блока")
    void verifyBlockTitle() {
        String expectedTitle = "Онлайн пополнение без комиссии";
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(., 'Онлайн')]")));

        String actualText = normalizeText(title.getText());

        assertEquals(expectedTitle, actualText, "Фактический заголовок ошибочен.");
    }

    @Test
    @DisplayName("Проверка логотипов платежных систем")
    void verifyPaymentLogos() {
        List<String> expectedLogos = List.of("Visa", "Mastercard", "Белкарт");
        WebElement logosContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".pay__partners")));

        assertAll("Проверка логотипов платежных систем",
                expectedLogos.stream()
                        .map(logo -> () -> assertTrue(isLogoPresent(logosContainer, logo),
                                "Логотип " + logo + " не найден"))
        );
    }

    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    void verifyDetailsLink() {
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Подробнее о сервисе')]")));

        assertAll("Проверка ссылки",
                () -> assertTrue(detailsLink.isDisplayed(), "Ссылка не отображается"),
                () -> assertTrue(detailsLink.isEnabled(), "Ссылка неактивна"),
                () -> assertNotNull(detailsLink.getAttribute("href"), "Ссылка не содержит URL")
        );
    }

    @Test
    @DisplayName("Проверка формы оплаты")
    void verifyPaymentForm() {
        // Добавляем явное ожидание и скролл к элементу
        WebElement serviceTab = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class, 'select__now') and text()='Услуги связи']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", serviceTab);
        wait.until(ExpectedConditions.elementToBeClickable(serviceTab)).click();

        fillInputField("input#connection-phone", PHONE_NUMBER);
        fillInputField("input#connection-email", "aston29@mail.ru");
        fillInputField("input#connection-sum", "10");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'button__default') and text()='Продолжить']")));

        assertTrue(submitButton.isEnabled(), "Кнопка 'Продолжить' должна функционировать.");
    }

    /* ========== Вспомогательные методы ========== */

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    private String normalizeText(String text) {
        return text.replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private boolean isLogoPresent(WebElement container, String logoName) {
        return container.findElements(By.tagName("img")).stream()
                .anyMatch(logo -> {
                    String altText = logo.getAttribute("alt");
                    String src = logo.getAttribute("src");
                    return (altText != null && altText.contains(logoName)) ||
                            (src != null && src.toLowerCase().contains(logoName.toLowerCase()));
                });
    }

    private void fillInputField(String cssSelector, String value) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        input.clear();
        input.sendKeys(value);
    }
}