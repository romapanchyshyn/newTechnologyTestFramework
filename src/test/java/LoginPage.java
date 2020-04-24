import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Waiter;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {
    private final SelenideElement loginInput = $("[name='login']");
    private final SelenideElement passwordInput = $("[name='pass']");
    private final SelenideElement submitLoginFormButton = $(".entergame");
    private final SelenideElement nickName = $x("//b[text() = 'Shedon']");

    @Step("User with login: {login}, password {password} logins to website")
    public void loginToSite(String login, String password){
        loginInput.setValue(login);
        passwordInput.setValue(password);
        submitLoginFormButton.click();
    }

    @Step("Assert that user login")
    public void assertUserLoggedIn(){
        nickName.shouldBe(Condition.visible);
    }
}
