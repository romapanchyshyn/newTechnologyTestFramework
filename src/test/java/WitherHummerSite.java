import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class WitherHummerSite {
    public static final String rouletteDetailPage = "http://daily.heroeswm.ru/roulette/detal.php";
    public static final String color = "//*[@style ='background:%s']//following-sibling::td[3]";
    public static final int EXPECTED_DIFFERENCE = 15;

    @Step("System gets wining color from statistic")
    public String getWinColor(){
        String colorResult = null;
        if(getNumberOfDroppingOutByColor(ColorEnum.BLACK)-getNumberOfDroppingOutByColor(ColorEnum.RED)>= EXPECTED_DIFFERENCE){
            colorResult = ColorEnum.RED.getColorName();
        }
        else if(getNumberOfDroppingOutByColor(ColorEnum.RED)-getNumberOfDroppingOutByColor(ColorEnum.BLACK)>= EXPECTED_DIFFERENCE){
            colorResult = ColorEnum.BLACK.getColorName();
        }
        return colorResult;
    }

    private int getNumberOfDroppingOutByColor(ColorEnum colorEnum){
        return Integer.parseInt($x(String.format(color, colorEnum.getColorName())).getText().trim());
    }



}
