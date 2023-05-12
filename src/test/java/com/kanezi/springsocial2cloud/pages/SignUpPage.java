package com.kanezi.springsocial2cloud.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    private final SelenideElement usernameInput = $("input#username");
    private final SelenideElement passwordInput = $("input#password");

    private final SelenideElement signInButton = $("input[type='submit']");


    public void signUp(String username, String password) {

        usernameInput.setValue(username);
        passwordInput.setValue(password);

        signInButton.click();
    }
}
