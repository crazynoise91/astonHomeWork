import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object класс для работы с платежным интерфейсом.
 * Обеспечивает взаимодействие с элементами платежного фрейма и проверку состояния формы оплаты.
 */
class PaymentFrame {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы элементов платежного фрейма
    private final By paymentFrame = By.xpath("//iframe[@class='bepaid-iframe']");
    private final By sumDisplay = By.xpath("//div[@class='pay-description__cost']");
    private final By phoneDisplay = By.xpath("//div[@class='pay-description__text']");
    private final By cardNumberInput = By.xpath("//label[@class='ng-tns-c2312288139-1 ng-star-inserted']");
    private final By expiryDateInput = By.xpath("//label[@class='ng-tns-c2312288139-4 ng-star-inserted']");
    private final By cvcInput = By.xpath("//label[@class='ng-tns-c2312288139-5 ng-star-inserted']");
    private final By paymentSystemIcons = By.cssSelector("img[src*='system']:not([style*='opacity: 0'])");
    private final By submitButton = By.xpath("//button[@class='colored disabled']");

    /**
     * Инициализирует новый экземпляр класса PaymentPageTest.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public PaymentFrame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Переключает контекст драйвера на платежный фрейм.
     * Ожидает доступность фрейма перед переключением.
     */
    private void switchToPaymentFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));
    }

    /**
     * Возвращает контекст драйвера к основному содержимому страницы.
     */
    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Проверяет видимость платежного фрейма.
     *
     * @return true если фрейм отображается, false в случае исключения
     */
    public boolean isPaymentFrameDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFrame)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Получает отображаемую сумму платежа из фрейма.
     *
     * @return текст с суммой платежа
     */
    public String getDisplayedSum() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(sumDisplay)).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Получает отображаемый номер телефона из фрейма.
     *
     * @return текст с номером телефона
     */
    public String getDisplayedPhoneNumber() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneDisplay)).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Получает текст метки для поля ввода номера карты.
     *
     * @return текст метки поля ввода карты
     */
    public String getCardNumberLabel() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Получает текст метки для поля ввода срока действия карты.
     *
     * @return текст метки поля срока действия
     */
    public String getExpiryDateLabel() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateInput)).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Получает текст метки для поля ввода CVC/CVV кода.
     *
     * @return текст метки CVC поля
     */
    public String getCvcLabel() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcInput)).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Подсчитывает количество доступных платежных систем.
     *
     * @return количество видимых иконок платежных систем
     */
    public int getPaymentSystemsCount() {
        switchToPaymentFrame();
        try {
            return driver.findElements(paymentSystemIcons).size();
        } finally {
            switchToDefaultContent();
        }
    }

    /**
     * Получает текст кнопки подтверждения оплаты.
     *
     * @return текст кнопки оплаты
     */
    public String getSubmitButtonText() {
        switchToPaymentFrame();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).getText();
        } finally {
            switchToDefaultContent();
        }
    }
}