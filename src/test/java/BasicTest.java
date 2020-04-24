import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.*;

public class BasicTest {
    @BeforeMethod
    public void beforeTest(){
        SelenideLogger.addListener("Allure Selenide Listener",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }


    @BeforeMethod(description = "Open home page")
    public void tirUp(){
        open(PropertyConfigurationUtils.getPropertyFromFile("base.url"));
    }

    @AfterTest
    public void afterTest(){
        SelenideLogger.removeListener("Allure Selenide Listener");
    }
}
