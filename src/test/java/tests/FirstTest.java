package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {


    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";   // Можно заменить на firefox, edge
        Configuration.browserVersion = "122.0";
        // версия из browsers.json

        Configuration.remote = "http://212.109.192.164:8091/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true,
                "enableLog", true
        ));

        Configuration.browserCapabilities = capabilities;

    }


    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().savePageSource(true).screenshots(true)
        );
    }

    @AfterEach
    void addAttach(){
        Attach.screenshotAs("LastScreenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("ui")
    void googleSearchTest() {
        // Открываем
        step("Открываем приложение", () ->
                open("http://212.109.192.164:8080")
        );


        // Вводим текст в поиске
        $x("//input[@id='username']").setValue("Selenide").pressEnter();

        // Проверяем, что в результатах есть Selenide.org
        $x("//div[contains(text(), 'Неправильные имя или пароль')]").shouldBe(Condition.visible);
    }

    @Test
    public void firstTest(){
        assertTrue(true);
    }
}
