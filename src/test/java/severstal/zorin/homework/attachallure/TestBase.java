package severstal.zorin.homework.attachallure;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static severstal.zorin.homework.attachallure.helpers.Attach.*;

public class TestBase {

    @BeforeAll
    @Step("Настройки браузера")
    static void beforeAll() {
        browserSize = "1980x1280";
        holdBrowserOpen = false;
        baseUrl = "https://demoqa.com";
        remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        addVideo();
        closeWebDriver();
    }
}
