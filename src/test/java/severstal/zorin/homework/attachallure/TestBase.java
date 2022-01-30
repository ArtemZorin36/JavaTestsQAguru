package severstal.zorin.homework.attachallure;

import com.codeborne.selenide.Configuration;
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
        baseUrl = "https://demoqa.com";
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "91");
        String remoteUrl = System.getProperty("remoteUrl", "selenoid.autotests.cloud/wd/hub");
        String login = System.getProperty("login", "user1");
        String pass = System.getProperty("pass", "1234");

        baseUrl = "https://demoqa.com";
        browserSize = "1920x1080";
        Configuration.browser = browser;
        browserVersion = version;

        String url = "https://" + login + ":" + pass + "@" + remoteUrl;

        remote = url;
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
