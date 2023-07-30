package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement paymentCardButton = $(byText("Купить"));
    private SelenideElement creditCardButton = $(byText("Купить в кредит"));

    public PaymentCardPage paymentCard() {
        paymentCardButton.click();
        return new PaymentCardPage();
    }

    public CreditCardPage creditCard() {
        creditCardButton.click();
        return new CreditCardPage();
    }
}
