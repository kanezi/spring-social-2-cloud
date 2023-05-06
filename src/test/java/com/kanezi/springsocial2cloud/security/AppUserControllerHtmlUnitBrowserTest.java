package com.kanezi.springsocial2cloud.security;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.kanezi.springsocial2cloud.db.CleanUpTestData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("postgres")
@CleanUpTestData
public class AppUserControllerHtmlUnitBrowserTest {

    @LocalServerPort
    int port;

    private static WebClient webClient;

    private String homePage() {
        return "http://localhost:" + port + "/";
    }

    @BeforeAll
    static void initWebClient() {
        webClient = new WebClient();
    }

    @AfterAll
    static void closeWebClient() {
        webClient.close();
    }

    @Test
    void signUpPureHtmlUnitTest() throws IOException {
        final String USERNAME = "test";
        final String PASSWORD = "password test";

        //Create test user
        HtmlPage createUserFormPage = webClient.getPage(homePage() + "user/sign-up");

        HtmlForm signUpForm = createUserFormPage.getHtmlElementById("sign-up-form");

        HtmlTextInput usernameInput = createUserFormPage.getHtmlElementById("username");
        usernameInput.setValueAttribute(USERNAME);
        HtmlPasswordInput passwordInput = createUserFormPage.getHtmlElementById("password");
        passwordInput.setText(PASSWORD);

        HtmlSubmitInput signInButton = signUpForm.getOneHtmlElementByAttribute("input", "type", "submit");
        HtmlPage sigUpResultPage = signInButton.click();

        assertThat(sigUpResultPage.getUrl().toString()).endsWith("/login");


        // LOGIN with newly created user
        HtmlPage loginPage = webClient.getPage(homePage() + "/login");

        HtmlTextInput userInput = loginPage.getHtmlElementById("user");
        userInput.setValueAttribute(USERNAME);
        HtmlPasswordInput passInput = loginPage.getHtmlElementById("pass");
        passInput.setText(PASSWORD);

        HtmlPage userResultPage = loginPage.getHtmlElementById("login_button").click();

        assertThat(userResultPage.getUrl().toString()).endsWith("/user");

    }
}