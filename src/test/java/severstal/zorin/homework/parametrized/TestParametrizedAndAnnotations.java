package severstal.zorin.homework.parametrized;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestParametrizedAndAnnotations {


    @ValueSource(strings = {"asolntsev", "svasenkov"})
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
    void valueSourceSearch(String TestData) {
        open("https://www.github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(TestData).pressEnter();
        $(".repo-list").shouldHave(Condition.text(TestData));
    }


    @CsvSource(value = {
            "asolntsev, asolntsev/asolntsev.github.io",
            "svasenkov, svasenkov/svasenkov"
    })

    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
    void csvSearch(String TestData, String expectedResult) {
        open("https://www.github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(TestData).pressEnter();
        $(".repo-list").shouldHave(Condition.text(expectedResult));
    }


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("asolntsev", "asolntsev/asolntsev.github.io"),
                Arguments.of("svasenkov", "svasenkov/svasenkov")
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
    void methodSearch(String TestData, String expectedResult) {
        open("https://www.github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(TestData).pressEnter();
        $(".repo-list").shouldHave(Condition.text(expectedResult));
    }

}
