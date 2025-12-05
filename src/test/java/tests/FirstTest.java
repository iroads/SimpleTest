package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {


    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";   // Можно заменить на firefox, edge
    }


    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().savePageSource(true).screenshots(true)
        );
    }

    @Test
    @Tag("ui")
    void googleSearchTest() {
        // Открываем
        open("http://212.109.192.164:8080");

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
