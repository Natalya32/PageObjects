package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferToCardPage {

    private SelenideElement heading = $x("//h1[contains(text(),\"Пополнение карты\")]");
    private SelenideElement amountField = $("div:nth-child(1) > div > span > span > span.input__box > input");
    private SelenideElement fromField = $x("//input[@type='tel']");
    private SelenideElement replenishButton = $("[data-test-id=\"action-transfer\"]");
    private SelenideElement errorNot = $(".notification__title");

    public TransferToCardPage() {
        heading.shouldBe(visible);
    }

    private void input(int amount, String from) {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        amountField.setValue(Integer.toString(amount));
        fromField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromField.setValue(from);
        replenishButton.click();
    }

    public DashboardPage inputTransterInfo(int amount, String from) {
        input(amount, from);
        return new DashboardPage();
    }

    public void inputTransterInfoWrongCard(int amount, String from) {
        input(amount, from);
        errorNot.shouldBe(visible);
    }
}
