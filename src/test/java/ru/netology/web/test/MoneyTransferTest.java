package ru.netology.web.test;
import com.codeborne.selenide.Configuration;
import lombok.Data;
import org.apache.commons.io.file.DeleteOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferFromFirstCard() {
        int amountTransfer = 10000;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.secondCard();
        var card = DataHelper.getFirstCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        var expectedBalanceOfFirstCard = balanceOfFirstCard - amountTransfer;
        var expectedBalanceOfSecondCard = balanceOfSecondCard + amountTransfer;
        var firstCardAfterTransferMoney = DashboardPage.getFirstCardBalance();
        var secondCardAfterTransferMoney = DashboardPage.getSecondCardBalance();
        assertEquals(expectedBalanceOfFirstCard, firstCardAfterTransferMoney);
        assertEquals(expectedBalanceOfSecondCard, secondCardAfterTransferMoney);

    }

    @Disabled
    @Test
    public void shouldNotTransferFromFirstCardIfNoMoney() {
        int amountTransfer = 100;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.secondCard();
        var card = DataHelper.getFirstCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        transferMoneyCard.ErrorMessage();
    }

    @Test
    public void shouldTransferFromSecondCard() {
        int amountTransfer = 6900;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.firstCard();
        var card = DataHelper.getSecondCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        var expectedBalanceOfFirstCard = balanceOfFirstCard + amountTransfer;
        var expectedBalanceOfSecondCard = balanceOfSecondCard - amountTransfer;
        var firstCardAfterTransferMoney = DashboardPage.getFirstCardBalance();
        var secondCardAfterTransferMoney = DashboardPage.getSecondCardBalance();
        assertEquals(expectedBalanceOfFirstCard, firstCardAfterTransferMoney);
        assertEquals(expectedBalanceOfSecondCard, secondCardAfterTransferMoney);

    }
    @Disabled
    @Test
    public void shouldNotTransferWithoutAmount() {
        int amountTransfer = 0;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.secondCard();
        var card = DataHelper.getFirstCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        transferMoneyCard.ErrorMessage();
    }
    @Disabled
    @Test
    public void shouldNotTransferFromFirstCardToFirstCard() {
        int amountTransfer = 1300;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.firstCard();
        var card = DataHelper.getFirstCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        transferMoneyCard.ErrorMessage();
    }


    @Disabled
    @Test
    public void shouldNotTransferFromFirstCardMoreMoneyThanHave() {
        int amountTransfer = 30000;
        var balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        var balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var transferMoneyCard = DashboardPage.secondCard();
        var card = DataHelper.getFirstCardInfo();
        transferMoneyCard.chooseCard(amountTransfer, card);
        transferMoneyCard.ErrorMessage();
    }



}

