package severstal.zorin.homework.softassert;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Tag("HW")
public class TestSoftAssert {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1280";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    public void checkPagesSoftAssertionsInGithub() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
        $("[data-filterable-for=wiki-pages-filter]").shouldHave(text("SoftAssertions"));
        $("[data-filterable-for='wiki-pages-filter']").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
    }

    @AfterEach
    void after(){
        closeWebDriver();
    }
}