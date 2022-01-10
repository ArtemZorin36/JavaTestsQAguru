package severstal.zorin.homework.softassert;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestDragAndDrop {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1280";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    public void moveSquareTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-a").shouldHave(text("b"));
        $("#column-b").shouldHave(text("a"));
    }

    @AfterEach
    void after(){
        closeWebDriver();
    }
}