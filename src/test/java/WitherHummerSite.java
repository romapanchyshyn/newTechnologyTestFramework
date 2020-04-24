import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class WitherHummerSite {
    public static final String rouletteDetailPage = "http://daily.heroeswm.ru/roulette/detal.php";
    public static final String colorByDay = "//*[@style ='background:%s']//following-sibling::td[3]";
    public static final String colorByRow = "//*[@style ='background:%s']//following-sibling::td[1]";
    public static final int EXPECTED_DIFFERENCE = 15;
    public static final int EXPECTED_AMOUNT_OF_LOSS = 4;


    @Step("System gets wining color from day statistic")
    public String getWinColorByDayStatistic(){
        String colorResult = null;
        if(getNumberOfDroppingOutByDay(ColorEnum.BLACK)- getNumberOfDroppingOutByDay(ColorEnum.RED)>= EXPECTED_DIFFERENCE){
            colorResult = ColorEnum.RED.getColorName();
        }
        else if(getNumberOfDroppingOutByDay(ColorEnum.RED)- getNumberOfDroppingOutByDay(ColorEnum.BLACK)>= EXPECTED_DIFFERENCE){
            colorResult = ColorEnum.BLACK.getColorName();
        }
        return colorResult;
    }


    @Step("System gets wining color row statistic")
    public String getWinColorByColorRowStatistic(){
        String colorResult = null;
        if(getNumberOfDroppingOutByColorOccursInRow(ColorEnum.BLACK) >= EXPECTED_AMOUNT_OF_LOSS){
            colorResult = ColorEnum.BLACK.getColorName();
        }
        else if(getNumberOfDroppingOutByColorOccursInRow(ColorEnum.RED) >= EXPECTED_AMOUNT_OF_LOSS){
            colorResult = ColorEnum.RED.getColorName();
        }
        return colorResult;
    }


    private int getNumberOfDroppingOutByDay(ColorEnum colorEnum){
        return Integer.parseInt($x(String.format(colorByDay, colorEnum.getColorName())).getText().trim());
    }

    private int getNumberOfDroppingOutByColorOccursInRow(ColorEnum colorEnum){
        return Integer.parseInt($x(String.format(colorByRow, colorEnum.getColorName())).getText().trim());
    }



}
