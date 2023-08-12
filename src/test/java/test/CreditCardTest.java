package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeEach
    void clearDataBase() {
        SQLHelper.cleanDataBase();
    }
    @Test
    public void shouldValidValues() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardValidValues());
        paymentCard.successPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldDeclinedCardNumber() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardDeclinedCardNumber());
        paymentCard.errorPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    public void shouldInvalidCardNumber() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardInvalidCardNumber());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldIncompleteMonth() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardIncompleteMonth());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldNullMonth() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardNullMonth());
        paymentCard.expiredLastMonth();
    }

    @Test
    public void shouldNonexistentMonth() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardNonexistentMonth());
        paymentCard.expiredLastMonth();
    }

    @Test
    public void shouldIncompleteYear() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardIncompleteYear());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldExpiredLastYear() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardExpiredLastYear());
        paymentCard.expiredLastYear();
    }

    @Test
    public void shouldExpiredLastMonth() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardExpiredLastMonth());
        paymentCard.expiredLastMonth();
    }

    @Test
    public void shouldExpirationDateMoreFiveYears() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardExpirationDateMoreFiveYears());
        paymentCard.expiredLastMonth();
    }

    @Test
    public void shouldNameRussianLanguage() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardNameRussianLanguage());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldCardNumberEmpty() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardNumberEmpty());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldMonthEmpty() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardMonthEmpty());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldYearEmpty() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardYearEmpty());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldOwnerEmpty() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardOwnerEmpty());
        paymentCard.requiredField();
    }

    @Test
    public void shouldCVCEmpty() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardCVCEmpty());
        paymentCard.invalidFormatField();
    }

    @Test
    public void shouldRandomCardNumber() {
        var homePage = new HomePage();
        var paymentCard = homePage.creditCard();
        paymentCard.CreditForm(DataHelper.getCardRandomCardNumber());
        paymentCard.errorPayment();
    }

}