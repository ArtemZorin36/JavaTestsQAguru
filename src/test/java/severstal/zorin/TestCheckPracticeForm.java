package severstal.zorin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class TestCheckPracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1280";
        Configuration.holdBrowserOpen = false;
    }
    @Test
    void testCheckPracticeForm() {
        open("https://demoqa.com/automation-practice-form");

        //Writting
        $("#firstName").setValue("Boris");
        $("#lastName").setValue("Blade");
        $("#userEmail").setValue("BorisBlade@mail.ru");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("8800555353");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1976");
        $("[aria-label='Choose Monday, June 28th, 1976']").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        File file = new File("src/test/resources/FirstJob.jpg");
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("Voronezh, Blade Street, 10");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Gurgaon").pressEnter();
        $("#submit").click();

        //Checks
        $("#example-modal-sizes-title-lg").shouldBe(visible);
        $(".table-responsive").shouldHave(
                text("Boris"),
                text("Blade"),
                text("BorisBlade@mail.ru"),
                text("8800555353"),
                text("28 June,1976"),
                text("Computer Science"),
                text("Sports, Reading, Music"),
                text("FirstJob.jpg"),
                text("Voronezh, Blade Street, 10"),
                text("NCR Gurgaon"));
    }

    @AfterEach
    void after(){
        closeWebDriver();
    }

}
