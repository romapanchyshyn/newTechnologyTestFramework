import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.hamcrest.core.IsNull;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class RoulettePage {
    private static final SelenideElement betCostField = $("[name = 'bet']");
    private static final SelenideElement submitBet = $("[value='Поставить!']");
    private static final SelenideElement gamesHistory = $x("//a[text() = 'История игр']");
    private static final SelenideElement lastGame = $x("//a[text() = 'Прошлая игра']");
    private static final SelenideElement theAmountOfWinnings = $x("(//table[@class = 'wbwhite']//table//tbody//tr/td[2])[2]");
    private static final SelenideElement theAmountOfLastBet = $x("(//table[@class = 'wbwhite']//table//tbody//tr/td[2])[1]");
    private String winingColor;



    public void findColorForBet() {
        this.winingColor = $$(String.format("font[color ='%s']", ColorEnum.BLACK)).size() >= $$(String.format("font[color ='%s']", ColorEnum.RED)).size() ? ColorEnum.RED.getColorName() : ColorEnum.BLACK.getColorName();
    }

    public void userClickOnGamesHistoryLink(){
        gamesHistory.click();
    }

    @Step("User clicks on last game link")
    public void userClicksOnLastGameLink(){
        lastGame.click();
    }

    @Step("User makes bet {betCost} on {this.winingColor} color")
    public void userMakesBet(String betCost){
        $(String.format("[title = '%s']", winingColor)).click();
        betCostField.val(betCost);
        submitBet.click();
    }

    @Step("User makes bet {betCost} on {color} color")
    public void userMakesBet(String betCost, String color){
        if(!Objects.isNull(color)) {
            $(String.format("[title = '%s']", color)).click();
            betCostField.val(betCost);
            submitBet.click();
        }
    }

    @Step("User makes bet {bet} on {color} color")
    public void userMakesBet(Integer bet, String color){
        if(!Objects.isNull(color)) {
            $(String.format("[title = '%s']", color)).click();
            betCostField.val(String.valueOf(bet));
            submitBet.click();
        }
    }

    @Step("Assert that bet is created")
    public void betIsCreated() {
        $x("//*[text() = 'Ваши ставки']//parent::center//following-sibling::table//td[child::img[@title = 'Золото']]//following-sibling::*//b").shouldHave();
    }

    public Integer getAmountOfGoldFormElement(){
        return Integer.parseInt(theAmountOfWinnings.getText().replace(",", ""));
    }

    @Step("In last game user win: '| {amountOfGold} |' gold")
    public Integer getLastWinningSum(Integer amountOfGold){
        return amountOfGold;
    }

    public Integer getLastBet(){
        return Integer.parseInt(theAmountOfLastBet.getText().replace(",", ""));
    }

    public int getBetAmount() {
        int bet = 375;
        if (getAmountOfGoldFormElement().equals(0)) {
            bet = getLastBet() * 2;
        }
        return bet;
    }
}

