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
        int amount = 1500;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = dashboardPage.getCardBalance(transferInfo.getIdCard1()) - amount;
        int expected2 = dashboardPage.getCardBalance(transferInfo.getIdCard2()) + amount;
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard1());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo1() {
        int amount = 5800;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = dashboardPage.getCardBalance(transferInfo.getIdCard1()) + amount;
        int expected2 = dashboardPage.getCardBalance(transferInfo.getIdCard2()) - amount;
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard1());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo2FullBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        DashboardPage dashboardPage = new DashboardPage();
        int amount = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int expected1 = 0;
        int expected2 = dashboardPage.getCardBalance(transferInfo.getIdCard2()) + amount;
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard1());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo2FromWrongCard() {
        int amount = 1000;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfoWrongCard(amount, transferInfo.getWrongNumberCard());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsToSameCard() {
        int amount = 10000;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int expected2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsTo1MoreBalance() {
        int amount = 10001;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var transferInfo = DataHelper.getTransferInfo();
        DashboardPage dashboardPage = new DashboardPage();
        int expected1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int expected2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        var transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard1());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard2());
        int actual1 = dashboardPage.getCardBalance(transferInfo.getIdCard1());
        int actual2 = dashboardPage.getCardBalance(transferInfo.getIdCard2());
        transferToCardPage = DashboardPage.transferToCard(transferInfo.getIdCard2());
        transferToCardPage.inputTransterInfo(amount, transferInfo.getNumberCard1());
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }
}
