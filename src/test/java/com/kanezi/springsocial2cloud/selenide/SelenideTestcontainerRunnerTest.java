package com.kanezi.springsocial2cloud.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.testcontainers.containers.BrowserWebDriverContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(TextReportExtension.class)
public class SelenideTestcontainerRunnerTest {

    static BrowserWebDriverContainer<?> browserContainer = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions());

    @RegisterExtension
    static ScreenShooterExtension screenShooterExtension = new ScreenShooterExtension()
            .to("target/selenide");


    @BeforeAll
    static void setUpDriver(@Autowired Environment environment) {

        Integer port = environment.getProperty("local.server.port", Integer.class);

        org.testcontainers.Testcontainers.exposeHostPorts(port);
        browserContainer.start();

        WebDriverRunner.setWebDriver(browserContainer.getWebDriver());

        Configuration.baseUrl = String.format("http://host.testcontainers.internal:%d", port);
    }

    @AfterAll
    static void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
