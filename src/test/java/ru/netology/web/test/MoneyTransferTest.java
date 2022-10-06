package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsTo2() {
        String amount = "1500";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = 8500;
        int expected2 = 11500;
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard1());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo1() {
        String amount = "5800";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard1());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = 15800;
        int expected2 = 4200;
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo2FullBalance() {
        String amount = "10000";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = 0;
        int expected2 = 20000;
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard1());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo2FromWrongCard() {
        String amount = "1000";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfoWrongCard(amount, transferInfo.getWrongNumberCard());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsToSameCard() {
        String amount = "10000";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = 10000;
        int expected2 = 10000;
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo1MoreBalance() {
        String amount = "10001";
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard1());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = 10000;
        int expected2 = 10000;
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        TransferToCardPage = DashboardPage.TransferToCard(transferInfo.getIdCard2());
        TransferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }
}
