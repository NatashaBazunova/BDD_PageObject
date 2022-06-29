package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static ElementsCollection cards = $$(".list__item");
    private static String balanceStart = "баланс: ";
    private static String balanceFinish = " р.";
    private static SelenideElement firstCard = $$("[data-test-id=action-deposit]").first();
    private static SelenideElement secondCard = $$("[data-test-id=action-deposit").last();


    public DashboardPage() {
    }
    public static int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    public static int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static TransferPage firstCard(){
        firstCard.click();
        return new TransferPage();
    }
    public static TransferPage secondCard(){
        secondCard.click();
        return new TransferPage();
    }
    public void DashboardPage() {heading.shouldBe(visible);}

}

