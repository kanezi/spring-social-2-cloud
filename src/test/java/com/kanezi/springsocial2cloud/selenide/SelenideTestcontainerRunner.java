package com.kanezi.springsocial2cloud.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.testcontainers.containers.BrowserWebDriverContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(TextReportExtension.class)
public class SelenideTestcontainerRunner {

    static BrowserWebDriverContainer<?> browserContainer = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions());

    @LocalServerPort
    int port;

    @RegisterExtension
    static ScreenShooterExtension screenShooterExtension = new ScreenShooterExtension()
            .to("target/selenide");


    @BeforeAll
    static void setUpDriver(@Autowired Environment environment) {
//        Configuration.reportsFolder = "target/selenide/reports";
//        Configuration.downloadsFolder = "target/selenide/downloads";

        org.testcontainers.Testcontainers.exposeHostPorts(environment.getProperty("local.server.port", Integer.class));
        browserContainer.start();

        WebDriverRunner.setWebDriver(browserContainer.getWebDriver());
    }

    @BeforeEach
    void setUpBaseUrl() {
        Configuration.baseUrl = String.format("http://host.testcontainers.internal:%d", this.port);
    }
}
