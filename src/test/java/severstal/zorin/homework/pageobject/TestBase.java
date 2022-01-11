package severstal.zorin.homework.pageobject;

import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1980x1280";
        holdBrowserOpen = false;
        baseUrl = "https://demoqa.com";
    }
}
