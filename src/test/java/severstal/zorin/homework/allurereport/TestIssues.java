package severstal.zorin.homework.allurereport;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class TestIssues {
    PageObjectSteps steps = new PageObjectSteps();

    @Test
    @Owner("ArtemZorin36")
    @Feature("Issues")
    @Story("Проверка наличия вкладки Issues")
    @DisplayName("Проверка наличия вкладки Issues с листенером")
    public void testListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("ArtemZorin36/JavaTestsQAguru");
        $(".header-search-input").submit();

        $(linkText("ArtemZorin36/JavaTestsQAguru")).click();
        $("#issues-tab").should(Condition.visible);
    }

    @Test
    @Owner("ArtemZorin36")
    @Feature("Issues")
    @Story("Проверка наличия вкладки Issues")
    @DisplayName("Проверка наличия вкладки Issues с лямбдой")
    public void testLambda() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + "ArtemZorin36/JavaTestsQAguru", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("ArtemZorin36/JavaTestsQAguru");
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий", () -> {
            $(linkText("ArtemZorin36/JavaTestsQAguru")).click();
        });
        step("Открываем таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем наличие Issue", () -> {
            $("#issues-tab").should(Condition.visible);
        });
    }

    @Test
    @Owner("ArtemZorin36")
    @Feature("Issues")
    @Story("Проверка наличия вкладки Issues")
    @DisplayName("Проверка наличия вкладки Issues с аннотациями")
    public void testAnnotatedStep() {
        steps.openMainPage();
        steps.searchForRepository("ArtemZorin36/JavaTestsQAguru");
        steps.openRepositoryPage("ArtemZorin36/JavaTestsQAguru");
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber();
    }

    @AfterEach
    public void saveSources() {
        steps.attachPageSource();
    }
}
