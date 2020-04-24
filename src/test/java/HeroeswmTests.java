import org.testng.annotations.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class HeroeswmTests extends BasicTest{
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    RoulettePage roulettePage = new RoulettePage();
    WitherHummerSite witherHummerSite = new WitherHummerSite();
    public static String winingColorFromStatisticSiteByDay = null;
    public static String winingColorFromStatisticSiteByOccurs = null;

//    @Test(description = "Ability for system to analyze roulette statistic")
//    public void getColorStatisticByDay(){
//        open(WitherHummerSite.rouletteDetailPage);
//        winingColorFromStatisticSiteByDay = witherHummerSite.getWinColorByDayStatistic();
//    }

    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Ability for system to analyze roulette statistic by color occurs ")
    public void getColorStatisticByColorOccurs(){
        open(WitherHummerSite.rouletteDetailPage);
        winingColorFromStatisticSiteByOccurs = witherHummerSite.getWinColorByColorRowStatistic();
    }

//    @Test(description = "Ability for user to login", threadPoolSize = 5)
//    public void userEnableToLoginPositiveTestCase(){
//        open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
//        loginPage.loginToSite("Smth", "6789");
//        loginPage.assertUserLoggedIn();
//    }

//    @Test(dependsOnMethods = "getColorStatisticByDay", description = "Ability for to bet in roulette", threadPoolSize = 5)
//    public void userAbleToBetInRoulette() throws InterruptedException {
//        open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
//        loginPage.loginToSite("Shedon", "Lamak2381009");
//        mainPage.userClicksOnRouletteOption();
//        roulettePage.userClickOnGamesHistoryLink();
//        roulettePage.findColorForBet();
//        mainPage.userClicksOnRouletteOption();
//        roulettePage.userMakesBet("300");
//        mainPage.userClicksOnRouletteOption();
//        roulettePage.userMakesBet("300", winingColorFromStatisticSiteByDay);
//        roulettePage.getLastWinningSum(roulettePage.getAmountOfGoldFormElement());
//    }

    @Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "getColorStatisticByColorOccurs", description = "Ability for to bet in roulette by occurs", threadPoolSize = 5)
    public void userAbleToBetInRouletteByOccurs() {
        if(!Objects.isNull(winingColorFromStatisticSiteByOccurs)) {
            open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
            loginPage.loginToSite("Shedon", "Lamak2381009");
            mainPage.userClicksOnRouletteOption();
            roulettePage.userClicksOnLastGameLink();
            int logicBetAmount = roulettePage.getBetAmount();
            mainPage.userClicksOnRouletteOption();
            roulettePage.userMakesBet(logicBetAmount, winingColorFromStatisticSiteByOccurs);
            roulettePage.userClicksOnLastGameLink();
            roulettePage.getLastWinningSum(roulettePage.getAmountOfGoldFormElement());
        }
    }

}
