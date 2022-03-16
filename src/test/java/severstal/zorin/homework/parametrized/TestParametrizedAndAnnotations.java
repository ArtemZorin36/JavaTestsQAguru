package severstal.zorin.homework.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestParametrizedAndAnnotations {


    @ValueSource(strings = {"asolntsev", "svasenkov"})
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
    void valueSourceSearch(String TestData) {
        open("https://www.github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(TestData).pressEnter();
        $(".repo-list").shouldHave(text(TestData));
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
        $(".repo-list").shouldHave(text(expectedResult));
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
        $(".repo-list").shouldHave(text(expectedResult));
    }

    enum Users {
        asolntsev, svasenkov;
    }

    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
    @EnumSource(Users.class)
        void testWithEnumSource(Users users) {
            open("https://www.github.com");
            $(".header-search-input").click();
            $(".header-search-input").setValue(String.valueOf(users)).pressEnter();
            $(".repo-list").shouldHave(text(String.valueOf(users)));
        }
        }

