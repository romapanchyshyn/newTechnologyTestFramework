import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class HeroeswmTests extends BasicTest{
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    RoulettePage roulettePage = new RoulettePage();
    WitherHummerSite witherHummerSite = new WitherHummerSite();
    public static String winingColorFromStatisticSite = null;

    @Test(description = "Ability for system to analyze roulette statistic")
    public void getColorStatistic(){
        open(WitherHummerSite.rouletteDetailPage);
        winingColorFromStatisticSite = witherHummerSite.getWinColor();
    }

//    @Test(description = "Ability for user to login", threadPoolSize = 5)
//    public void userEnableToLoginPositiveTestCase(){
//        open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
//        loginPage.loginToSite("Smth", "6789");
//        loginPage.assertUserLoggedIn();
//    }

    @Test(dependsOnMethods ="getColorStatistic", description = "Ability for to bet in roulette", threadPoolSize = 5)
    public void userAbleToBetInRoulette() throws InterruptedException {
        open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
        loginPage.loginToSite("Shedon", "Lamak2381009");
        mainPage.userClicksOnRouletteOption();
        roulettePage.userClickOnGamesHistoryLink();
        roulettePage.findColorForBet();
        mainPage.userClicksOnRouletteOption();
        roulettePage.userMakesBet("300");
//        roulettePage.betIsCreated();
        mainPage.userClicksOnRouletteOption();
        roulettePage.userMakesBet("300", winingColorFromStatisticSite);
//        roulettePage.betIsCreated();
        Selenide.sleep(1000);
    }

}
