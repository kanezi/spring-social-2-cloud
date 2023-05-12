package com.kanezi.springsocial2cloud.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LogInPage {
    private final SelenideElement userInput = $("#user");
    private final SelenideElement passInput = $("#pass");

    private final SelenideElement logInButton = $(By.id("login_button"));

    public void logIn(String username, String password) {
        userInput.setValue(username);
        passInput.setValue(password);

        logInButton.click();
    }
}
