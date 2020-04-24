import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement rouletteOption = $x("//b[text() = 'Рулетка']");

    @Step("User chooses roulette option in menu")
    public void userClicksOnRouletteOption (){
        rouletteOption.click();
    }
}
