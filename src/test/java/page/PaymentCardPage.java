package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentCardPage {
    private SelenideElement formPayment = $(byText("Купить"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $x("//*[contains(text(), 'Владелец')]/../*/input");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    public PaymentCardPage() {
        formPayment.shouldBe(visible);
    }

    public void PaymentForm(DataHelper.Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getName());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    private SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement formatIncorrect = $(byText("Неверный формат"));
    private SelenideElement forExpiredLastMonth = $(byText("Неверно указан срок действия карты"));
    private SelenideElement forExpiredLastYear = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));

    public void successPayment() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorPayment() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void requiredField() {
        requiredField.shouldBe(visible);
    }

    public void invalidFormatField() {
        formatIncorrect.shouldBe(visible);
    }

    public void expiredLastMonth() {
        forExpiredLastMonth.shouldBe(visible);
    }

    public void expiredLastYear() {
        forExpiredLastYear.shouldBe(visible);
    }

}
