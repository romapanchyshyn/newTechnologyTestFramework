import org.testng.annotations.Test;

public class HeroeswmTests extends BasicTest{
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    RoulettePage roulettePage = new RoulettePage();

    @Test(description = "Ability for user to login", threadPoolSize = 5)
    public void userEnableToLoginPositiveTestCase(){
        loginPage.loginToSite("Smth", "6789");
        loginPage.assertUserLoggedIn();
    }

    @Test(description = "Ability for to bet in roulette", threadPoolSize = 5)
    public void userAbleToBetInRoulette(){
        loginPage.loginToSite("Shedon", "Lamak2381009");
        mainPage.userClicksOnRouletteOption();
        roulettePage.userClickOnGamesHistoryLink();
        roulettePage.findColorForBet();
        mainPage.userClicksOnRouletteOption();
        roulettePage.userMakesBet("300");
        roulettePage.betIsCreated();


    }

}
