import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class RoulettePage {
    private static final SelenideElement betCostField = $("[name = 'bet']");
    private static final SelenideElement submitBet = $("[type = 'submit']");
    private static final SelenideElement gamesHistory = $x("//a[text() = 'История игр']");
    private String winingColor;


    public void findColorForBet() {
        this.winingColor = $$(String.format("font[color ='%s']", ColorEnum.BLACK)).size() >= $$(String.format("font[color ='%s']", ColorEnum.RED)).size() ? ColorEnum.BLACK.getColorName() : ColorEnum.RED.getColorName();
    }

    public void userClickOnGamesHistoryLink(){
        gamesHistory.click();
    }

    @Step("User makes bet {betCost} on {winingColor} color")
    public void userMakesBet(String betCost){
        $(String.format("[title = '%s']", winingColor)).click();
        betCostField.val(betCost);
        submitBet.click();
    }

    @Step("Assert that bet is created")
    public void betIsCreated() {
        $x("//*[text() = 'Ваши ставки']//parent::center//following-sibling::table//td[child::img[@title = 'Золото']]//following-sibling::*//b").shouldHave(Condition.text("300"));
    }
}

