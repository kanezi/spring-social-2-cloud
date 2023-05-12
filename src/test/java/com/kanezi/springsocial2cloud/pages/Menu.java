package com.kanezi.springsocial2cloud.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Menu {
    private final SelenideElement signUpButton = $(byText("Sign up"));
    private final SelenideElement logInButton = $(byText("Log in"));
    private final SelenideElement logOutButton = $(byText("Log out"));

    public final ElementsCollection buttons = $$("div .button");

    public void signUp() {
        signUpButton.click();
    }

    public void logIn() {
        logInButton.click();
    }

    public void logOut() {
        logOutButton.click();
    }
}
