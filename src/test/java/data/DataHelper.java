package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static String getApprovedCardNumber() {
        return "4444_4444_4444_4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444_4444_4444_4442";
    }

    public static String getInvalidCardNumber() {
        return "4444_4444_4444";
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextSixYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    static Faker faker = new Faker(new Locale("en"));

    public static String getName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCVC() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    @Value
    public static class Card {
        private String cardNumber;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static Card getCardValidValues() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static Card getCardDeclinedCardNumber() {
        return new Card(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static Card getCardInvalidCardNumber() {
        return new Card(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static Card getCardIncompleteMonth() {
        return new Card(getApprovedCardNumber(), "8", getNextYear(), getName(), getCVC());
    }

    public static Card getCardNullMonth() {
        return new Card(getApprovedCardNumber(), "00", getNextYear(), getName(), getCVC());
    }

    public static Card getCardNonexistentMonth() {
        return new Card(getApprovedCardNumber(), "13", getNextYear(), getName(), getCVC());
    }

    public static Card getCardIncompleteYear() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), "4", getName(), getCVC());
    }

    public static Card getCardExpiredLastYear() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getLastYear(), getName(), getCVC());
    }

    public static Card getCardExpiredLastMonth() {
        return new Card(getApprovedCardNumber(), getLastMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static Card getCardExpirationDateMoreFiveYears() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getNextSixYear(), getName(), getCVC());
    }

    public static Card getCardNameRussianLanguage() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "Анна Иоанновна", getCVC());
    }

    public static Card getCardNumberEmpty() {
        return new Card("", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static Card getCardMonthEmpty() {
        return new Card(getApprovedCardNumber(), "", getNextYear(), getName(), getCVC());
    }

    public static Card getCardYearEmpty() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), "", getName(), getCVC());
    }

    public static Card getCardOwnerEmpty() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "", getCVC());
    }

    public static Card getCardCVCEmpty() {
        return new Card(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), "");
    }

    public static Card getCardRandomCardNumber() {
        return new Card("1234_5678_9876_5432", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }
}
