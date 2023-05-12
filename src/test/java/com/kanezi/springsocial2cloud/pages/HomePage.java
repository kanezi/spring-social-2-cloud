package com.kanezi.springsocial2cloud.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public final SelenideElement header = $("h1");
    public void open() {
        Selenide.open("/");
    }
}
