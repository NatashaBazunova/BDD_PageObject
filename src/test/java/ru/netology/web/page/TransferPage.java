package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.web.data.DataHelper.*;

public class TransferPage<amountTransfer> {
    private static SelenideElement heading = $(withText("Пополнение карты"));
    private static SelenideElement amount = $(".input__control");
    private static SelenideElement from = $("[data-test-id=from] input");
    private static SelenideElement toWhom = $("[data-test-id=to]");
    private static SelenideElement buttonSubmit = $(withText("Пополнить"));
    private static SelenideElement cancelButton = $(".button__text");
    private SelenideElement messageerror = $("data-test-id=error-notification");


    public void chooseCard(int amountTransfer, CardInfo card) {
        amount.val(String.valueOf(amountTransfer));
        from.val(card.getCardId());
        buttonSubmit.click();
    }

    public static void setCancelButton(String card, int amountTransfer) {
        from.val(card);
        amount.val(String.valueOf(amountTransfer));
        cancelButton.click();
    }
    public void HeadingsVisible() {heading.shouldBe(visible);}

    public void ErrorMessage()
    {messageerror.shouldBe(visible);}


}
